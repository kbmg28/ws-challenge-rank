package org.adv.mj.wschallengerank.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class StaticAssetsConfig implements WebMvcConfigurer {
//    private final String baseUrl;
//
//    public StaticAssetsConfig(String baseUrl) {
//        this.baseUrl = baseUrl;
//    }
//
//    @Override
//    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
//        this.resourceHandlersForCache(registry);
//        this.resourceForHandlersSwaggerUI(registry);
//    }

    private void resourceHandlersForCache(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePrivate().mustRevalidate());

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/")
            .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().mustRevalidate());

        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/index.html").setCachePeriod(0);
    }

    private void resourceForHandlersSwaggerUI(ResourceHandlerRegistry registry) {
//        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:" + "/swagger-ui/index.html");
    }
}
