package com.chatApp.chat_app_backend.repositories;

import com.chatApp.chat_app_backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

    public Room findByRoomId(String roomId);
}
