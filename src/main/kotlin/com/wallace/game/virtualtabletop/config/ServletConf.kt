package com.wallace.game.virtualtabletop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.multipart.support.StandardServletMultipartResolver
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Created by Glen on 10/3/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.wallace.game.virtualtabletop.controller")
//TODO: Remove
open class ServletConf : WebMvcConfigurerAdapter() {
    @Bean
    open fun multipartResolver(): MultipartResolver = StandardServletMultipartResolver()

    @Bean
    open fun internalViewResolver() : ViewResolver {
        var resolver = InternalResourceViewResolver()
//        resolver.setPrefix("/resources/")
        resolver.setSuffix(".html")

        return resolver
    }
}