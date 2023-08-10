package io.github.janekkodowanie.ezML.chatgpt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String role;

    /* prompt */
    private String content;

}
