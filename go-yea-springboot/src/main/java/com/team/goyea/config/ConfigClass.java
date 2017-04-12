package com.team.goyea.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.yea.shiro.web.interceptor.ShiroInterceptor;
import com.yea.web.jsonbody.JsonReturnHandler;

@Configuration
@ImportResource(locations={"classpath:spring-bean.xml"})
public class ConfigClass extends WebMvcConfigurationSupport{

	@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setOrder(1);
        filterRegistration.setAsyncSupported(true);
        return filterRegistration;
    }
	
	@Bean
	@Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		adapter.getMessageConverters().add(jacksonConverter);
		
		adapter.getCustomReturnValueHandlers().add(new JsonReturnHandler());
		return adapter;
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		InterceptorRegistration ir = registry.addInterceptor(new ShiroInterceptor());
		// 配置拦截的路径
		ir.addPathPatterns("/**");

		// 还可以注册其它的拦截器
	}
}
