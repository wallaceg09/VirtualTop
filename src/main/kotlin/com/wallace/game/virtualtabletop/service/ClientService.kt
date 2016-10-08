package com.wallace.game.virtualtabletop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by work on 10/8/2016.
 */
@Service
class ClientService (): Runnable{
    private lateinit var template: SimpMessagingTemplate

    private val executorService: ScheduledExecutorService

    init {
        executorService = Executors.newScheduledThreadPool(1)
        executorService.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS)
    }

    @Autowired
    constructor(template: SimpMessagingTemplate) : this(){
        this.template = template
    }

    fun sendMessage(topic: String, message: Any) {
        template.convertAndSend(topic, message)
    }

    override fun run() {
//        sendMessage("/topic/client", "Client Tick!")
        // TODO: Something...
    }
}