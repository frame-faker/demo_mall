package com.sanbangzi.xcx_web.api;

import com.sanbangzi.common.utils.WebResponse;
import com.sanbangzi.domain.dto.req.ProductReqDTO;
import com.sanbangzi.product_service_api.api.ProductService;
import com.sanbangzi.xcx_web.annotation.IgnoreLogin;
import com.sanbangzi.xcx_web.annotation.RequestParamsNotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @IgnoreLogin
    @GetMapping("/getNew")
    @RequestParamsNotEmpty(names = {"companyId"})
    public WebResponse getNew(Long companyId) throws Exception {
        return xcxSuccess(productService.getNew(companyId));
    }

    @IgnoreLogin
    @PostMapping("/pageRecommend")
    @RequestParamsNotEmpty(names = {"companyId", "pageNum", "pageSize"})
    public WebResponse pageRecommend(@RequestBody ProductReqDTO productReqDTO) throws Exception {
        return xcxSuccess(productService.pageRecommend(productReqDTO));
    }

    @IgnoreLogin
    @PostMapping("/search")
    @RequestParamsNotEmpty(names = {"companyId", "pageNum", "pageSize"})
    public WebResponse search(@RequestBody ProductReqDTO productReqDTO) throws Exception {
        return xcxSuccess(productService.searchByTitle(productReqDTO));
    }

    @IgnoreLogin
    @PostMapping("/pageByType")
    @RequestParamsNotEmpty(names = {"companyId", "pageNum", "pageSize"})
    public WebResponse pageByType(@RequestBody ProductReqDTO productReqDTO) throws Exception {
        return xcxSuccess(productService.pageByType(productReqDTO));
    }

    @IgnoreLogin
    @GetMapping("/detail")
    @RequestParamsNotEmpty(names = {"productId"})
    public WebResponse getById(Long productId) throws Exception {
        return xcxSuccess(productService.getDetailById(productId));
    }

}
