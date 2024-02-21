package site.greenwave.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final UserInfoArgumentResolver userInfoArgumentResolver;
	@Override
	public void  addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("img/**")
				.addResourceLocations("file:///C:/greenwave/img/")//TestLocal Window
//				.addResourceLocations("file:///C:/greenwave/img/")//TestLocal MacBook
//				.addResourceLocations("file:///home/img/")//Deploy
				.setCachePeriod(31556926);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userInfoArgumentResolver);
	}

}
