package com.sanbangzi.product_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sanbangzi.common.utils.BeanUtil;
import com.sanbangzi.common.utils.CollectionUtil;
import com.sanbangzi.common.utils.MyPage;
import com.sanbangzi.domain.dto.req.ProductReqDTO;
import com.sanbangzi.domain.dto.res.ProductDetailResDTO;
import com.sanbangzi.domain.dto.res.ProductResDTO;
import com.sanbangzi.domain.entity.ProductEntity;
import com.sanbangzi.domain.entity.ProudctResourceEntity;
import com.sanbangzi.product_service.dao.ProductDao;
import com.sanbangzi.product_service.dao.ProudctResourceDao;
import com.sanbangzi.product_service_api.api.ProductService;
import com.sanbangzi.product_service_api.consts.ProductConst;
import com.sanbangzi.product_service_api.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProudctResourceDao proudctResourceDao;

    @Override
    public List<ProductResDTO> getNew(Long companyId) throws ProductException {
        PageHelper.startPage(1, ProductConst.NEW_PRODUCT_NUMBER);
        Example example = Example.builder(ProductEntity.class)
                .where(WeekendSqls.<ProductEntity>custom().andEqualTo(ProductEntity::getCompanyId, companyId))
                .orderByDesc("createTime").build();
        return BeanUtil.copyList(productDao.selectByExample(example), ProductResDTO.class);
    }

    @Override
    public MyPage<ProductResDTO> pageRecommend(ProductReqDTO productReqDTO) throws ProductException {
        Example example = Example.builder(ProductEntity.class)
                .where(WeekendSqls.<ProductEntity>custom().andEqualTo(ProductEntity::getCompanyId, productReqDTO.getCompanyId()))
                .orderByDesc("updateTime").build();
        PageHelper.startPage(productReqDTO.getPageNum(), productReqDTO.getPageSize());
        return BeanUtil.copyPage(MyPage.newInstance(PageInfo.of(productDao.selectByExample(example))), ProductResDTO.class);
    }

    @Override
    public MyPage<ProductResDTO> searchByTitle(ProductReqDTO productReqDTO) throws ProductException {
        Example example = Example.builder(ProductEntity.class)
                .where(WeekendSqls.<ProductEntity>custom().andLike(ProductEntity::getTitle, "%" + productReqDTO.getTitle() + "%"))
                .orderByDesc("updateTime").build();
        PageHelper.startPage(productReqDTO.getPageNum(), productReqDTO.getPageSize());
        return BeanUtil.copyPage(MyPage.newInstance(PageInfo.of(productDao.selectByExample(example))), ProductResDTO.class);
    }

    @Override
    public MyPage<ProductResDTO> pageByType(ProductReqDTO productReqDTO) throws ProductException {
        PageHelper.startPage(productReqDTO.getPageNum(), productReqDTO.getPageSize());
        return MyPage.newInstance(PageInfo.of(productDao.pageByType(productReqDTO.getTypeId())));
    }

    @Override
    public ProductDetailResDTO getDetailById(Long productId) throws Exception {
        ProductEntity entity = productDao.selectByPrimaryKey(productId);
        if (null == entity) {
            throw ProductException.throwBusiError("产品已下线");
        }
        ProductDetailResDTO res = BeanUtil.copyObject(entity, ProductDetailResDTO.class);

        // 获取产品资源数据
        Example example = Example.builder(ProudctResourceEntity.class)
                .where(WeekendSqls.<ProudctResourceEntity>custom().andEqualTo(ProudctResourceEntity::getProductId, productId))
                .orderByDesc("sort").build();
        List<ProudctResourceEntity> resourceEntities = proudctResourceDao.selectByExample(example);
        if (CollectionUtil.isBlank(resourceEntities)) {
            throw ProductException.throwBusiError("产品已下线");
        }
        List<String> carouselList = Lists.newArrayList();
        String detail = null;
        for (ProudctResourceEntity resourceEntity : resourceEntities) {
            String resource = resourceEntity.getResource();
            Byte type = resourceEntity.getType();
            switch (type) {
                case ProductConst.PRODUCT_RESOURCE_TYPE_CAROUSEL:
                    carouselList.add(resource);
                    break;
                case ProductConst.PRODUCT_RESOURCE_TYPE_DETAIL:
                    detail = resource;
                    break;
                default:
            }
        }
        res.setCarousel(Joiner.on(",").join(carouselList));
        res.setDetail(detail);
        return res;
    }
}
