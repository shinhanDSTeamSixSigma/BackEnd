package site.greenwave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Value("${filesave.location}")
	private String path;
	
	@Override
	public void  addResourceHandlers(ResourceHandlerRegistry registry) {
		String resourceLocationPath = "file:///"+path;
		registry.addResourceHandler("img/**")
				.addResourceLocations(resourceLocationPath)
				.setCachePeriod(31556926);
	}
}
