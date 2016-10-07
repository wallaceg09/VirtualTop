package com.wallace.game.virtualtabletop.database.memory

import com.wallace.game.virtualtabletop.model.GameMap
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileReader
import java.util.*
import javax.annotation.PostConstruct

/**
 * Created by Glen on 10/4/2016.
 */
@Component
class GameMapDatabaseMemory {
    private val maps = mutableListOf<GameMap>()

    @PostConstruct
    private fun init() {
        val file = File(javaClass.classLoader.getResource("static/kenny_debug.json").file)

        val reader = FileReader(file)
        val lines = reader.readLines()
        reader.close()
        val json = lines.joinToString(separator = " ")

        maps.add(GameMap(UUID.fromString("242eceff-3ce0-4e23-9183-a63de7cbf66e"), json, "debug_map", mutableMapOf(Pair("roguelikeSheet_transparent", "test"))))
    }

    fun list() = maps

    fun getByName(name: String) = maps.all { it.name == name }

    fun getById(id: UUID) = maps.firstOrNull { it.id == id }

    fun getById(id: String) = maps.firstOrNull { it.id == UUID.fromString(id) }

    fun add(map: GameMap): Boolean {
        maps.firstOrNull {it.id == map.id}?.let{
            return false
        }

        maps.add(map)
        return true
    }
}