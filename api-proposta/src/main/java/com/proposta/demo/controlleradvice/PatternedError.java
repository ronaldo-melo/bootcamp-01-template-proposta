package com.proposta.demo.controlleradvice;

import java.util.Collection;

public class PatternedError {

    private Collection<String> messages;

    public PatternedError(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }

    public void setMessages(Collection<String> messages) {
        this.messages = messages;
    }
}
