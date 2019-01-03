package com.sanbangzi.company_service.impl;

import com.sanbangzi.common.utils.BeanUtil;
import com.sanbangzi.company_service.dao.CompanyBannerDao;
import com.sanbangzi.company_service_api.api.CompanyBannerService;
import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyBannerResDTO;
import com.sanbangzi.domain.entity.CompanyBannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

@Service
public class CompanyBannerServiceImpl implements CompanyBannerService {

    @Autowired
    private CompanyBannerDao companyBannerMapper;

    @Override
    public List<CompanyBannerResDTO> listByCompanyId(Long companyId) throws CompanyException {
        Example example = Example.builder(CompanyBannerEntity.class)
                .where(WeekendSqls.<CompanyBannerEntity>custom().andEqualTo(CompanyBannerEntity::getCompanyId, companyId))
                .orderByAsc("sort").build();
        List<CompanyBannerEntity> companyBannerEntities = companyBannerMapper.selectByExample(example);

        return BeanUtil.copyList(companyBannerEntities, CompanyBannerResDTO.class);
    }

}
