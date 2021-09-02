package com.albert.management.configuration.springfoxPlugin;

import java.util.List;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SwaggerPageable {
  
    @ApiModelProperty(value = "Number of records per page", example = "0")
    @Nullable
    private Integer size;

    @ApiModelProperty(value = "Results page you want to retrieve (0..N)", example = "0")
    @Nullable
    private Integer page;

    @ApiModelProperty(dataType = "query", value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    @Nullable
    // 透過directModelSubstitute()的方法重新設定，會讓List<String> sort一直被視為 "style":"pipeDelimited", 且 "explode":false
    // 這裡也沒辦法設定explode的值，算是比較知名的Springfox的Issue。
    private List<String> sort;

    public Integer getSize() {
      return size;
    }
    
    public Integer getPage() {
      return page;
    }

    public List<String> getSort() {
      return sort;
    }
}
