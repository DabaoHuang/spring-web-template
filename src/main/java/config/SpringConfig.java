package config;


import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;


import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


import java.util.*;

/***
 * Spring Ｊava Config File (新版的Spring 可使用  java 配置)
 */

@Configuration
@EnableWebMvc                           //same as  <mvc:annotation-driven/>
@ComponentScan({"ocean.aop","ocean.*"})  //掃描package裡的Class，並對spring物件執行注入
public class SpringConfig extends WebMvcConfigurerAdapter{

    public Locale defaultLocale = Locale.TAIWAN;

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

    //載入環境設定檔
    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("environment.properties"));
        /* 多屬性檔設定方式
        ArrayList<Resource> list = new ArrayList<Resource>();
        list.add(new ClassPathResource("environment.properties"));
        list.add(new ClassPathResource("dbCollection.properties"));
        */
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public ServletContextTemplateResolver templateResolver(){
        ServletContextTemplateResolver resolver =  new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.addDialect(new LayoutDialect());
        return engine;
    }

    @Bean
    public ViewResolver viewResolver(){
        String [] patten = {"*"};
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(patten);
        viewResolver.setCache(false);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML)
                  .mediaType("html", MediaType.TEXT_HTML)
                  .mediaType("json", MediaType.APPLICATION_JSON)
                   //.mediaType("xml", MediaType.APPLICATION_XML);
                  .favorPathExtension(false)
                  .ignoreAcceptHeader(false);
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookiePath("/");
        localeResolver.setCookieName("lang");
        localeResolver.setCookieMaxAge(Integer.MAX_VALUE);
        localeResolver.setDefaultLocale(Locale.TAIWAN);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /*
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
    */
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
