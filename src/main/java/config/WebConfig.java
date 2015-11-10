package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


import java.util.*;

/***
 * Spring Ｊava Config File (新版的Spring 可使用  java 配置)
 */

@Configuration
@EnableWebMvc                           //same as  <mvc:annotation-driven/>
@ComponentScan({"eHanlin.aop","eHanlin.*"})  //掃描package裡的Class，並對spring物件執行注入
public class WebConfig extends WebMvcConfigurerAdapter{

    /* 用來過濾 沒有權限的使用者，此處需要克制一個攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }*/

    //same as <mvc:resources location="/resources/" mapping="/resources/**"/>
    //靜態資源處理，讓 DispatccherServlet不要處理靜態路徑。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    //same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML)
                  .mediaType("html", MediaType.TEXT_HTML)
                  .mediaType("json", MediaType.APPLICATION_JSON)
                   //.mediaType("xml", MediaType.APPLICATION_XML)
                  .favorPathExtension(false)
                  .ignoreAcceptHeader(false);
    }


    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jsonViewResolver());
        
        // resolvers.add(jaxb2MarshallingXmlViewResolver());
        // resolvers.add(jspViewResolver());
        // resolvers.add(pdfViewResolver());
        // resolvers.add(excelViewResolver());
        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    //handler json
    @Bean
    public ViewResolver jsonViewResolver() {
        return new ViewResolver() {
            @Override
            public View resolveViewName(String s, Locale locale) throws Exception {
                MappingJackson2JsonView view = new MappingJackson2JsonView();
                view.setPrettyPrint(true);      // Lay the JSON out to be nicely readable
                return view;
            }
        };
    }


    /*
    @Bean
    public ViewResolver jaxb2MarshallingXmlViewResolver() {
        new Jabx
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Pizza.class);
        return new Jaxb2MarshallingXmlViewResolver(marshaller);
    }*/
   /*
    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }*/

    /*
    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }*/

    /*
    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/


    /*
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/

}
