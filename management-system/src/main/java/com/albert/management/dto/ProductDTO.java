package com.albert.management.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProductDTO {
    
    @ApiModelProperty(value = "產品的ID", example = "1001", required = true)
    private Long id;
    @ApiModelProperty(value = "產品的名稱", example = "apple", required = true)
    private String name;
    @ApiModelProperty(value = "產品的剩餘數量")
    private Integer remain;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Integer remain) {
        this.id = id;
        this.name = name;
        this.remain = remain;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemain() {
        return this.remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "{" +
            " \"id\":" + getId() + "" +
            ", \"name\":\"" + getName() + "\"" +
            ", \"remain\":" + getRemain() + "" +
            "}";
    }
}
