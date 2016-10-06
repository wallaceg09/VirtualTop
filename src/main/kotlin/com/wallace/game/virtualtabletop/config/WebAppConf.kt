package com.wallace.game.virtualtabletop.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import javax.servlet.ServletRegistration

/**
 * Created by Glen on 10/3/2016.
 */
//TODO: Remove
class WebAppConf : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<out Class<*>> {
        return arrayOf()
    }

    override fun getServletConfigClasses(): Array<out Class<*>> {
        return arrayOf()
    }

    override fun getServletMappings(): Array<out String> {
        return arrayOf("/")
    }

    override fun customizeRegistration(registration: ServletRegistration.Dynamic?) {
        super.customizeRegistration(registration)
    }
}