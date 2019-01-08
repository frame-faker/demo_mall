package com.sanbangzi.company_service_api.api;

import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.domain.dto.res.CompanyConfigResDTO;
import com.sanbangzi.domain.dto.res.CompanyResDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyConfigResDTO> config(Long companyId) throws CompanyException;

    CompanyResDTO getById(Long companyId) throws CompanyException;
}
