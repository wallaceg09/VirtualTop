package com.wallace.game.virtualtabletop.controller.rest

import com.wallace.game.virtualtabletop.database.memory.ImageDatabaseMemory
import com.wallace.game.virtualtabletop.model.Image
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

/**
 * Created by Glen on 10/3/2016.
 */
@RequestMapping(value = "/image")
@RestController
class ImageController {
    @Autowired
    private lateinit var database: ImageDatabaseMemory

    @RequestMapping(value = "/get", method = arrayOf(RequestMethod.GET))
    fun get(@PathParam(value = "id") id: String) = database.getById(id)

    @RequestMapping(value = "/search", method = arrayOf(RequestMethod.GET))
    fun search(@PathParam(value = "name") name: String) = database.getByName(name)

    @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
    fun list() = database.list()

    @RequestMapping(value = "/new", method = arrayOf(RequestMethod.POST))
    fun new(@RequestBody image: Image) =
        if (database.add(image)) image
        else throw DuplicateItemException()

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate item.")
    class DuplicateItemException: RuntimeException() {}
}