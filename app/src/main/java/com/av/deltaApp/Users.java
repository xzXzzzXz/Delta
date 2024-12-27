package com.av.deltaApp;

public class Users {
    String mail, userName, password, userId, lastMessage, status;

    // Konstruktor sa svim argumentima
    public Users(String userId, String userName, String mail, String password, String status) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.status = status;
    }

    // Konstruktor bez argumenata (potreban za Firebase)
    public Users() {
        // Ovaj konstruktor je obavezan za Firebase
    }

    // Getter i setter metode
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
