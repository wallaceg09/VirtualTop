package com.wallace.game.virtualtabletop.service

import com.wallace.game.virtualtabletop.database.memory.CharacterDatabaseMemory

import org.springframework.beans.factory.annotation.Autowired
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

    private val executor: ScheduledExecutorService

    constructor() {
        executor = Executors.newScheduledThreadPool(1)
        executor.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS)
    }

    override fun run() {
        println("TODO: Update players...")
    }
}