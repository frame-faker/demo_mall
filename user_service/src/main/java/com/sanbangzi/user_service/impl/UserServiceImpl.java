package com.sanbangzi.user_service.impl;

import com.sanbangzi.common.utils.BeanUtil;
import com.sanbangzi.common.utils.RandomUtil;
import com.sanbangzi.domain.dto.req.UserReqDTO;
import com.sanbangzi.domain.dto.res.UserResDTO;
import com.sanbangzi.domain.entity.UserEntity;
import com.sanbangzi.user_service.dao.UserDao;
import com.sanbangzi.user_service_api.api.UserService;
import com.sanbangzi.user_service_api.consts.UserConst;
import com.sanbangzi.user_service_api.exception.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserResDTO getById(Long userId) throws UserException {
        return BeanUtil.copyObject(userDao.selectByPrimaryKey(userId), UserResDTO.class);
    }

    @Override
    public UserResDTO getByOpenId(String openId) throws UserException {
        Example example = Example.builder(UserEntity.class)
                .where(WeekendSqls.<UserEntity>custom().andEqualTo(UserEntity::getOpenId, openId))
                .build();
        return BeanUtil.copyObject(userDao.selectOneByExample(example), UserResDTO.class);
    }

    @Override
    public Long save(UserReqDTO user) throws Exception {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        String code = RandomUtil.uuid();
        Date now = new Date();
        entity.setCode(code);
        entity.setStatus(UserConst.USER_STATUS_NORMAL);
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        userDao.insertSelective(entity);
        return entity.getId();
    }
}
