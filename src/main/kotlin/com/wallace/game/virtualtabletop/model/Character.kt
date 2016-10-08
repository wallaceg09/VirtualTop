package com.wallace.game.virtualtabletop.model

import java.util.*

/**
 * Created by work on 10/7/2016.
 */
class Character() {
    lateinit var id: UUID
    lateinit var name: String

    var x: Int = 0
    var y: Int = 0

    var sprite: Int = 0

    constructor(id: UUID, name: String, x: Int, y: Int, sprite: Int): this() {
        this.id = id
        this.name = name

        this.x = x
        this.y = y

        this.sprite = sprite
    }
}