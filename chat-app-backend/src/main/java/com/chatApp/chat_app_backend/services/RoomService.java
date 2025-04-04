package com.chatApp.chat_app_backend.services;

import com.chatApp.chat_app_backend.entities.Message;
import com.chatApp.chat_app_backend.entities.Room;
import com.chatApp.chat_app_backend.repositories.RoomRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public ResponseEntity<?> createRoom(String roomId) {
        if(roomRepository.findByRoomId(roomId) != null){
            return ResponseEntity.badRequest().body("Room already exists");
        }
        Room newRoom = new Room();
        newRoom.setRoomId(roomId);
        roomRepository.save(newRoom);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newRoom);
    }

    public ResponseEntity<?> getRoom(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("Room does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    public ResponseEntity<List<Message>> getMessages(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages = room.getMessages();
        return ResponseEntity.ok(messages);
    }
}
