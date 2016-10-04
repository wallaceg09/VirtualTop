package com.wallace.game.virtualtabletop.database.memory

import com.wallace.game.virtualtabletop.model.Image
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

/**
 * Created by Glen on 10/3/2016.
 */
@Component
class ImageDatabaseMemory {
    private val images = mutableListOf<Image>()

    @PostConstruct
    private fun init() {
        images.add(Image(UUID.fromString("d6ca9416-3856-4449-a4c8-044d3aa8df90"), "test", "http://imgur.com/dAMla3A"))
    }

    fun list() = images

    fun getByName(name: String) = images.all { it.name == name }

    fun getById(id: UUID) = images.firstOrNull { it.id == id }

    fun getById(id: String) = images.firstOrNull { it.id == UUID.fromString(id) }

    fun add(image: Image): Boolean {
        images.firstOrNull {it.id == image.id}?.let{
            return false
        }

        images.add(image)
        return true
    }
}