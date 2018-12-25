package com.epam.lab.model;

import java.util.Objects;

public class Message {

    private String sender;
    private String subject;
    private String message;

    public static class MessageBuilder {
        private String sender;
        private String subject;
        private String message;

        public MessageBuilder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public MessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder setMessageText(String messageText) {
            this.message = messageText;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

    }

    private Message(MessageBuilder builder) {
        this.sender = builder.sender;
        this.subject = builder.subject;
        this.message = builder.message;
    }

    public String getSender() {
        return this.sender;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.sender == null) ? 0 : this.sender.hashCode());
        result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
        result = prime * result + ((this.subject == null) ? 0 : this.subject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(sender, message1.sender) &&
                Objects.equals(subject, message1.subject) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public String toString() {
        return "Message [sender=" + this.sender + ", subject=" + this.subject + ", message=" + this.message
                + "]\n";
    }
}
