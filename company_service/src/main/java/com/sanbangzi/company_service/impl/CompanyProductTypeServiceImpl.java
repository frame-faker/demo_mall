package com.sanbangzi.company_service.impl;

import com.sanbangzi.common.utils.BeanUtil;
import com.sanbangzi.company_service.dao.CompanyProductTypeDao;
import com.sanbangzi.company_service_api.api.CompanyProductTypeService;
import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyProductTypeResDTO;
import com.sanbangzi.domain.entity.CompanyProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

@Service
public class CompanyProductTypeServiceImpl implements CompanyProductTypeService {

    @Autowired
    private CompanyProductTypeDao companyProductTypeDao;

    @Override
    public List<CompanyProductTypeResDTO> listByCompanyId(Long companyId) throws CompanyException {
        Example example = Example.builder(CompanyProductTypeEntity.class)
                .where(WeekendSqls.<CompanyProductTypeEntity>custom().andEqualTo(CompanyProductTypeEntity::getCompanyId, companyId))
                .orderByAsc("sort").build();
        return BeanUtil.copyList(companyProductTypeDao.selectByExample(example), CompanyProductTypeResDTO.class);
    }

}
