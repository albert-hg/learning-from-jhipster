package com.albert.management.configuration;

// import com.albert.management.configuration.springfoxPlugin.SwaggerPageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

  public SwaggerConfiguration() {}

  @Bean
  public Docket swaggerSetting() {
    return new Docket(DocumentationType.OAS_30)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build()
      .ignoredParameterTypes(Pageable.class)
      // .directModelSubstitute(Pageable.class, SwaggerPageable.class)
      // ↑
      // 你可以使用重新定義SwaggerPageable的方式來測試看看文章中所述的效果，但要記得要把PageableParameterPlugin的Configuration給關閉。
      ;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Management System Documentation")
      .description("learning Spring Boot")
      .contact(new Contact("Albert", "#", "alberthuang@gmail.com"))
      .version("0.0")
      .build();
  }
}
