package com.sanbangzi.xcx_web.api;

import com.sanbangzi.common.utils.WebResponse;
import com.sanbangzi.company_service_api.api.CompanyBannerService;
import com.sanbangzi.company_service_api.api.CompanyProductTypeService;
import com.sanbangzi.company_service_api.api.CompanyService;
import com.sanbangzi.domain.dto.res.CompanyBannerResDTO;
import com.sanbangzi.xcx_web.annotation.IgnoreLogin;
import com.sanbangzi.xcx_web.annotation.RequestParamsNotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyBannerService companyBannerService;

    @Autowired
    private CompanyProductTypeService companyProductTypeService;

    @Autowired
    private CompanyService companyService;

    @IgnoreLogin
    @GetMapping("/config")
    @RequestParamsNotEmpty(names = {"companyId"})
    public WebResponse config(Long companyId) throws Exception {
        return xcxSuccess(companyService.config(companyId));
    }

    @IgnoreLogin
    @GetMapping("/getById")
    @RequestParamsNotEmpty(names = {"companyId"})
    public WebResponse getById(Long companyId) throws Exception {
        return xcxSuccess(companyService.getById(companyId));
    }

    @IgnoreLogin
    @GetMapping("/banner")
    @RequestParamsNotEmpty(names = {"companyId"})
    public WebResponse banner(Long companyId) throws Exception {
        List<CompanyBannerResDTO> companyBannerResDTOS = companyBannerService.listByCompanyId(companyId);
        return xcxSuccess(companyBannerResDTOS);
    }

    @IgnoreLogin
    @GetMapping("/productType")
    @RequestParamsNotEmpty(names = {"companyId"})
    public WebResponse productType(Long companyId) throws Exception {
       return xcxSuccess(companyProductTypeService.listByCompanyId(companyId));
    }
}
