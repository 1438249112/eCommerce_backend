package com.management.backend.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.backend.api.mybatis.mapper.ProductTypeMapper;
import com.management.backend.api.mybatis.model.ProductType;
import com.management.backend.api.mybatis.model.ProductTypeExample;
import com.management.backend.api.util.Resp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Api(value = "productType operations",tags = {"productType service"})
public class ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @ApiOperation(value="获取产品详细信息", notes="根据产品的id来获取产品详细信息",produces="application/json",consumes = "application/json")
    @ApiImplicitParam(name = "productTypeId", value = "产品ID", required = true,paramType = "path", dataType = "Integer")
    @GetMapping(value = "/producttypes/{productTypeId}")
    public ProductType getProductType(@PathVariable("productTypeId") int productTypeId){
        return productTypeMapper.selectByPrimaryKey(productTypeId);
    }

    @ApiOperation(value="获取产品列表信息", notes="获取所有产品的详细信息",produces="application/json",consumes = "application/json")
    @GetMapping(value = "/producttypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "获取的页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pid", value = "父类型id", required = false, paramType = "query", dataType = "Integer")
    })
    public PageInfo<ProductType> getProducts(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "pid", defaultValue = "-1") Integer pid) {
        PageHelper.startPage(pageNum,pageSize);
        ProductTypeExample productTypeExample = new ProductTypeExample();
        if (pid > -1) {
            ProductTypeExample.Criteria criteria = productTypeExample.createCriteria();
            criteria.andPidEqualTo(pid);
        }

        ArrayList<ProductType> ProductTypes = (ArrayList<ProductType>) productTypeMapper.selectByExample(productTypeExample);
        PageInfo<ProductType> appsPageInfo = new PageInfo<ProductType>(ProductTypes);
        return appsPageInfo;
    }


    @ApiOperation(value="创建产品", notes="创建一个新的产品",produces="application/json",consumes = "application/json")
    @PostMapping(value = "/producttype")
    public Resp saveProductType(@RequestBody @ApiParam(name="产品对象",value="传入json格式;id会自动生成，不用输入",required=true) ProductType productType){
           productTypeMapper.insert(productType);
        return   new Resp(productType.getId());
    }

    @ApiOperation(value="更新产品", notes="更新一个已存在的产品",produces="application/json",consumes = "application/json")
    @PutMapping(value = "/producttypes/{productTypeId}")
    @ApiImplicitParam(name = "productTypeId", value = "产品ID", required = true,paramType = "path", dataType = "Integer")
    public int updateProductType(@RequestBody @ApiParam(name="产品对象",value="传入json格式;可以忽略id字段",required=true) ProductType productType,@PathVariable("productTypeId") int productTypeId){
        ProductTypeExample ue = new ProductTypeExample();
        ue.createCriteria().andIdEqualTo(productTypeId);
        productType.setId(productTypeId);
        return   productTypeMapper.updateByExample(productType,ue);
    }



}
