package com.company.MailerApi.Model;

public class Mail {
    private String Recipient;
    private String Message;
    private String Subject;

    public Mail(){

    }
    public Mail(String recipient, String message, String subject) {
        Recipient = recipient;
        Message = message;
        Subject = subject;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
