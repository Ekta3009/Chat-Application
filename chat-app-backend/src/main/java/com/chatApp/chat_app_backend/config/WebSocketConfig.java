package com.chatApp.chat_app_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //the connection will be established on "/chat" url
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:5173")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //the server will publish messages only when the url has prefix "/topic"
        //for eg: "/topic/messages" - yes; "/test/messages" - no
        config.enableSimpleBroker("/topic");

        //the service urls will begin with "/app"
        //the client (frontend) has to hit "/app/chat" but in the service controller we define "/chat" only
        //the "/app" prefix will automatically come from here
        config.setApplicationDestinationPrefixes("/app");
    }
}
