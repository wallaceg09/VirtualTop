package com.wallace.game.virtualtabletop.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

/**
 * Created by work on 10/8/2016.
 */
@Configuration
@EnableWebSocketMessageBroker
open class WebsocketConfig : AbstractWebSocketMessageBrokerConfigurer() {

    override fun configureMessageBroker(registry: MessageBrokerRegistry?) {
        registry?.enableSimpleBroker("/topic")
        registry?.setApplicationDestinationPrefixes("/app")

    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry?) {
        registry?.addEndpoint("/ws")?.withSockJS()
    }

}