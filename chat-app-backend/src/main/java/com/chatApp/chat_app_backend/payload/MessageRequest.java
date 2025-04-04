package com.chatApp.chat_app_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MessageRequest {

    private String roomId;
    private String content;
    private String sender;
}
