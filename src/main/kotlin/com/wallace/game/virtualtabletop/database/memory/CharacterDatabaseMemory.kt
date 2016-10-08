package com.wallace.game.virtualtabletop.database.memory

import com.wallace.game.virtualtabletop.model.Character
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

/**
 * Created by work on 10/7/2016.
 */
@Component
class CharacterDatabaseMemory {
    private val characters = mutableListOf<Character>()

    @PostConstruct
    private fun init() {
        characters.add(Character(UUID.fromString("a9729c92-a0ba-4d64-8b01-8022dc5eb0d0"), "Glen", 0, 0, 0))
    }

    fun list() = characters

    fun getByName(name: String) = characters.all { it.name == name }

    fun getById(id: UUID) = characters.firstOrNull { it.id == id }

    fun getById(id: String) = characters.firstOrNull { it.id == UUID.fromString(id) }

    fun add(character: Character): Boolean {
        characters.firstOrNull {it.id == character.id}?.let{
            return false
        }

        characters.add(character)
        return true
    }
}