package site.greenwave.config;

import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
}
