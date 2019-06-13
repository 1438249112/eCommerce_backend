package com.management.backend.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.backend.api.mybatis.mapper.ProductPictureMapper;
import com.management.backend.api.mybatis.model.ProductPicture;
import com.management.backend.api.mybatis.model.ProductPictureExample;
import com.management.backend.api.util.Resp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@Api(value = "productPicture operations",tags = {"productPicture service"})
public class ProductPictureService {

    @Autowired
    private ProductPictureMapper productPictureMapper;


    @ApiOperation(value="获取产品图片详细信息", notes="根据产品图片的id来获取产品图片详细信息",produces="application/json",consumes = "application/json")
    @ApiImplicitParam(name = "productPictureId", value = "产品图片ID", required = true,paramType = "path", dataType = "Integer")
    @GetMapping(value = "/productpictures/{productPictureId}")
    public ProductPicture getProductPicture(@PathVariable("productPictureId") int productPictureId){
        ProductPicture productPicture = productPictureMapper.selectByPrimaryKey(productPictureId);
        return productPicture;
    }

    @ApiOperation(value="获取产品图片列表信息", notes="获取所有产品图片的详细信息",produces="application/json",consumes = "application/json")
    @GetMapping(value = "/productpictures")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "获取的页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productId", value = "所属产品id", required = false, paramType = "query", dataType = "int")

    })
    public PageInfo<ProductPicture> getProducts(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "productId", defaultValue = "-1") int productId) {
        PageHelper.startPage(pageNum,pageSize);
        ProductPictureExample productPictureExample = new ProductPictureExample();
        if (productId > -1) {
            ProductPictureExample.Criteria criteria = productPictureExample.createCriteria();
            criteria.andProductIdEqualTo(productId);
        }
        ArrayList<ProductPicture> productPictures = (ArrayList<ProductPicture>) productPictureMapper.selectByExample(new ProductPictureExample());
        PageInfo<ProductPicture> appsPageInfo = new PageInfo<ProductPicture>(productPictures);
        return appsPageInfo;
    }


    @ApiOperation(value="创建产品图片", notes="创建一个新的产品图片",produces="application/json",consumes = "application/json")
    @PostMapping(value = "/productpicture")
    public Resp saveProductPicture(@RequestParam(value = "file", required = true) MultipartFile file ,
                                   @RequestParam(value = "productId", required = true) Integer productId ) throws IOException {
        ProductPicture productPicture = new ProductPicture();
        productPicture.setProductId(productId);
        String fileLocation =  "pic"+"/"+UUID.randomUUID().toString().toLowerCase()+"/"+file.getOriginalFilename();
        productPicture.setPictureLocation(fileLocation);
        productPicture.setPicture(file.getBytes());
        productPictureMapper.insert(productPicture);
        return   new Resp(productPicture.getId());

    }

    @ApiOperation(value="更新产品图片", notes="更新一个已存在的产品图片",produces="application/json",consumes = "application/json")
    @PutMapping(value = "/productpictures/{productPictureId}")
    @ApiImplicitParam(name = "productPictureId", value = "产品图片ID", required = true,paramType = "path", dataType = "Integer")
    public int updateProductPicture(@RequestBody @ApiParam(name="产品图片对象",value="传入json格式",required=true) ProductPicture productPicture,@PathVariable("productPictureId") int productPictureId){
        ProductPictureExample ue = new ProductPictureExample();
        ue.createCriteria().andIdEqualTo(productPictureId);
        productPicture.setId(productPictureId);
        return   productPictureMapper.updateByExample(productPicture,ue);
    }



}
