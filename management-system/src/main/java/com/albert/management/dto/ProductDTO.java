package com.albert.management.dto;

public class ProductDTO {
    private Long id;        // 產品的ID
    private String name;    // 產品的名稱
    private Integer remain; // 產品的剩餘數量

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
