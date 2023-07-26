package io.github.janekkodowanie.ezML.chatgpt;

import java.util.ArrayList;
import java.util.List;

class Session {
    private List<Query> queries;

    public Session() {
        this.queries = new ArrayList<>();
    }

    void addQuery(String prompt, String response) {
        this.queries.add(new Query(prompt, response));
    }

    public static class Query {
        String prompt;
        String response;

        public Query(String prompt, String response) {
            this.prompt = prompt;
            this.response = response;
        }

        public String getPrompt() {
            return prompt;
        }

        public String getResponse() {
            return response;
        }

    }

    public List<Query> getQueries() {
        return queries;
    }

}
