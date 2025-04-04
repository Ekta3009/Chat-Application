package com.chatApp.chat_app_backend.controllers;

import com.chatApp.chat_app_backend.entities.Message;
import com.chatApp.chat_app_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    //get room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable String roomId){
        return roomService.getRoom(roomId);
    }

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        return roomService.createRoom(roomId);
    }

    //get messages
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId){
        return roomService.getMessages(roomId);
    }
}