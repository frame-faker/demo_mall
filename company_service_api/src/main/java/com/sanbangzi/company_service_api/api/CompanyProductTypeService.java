package com.sanbangzi.company_service_api.api;

import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyProductTypeResDTO;

import java.util.List;

public interface CompanyProductTypeService {

    List<CompanyProductTypeResDTO> listByCompanyId(Long companyId) throws CompanyException;

}
