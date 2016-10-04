package com.wallace.game.virtualtabletop

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Glen on 10/3/2016.
 */
@SpringBootApplication
open class App {
}

fun main(args:Array<String>) {
    SpringApplication.run(App::class.java, *args)
}