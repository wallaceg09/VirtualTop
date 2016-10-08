package com.wallace.game.virtualtabletop.service

import com.wallace.game.virtualtabletop.database.memory.CharacterDatabaseMemory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by work on 10/7/2016.
 */
@Service
class CharacterService : Runnable{
    @Autowired
    private lateinit var database: CharacterDatabaseMemory

    private lateinit var template: SimpMessagingTemplate

    private val executor: ScheduledExecutorService

    @Autowired
    constructor(template: SimpMessagingTemplate) {
        this.template = template
        executor = Executors.newScheduledThreadPool(1)
        executor.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS)
    }

    override fun run() {
        heartbeat()
        println("TODO: Update players...")
    }

    private fun heartbeat() {
        println("TICK")
        template.convertAndSend("/topic/heartbeat", "tick")
    }
}