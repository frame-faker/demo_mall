package com.sanbangzi.user_service.impl;

import com.sanbangzi.common.utils.RandomUtil;
import com.sanbangzi.common.utils.StringUtil;
import com.sanbangzi.domain.dto.req.UserReqDTO;
import com.sanbangzi.domain.dto.res.UserResDTO;
import com.sanbangzi.domain.dto.res.XcxLoginResDTO;
import com.sanbangzi.thriparty.ali.oss.OssConst;
import com.sanbangzi.thriparty.ali.oss.OssUtil;
import com.sanbangzi.thriparty.wx.xcx.XcxManage;
import com.sanbangzi.thriparty.wx.xcx.XcxUtil;
import com.sanbangzi.user_service_api.api.UserService;
import com.sanbangzi.user_service_api.api.XcxLoginService;
import com.sanbangzi.user_service_api.consts.UserCacheKeyConst;
import com.sanbangzi.user_service_api.exception.UserErrorCode;
import com.sanbangzi.user_service_api.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class XcxLoginServiceImpl implements XcxLoginService {

    @Autowired
    private XcxManage xcxManage;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public XcxLoginResDTO login(String code) throws UserException {
        Map<String, String> xcxResMap = xcxManage.getOpenIdByCode(code);
        String openId = xcxResMap.get("openid");
        if (StringUtil.isBlank(openId)) {
            throw UserException.throwErrorCode(UserErrorCode.LOGIN_ERROR);
        }
        UserResDTO userResDTO = userService.getByOpenId(openId);
        if (null == userResDTO) {
            throw UserException.throwErrorCode(UserErrorCode.LOGIN_ERROR);
        }
        Long userId = userResDTO.getId();
        String accessToken = RandomUtil.uuid();
        stringRedisTemplate.opsForValue().set(UserCacheKeyConst.XCX_ACCESS_TOKEN + accessToken, String.valueOf(userId), UserCacheKeyConst.XCX_ACCESS_TOKEN_EXPIRE_TIME);

        XcxLoginResDTO xcxLoginResDTO = new XcxLoginResDTO();
        xcxLoginResDTO.setUserId(userId);
        xcxLoginResDTO.setAccessToken(accessToken);
        xcxLoginResDTO.setHeadPhoto(userResDTO.getHeadPhoto());
        xcxLoginResDTO.setNickName(userResDTO.getNickName());
        return xcxLoginResDTO;
    }

    @Override
    public XcxLoginResDTO register(String code, String userInfo) throws Exception {
        Map<String, String> xcxResMap = xcxManage.getOpenIdByCode(code);
        String openId = xcxResMap.get("openid");
        String sessionKey = xcxResMap.get("session_key");
        if (StringUtil.isBlank(openId)) {
            throw UserException.throwErrorCode(UserErrorCode.REGISTER_ERROR);
        }
        if (null != userService.getByOpenId(openId)) {
            throw UserException.throwErrorCode(UserErrorCode.REGISTER_ERROR);
        }
        Map<String, Object> decodeUserInfo = XcxUtil.decodeUserInfo(userInfo, sessionKey);
        if (null == decodeUserInfo) {
            throw UserException.throwErrorCode(UserErrorCode.REGISTER_ERROR);
        }

        // 注册用户
        UserReqDTO userReq = new UserReqDTO();
        userReq.setOpenId(openId);
        String nickName = decodeUserInfo.get("nickName").toString();
        userReq.setNickName(nickName);
        String avatarUrl = decodeUserInfo.get("avatarUrl").toString();
        // 上传头像
        String key = String.format("%s%s%s", OssConst.USER_HEAD_PHOTO_DIR, RandomUtil.uuid(), ".jpeg");
        String headPhoto = OssUtil.uploadUrlImg(key, avatarUrl);
        userReq.setHeadPhoto(headPhoto);
        Long userId = userService.save(userReq);

        // 登录
        String accessToken = RandomUtil.uuid();
        stringRedisTemplate.opsForValue().set(UserCacheKeyConst.XCX_ACCESS_TOKEN + accessToken, String.valueOf(userId), UserCacheKeyConst.XCX_ACCESS_TOKEN_EXPIRE_TIME);

        XcxLoginResDTO xcxLoginResDTO = new XcxLoginResDTO();
        xcxLoginResDTO.setUserId(userId);
        xcxLoginResDTO.setAccessToken(accessToken);
        xcxLoginResDTO.setNickName(nickName);
        xcxLoginResDTO.setHeadPhoto(headPhoto);
        return xcxLoginResDTO;
    }
}
