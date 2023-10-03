package io.github.janekkodowanie.ezML.chatgpt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public
class ChatGPTRequest {

    private String model;
    private List<Message> messages;
    private boolean stream;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.stream = true;
    }
}
