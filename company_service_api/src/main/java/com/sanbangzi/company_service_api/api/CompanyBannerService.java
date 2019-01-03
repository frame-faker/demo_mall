package com.sanbangzi.company_service_api.api;

import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyBannerResDTO;

import java.util.List;

public interface CompanyBannerService {

    List<CompanyBannerResDTO> listByCompanyId(Long companyId) throws CompanyException;
}
