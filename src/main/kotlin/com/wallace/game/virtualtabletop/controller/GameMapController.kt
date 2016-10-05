package com.wallace.game.virtualtabletop.controller

import com.wallace.game.virtualtabletop.database.memory.GameMapDatabaseMemory
import com.wallace.game.virtualtabletop.model.GameMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

/**
 * Created by Glen on 10/4/2016.
 */
@RequestMapping(value = "/map")
@RestController
class GameMapController {
    @Autowired
    private lateinit var database: GameMapDatabaseMemory

    @RequestMapping(value = "/get", method = arrayOf(RequestMethod.GET))
    fun get(@PathParam(value = "id") id: String) = database.getById(id)

    @RequestMapping(value = "/search", method = arrayOf(RequestMethod.GET))
    fun search(@PathParam(value = "name") name: String) = database.getByName(name)

    @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
    fun list() = database.list()

    @RequestMapping(value = "/new", method = arrayOf(RequestMethod.POST))
    fun new(@RequestBody map: GameMap) =
            if (database.add(map)) map
            else throw DuplicateItemException()

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate item.") //TODO: Move to own file
    class DuplicateItemException: RuntimeException() {}
}