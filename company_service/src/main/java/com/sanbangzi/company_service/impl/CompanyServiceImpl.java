package com.sanbangzi.company_service.impl;

import com.sanbangzi.common.utils.BeanUtil;
import com.sanbangzi.company_service.dao.CompanyConfigDao;
import com.sanbangzi.company_service.dao.CompanyDao;
import com.sanbangzi.company_service_api.api.CompanyService;
import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyConfigResDTO;
import com.sanbangzi.domain.dto.res.CompanyResDTO;
import com.sanbangzi.domain.entity.CompanyConfigEntity;
import com.sanbangzi.domain.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyConfigDao companyConfigDao;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<CompanyConfigResDTO> config(Long companyId) throws CompanyException {
        Example example = Example.builder(CompanyConfigEntity.class)
                .where(WeekendSqls.<CompanyConfigEntity>custom().andEqualTo(CompanyConfigEntity::getCompanyId, companyId))
                .build();
        return BeanUtil.copyList(companyConfigDao.selectByExample(example), CompanyConfigResDTO.class);
    }

    @Override
    public CompanyResDTO getById(Long companyId) throws CompanyException {
        return BeanUtil.copyObject(companyDao.selectByPrimaryKey(companyId), CompanyResDTO.class);
    }
}
