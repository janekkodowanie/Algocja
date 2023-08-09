package io.github.janekkodowanie.ezML.chatgpt;

import lombok.AllArgsConstructor;

import java.util.List;

class Chunk {

    private String id;
    private String object;

    private int created;
    private String model;

    private List<Choice> choices;

    public static class Choice {
        private int index;
        private Delta delta;
        private String finishReason;

        public static class Delta {
            private String role;
            private String content;

            private FunctionCall functionCall;

            public static class FunctionCall {
                private String name;
                private List<String> args;
            }

            public String getContent() {
                return content;
            }
        }

        public Delta getDelta() {
            return delta;
        }

        String getFinishReason() {
            return finishReason;
        }
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
