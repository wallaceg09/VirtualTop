package com.wallace.game.virtualtabletop.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by Glen on 10/3/2016.
 */
@Controller
@RequestMapping(value = "/fuck", method = arrayOf(RequestMethod.GET))
class WelcomeController {

    @RequestMapping(value = "/my/life", method = arrayOf(RequestMethod.GET))
    fun index() = "index"
}