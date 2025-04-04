package com.chatApp.chat_app_backend.controllers;

import com.chatApp.chat_app_backend.entities.Message;
import com.chatApp.chat_app_backend.entities.Room;
import com.chatApp.chat_app_backend.payload.MessageRequest;
import com.chatApp.chat_app_backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

    @Autowired
    private RoomRepository roomRepository;

    //send and receive messages

    // client will have to hit /app/sendMessage/roomId
    @MessageMapping("/sendMessage/{roomId}")

    //messages published to this url. client will subscribe to this
    @SendTo("/topic/room/{roomId}")

    //@DestinationVariable in WebSocket is similar to @PathVariable in REST
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request) {

        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();

        if(room != null){
            message.setContent(request.getContent());
            message.setSender(request.getSender());
            message.setTimeStamp(LocalDateTime.now());
            room.getMessages().add(message);
            roomRepository.save(room);
        }
        else{
            throw new RuntimeException("Room not found");
        }

        return message;
    }
}
