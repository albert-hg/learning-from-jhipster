package com.albert.management.configuration.springfoxPlugin;

import com.albert.management.configuration.SwaggerConfiguration;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterStyle;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@AutoConfigureAfter(SwaggerConfiguration.class)
@ConditionalOnBean(Docket.class)
public class PageableParameterPlugin implements OperationBuilderPlugin {
  /* 
  reference: https://github.com/jhipster/jhipster/blob/main/jhipster-framework/src/main/java/io/github/jhipster/config/apidoc/PageableParameterBuilderPlugin.java
  */

  private final TypeResolver resolver;

  @Autowired
  public PageableParameterPlugin(TypeResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  public boolean supports(DocumentationType arg0) {
    return true;
  }

  @Override
  public void apply(OperationContext context) {
    List<ResolvedMethodParameter> methodParameters = context.getParameters();
    ResolvedType pageableType = resolver.resolve(Pageable.class);
    List<RequestParameter> parameters = new ArrayList<>();
    for (ResolvedMethodParameter methodParameter : methodParameters) {
      ResolvedType resolvedType = methodParameter.getParameterType();
      if (pageableType.equals(resolvedType)) {
        parameters.add(createPageParameter());
        parameters.add(createSizeParameter());
        parameters.add(createSortParameter());
        context.operationBuilder().requestParameters(parameters);
      }
    }
  }

  private RequestParameter createPageParameter() {
    return new RequestParameterBuilder()
      .name("page")
      .in(ParameterType.QUERY)
      .query(q -> q.model(m -> m.scalarModel(ScalarType.INTEGER)))
      .description("Results page you want to retrieve (0..N)")
      .build();
  }

  private RequestParameter createSizeParameter() {
    return new RequestParameterBuilder()
      .name("size")
      .in(ParameterType.QUERY)
      .query(q -> q.model(m -> m.scalarModel(ScalarType.INTEGER)))
      .description("Number of records per page")
      .build();
  }

  protected RequestParameter createSortParameter() {
    String sortDescription =
      "Sorting criteria in the format: property(,asc|desc). " +
      "Default sort order is ascending. " +
      "Multiple sort criteria are supported.";
    return new RequestParameterBuilder()
      .name("sort")
      .in(ParameterType.QUERY)
      .query(q -> q.explode(true).style(ParameterStyle.PIPEDELIMITED).model(m -> m.collectionModel(cm -> cm.model(m2 -> m2.scalarModel(ScalarType.STRING)))))
      // .query(q -> q.model(m -> m.collectionModel(cm -> cm.model(m2 -> m2.scalarModel(ScalarType.STRING)))))
      .description(sortDescription)
      .build();
  }
}
