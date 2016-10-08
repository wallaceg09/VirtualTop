package com.wallace.game.virtualtabletop

import com.wallace.game.virtualtabletop.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Glen on 10/3/2016.
 */
@SpringBootApplication
open class App {
    @Autowired
    lateinit var characterService: CharacterService

}

fun main(args:Array<String>) {
    SpringApplication.run(App::class.java, *args)
}