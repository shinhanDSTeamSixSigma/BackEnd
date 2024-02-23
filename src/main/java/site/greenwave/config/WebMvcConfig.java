package site.greenwave.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import site.greenwave.farm.controller.formatter.LocalDateFormatter;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	@Value("${filesave.location}")
	private String path;


	private final UserInfoArgumentResolver userInfoArgumentResolver;
	@Override
	public void  addResourceHandlers(ResourceHandlerRegistry registry) {
		String resourceLocationPath = "file:///"+path;
		registry.addResourceHandler("img/**")
				.addResourceLocations(resourceLocationPath)
				.setCachePeriod(31556926);
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userInfoArgumentResolver);
	}

    // ModelMapper 설정
    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }



    // LocalDateFormatter 설정
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
    }
/*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(30).allowedHeaders("Authorization", "Cache-Control", "Content-Type");
//		addMapping - CORS를 적용할 url의 패턴을 정의 (/** 로 모든 패턴을 가능하게 함)
//		allowedOrigins - 허용할 origin을 정의 (* 로 모든 origin을 허용, 여러개도 지정가능)
//		allowedMethods - HTTP Method를 지정 (* 로 모든 Method를 허용)
//		maxAge - 원하는 시간만큼 request를 cashing함

    }*/

}
