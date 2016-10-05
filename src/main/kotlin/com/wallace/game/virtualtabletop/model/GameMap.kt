package com.wallace.game.virtualtabletop.model

import java.util.*

/**
 * Created by Glen on 10/4/2016.
 */
class GameMap() {
    lateinit var id: UUID
    lateinit var name: String
    lateinit var json: String
    var imageMap: MutableMap<String, String> = HashMap()

    constructor(
            id: UUID,
            json: String,
            name: String,
            imageMap: MutableMap<String, String>
    ): this() {
        this.id = id
        this.json = json
        this.name = name
        this.imageMap = imageMap
    }

}