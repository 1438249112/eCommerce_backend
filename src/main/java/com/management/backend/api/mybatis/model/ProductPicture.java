package com.management.backend.api.mybatis.model;

public class ProductPicture {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_picture.id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_picture.product_id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    private Integer productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_picture.picture_location
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    private String pictureLocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_picture.picture
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    private byte[] picture;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_picture.id
     *
     * @return the value of product_picture.id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_picture.id
     *
     * @param id the value for product_picture.id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_picture.product_id
     *
     * @return the value of product_picture.product_id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_picture.product_id
     *
     * @param productId the value for product_picture.product_id
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_picture.picture_location
     *
     * @return the value of product_picture.picture_location
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public String getPictureLocation() {
        return pictureLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_picture.picture_location
     *
     * @param pictureLocation the value for product_picture.picture_location
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation == null ? null : pictureLocation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_picture.picture
     *
     * @return the value of product_picture.picture
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_picture.picture
     *
     * @param picture the value for product_picture.picture
     *
     * @mbggenerated Sun Jun 09 21:58:57 CST 2019
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}