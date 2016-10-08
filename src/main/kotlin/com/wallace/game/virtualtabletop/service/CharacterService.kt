package com.wallace.game.virtualtabletop.service

import com.wallace.game.virtualtabletop.database.memory.CharacterDatabaseMemory
import com.wallace.game.virtualtabletop.message.Login
import com.wallace.game.virtualtabletop.model.Character
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by work on 10/7/2016.
 */
@Service
@Controller //TODO: Quick hack...
class CharacterService () : Runnable{
    private lateinit var database: CharacterDatabaseMemory

    private lateinit var clientService: ClientService

    private var executor: ScheduledExecutorService

    private var activeCharacters: MutableList<Character>

    init {
        activeCharacters = mutableListOf()

        executor = Executors.newScheduledThreadPool(1)
        executor.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS)
    }

    @Autowired
    constructor(database: CharacterDatabaseMemory, clientService: ClientService) : this(){
        this.database = database
        this.clientService = clientService
    }

    override fun run() {
//        heartbeat()
        println("TODO: Update players...")
    }

    @MessageMapping("/login")
    fun login(login: Login) {
        val character = database.getById(login.userID)
        character?.let {
            activeCharacters.add(it)
            clientService?.sendMessage("/character/${it.id}", character)
        }

    }

    private fun heartbeat() {
        println("TICK")
//        for
//        clientService?.sendMessage("/topic/heartbeat", "tick")
    }
}