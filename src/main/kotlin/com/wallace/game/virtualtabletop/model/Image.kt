package com.wallace.game.virtualtabletop.model

import java.net.URL
import java.util.*

/**
 * Created by Glen on 10/3/2016.
 */
class Image() {
    lateinit var id: UUID
    lateinit var name: String
    lateinit var location: URL

    constructor(
            id: UUID,
            name: String,
            location: URL
    ) : this(){
        this.id = id
        this.name = name
        this.location = location
    }

    constructor(
            id: UUID,
            name: String,
            location: String
    ): this(id, name, URL(location))
}