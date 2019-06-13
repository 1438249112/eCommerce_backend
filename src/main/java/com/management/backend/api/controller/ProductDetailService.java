package com.management.backend.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.backend.api.mybatis.mapper.ProductDetailMapper;
import com.management.backend.api.mybatis.model.ProductDetail;
import com.management.backend.api.mybatis.model.ProductDetailExample;
import com.management.backend.api.util.Resp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Api(value = "productDetail operations", tags = {"productDetail service"})
public class ProductDetailService {

    @Autowired
    private ProductDetailMapper productDetailMapper;


    @ApiOperation(value = "获取产品詳情详细信息", notes = "根据产品詳情的id来获取产品詳情详细信息", produces = "application/json", consumes = "application/json")
    @ApiImplicitParam(name = "productDetailId", value = "产品詳情ID", required = true, paramType = "path", dataType = "Integer")
    @GetMapping(value = "/productdetails/{productDetailId}")
    public ProductDetail getProductDetail(@PathVariable("productDetailId") int productDetailId) {
        ProductDetail productDetail = productDetailMapper.selectByPrimaryKey(productDetailId);
        return productDetail;
    }

    @ApiOperation(value = "获取产品詳情列表信息", notes = "获取所有产品詳情的详细信息", produces = "application/json", consumes = "application/json")
    @GetMapping(value = "/productdetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "获取的页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productId", value = "所属产品id", required = false, paramType = "query", dataType = "int")

    })
    public PageInfo<ProductDetail> getProducts(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "productId", defaultValue = "-1") int productId) {
        PageHelper.startPage(pageNum, pageSize);
        ProductDetailExample productDetailExample = new ProductDetailExample();
        if (productId > -1) {
            ProductDetailExample.Criteria criteria = productDetailExample.createCriteria();
            criteria.andProductIdEqualTo(productId);
        }
        ArrayList<ProductDetail> productDetails = (ArrayList<ProductDetail>) productDetailMapper.selectByExample(new ProductDetailExample());
        PageInfo<ProductDetail> appsPageInfo = new PageInfo<ProductDetail>(productDetails);
        return appsPageInfo;
    }


    @ApiOperation(value = "创建产品詳情", notes = "创建一个新的产品詳情", produces = "application/json", consumes = "application/json")
    @PostMapping(value = "/productdetail")
    public Resp saveProductDetail(@RequestBody @ApiParam(name = "产品詳情对象", value = "传入json格式;id会自动生成，不用输入", required = true) ProductDetail productDetail) {
        productDetailMapper.insert(productDetail);
        return new Resp(productDetail.getId());

    }

    @ApiOperation(value = "更新产品詳情", notes = "更新一个已存在的产品詳情", produces = "application/json", consumes = "application/json")
    @PutMapping(value = "/productdetails/{productDetailId}")
    @ApiImplicitParam(name = "productDetailId", value = "产品詳情ID", required = true, paramType = "path", dataType = "Integer")
    public int updateProductDetail(@RequestBody @ApiParam(name = "产品詳情对象", value = "传入json格式", required = true) ProductDetail productDetail, @PathVariable("productDetailId") int productDetailId) {
        ProductDetailExample ue = new ProductDetailExample();
        ue.createCriteria().andIdEqualTo(productDetailId);
        productDetail.setId(productDetailId);
        return productDetailMapper.updateByExample(productDetail, ue);
    }


}
