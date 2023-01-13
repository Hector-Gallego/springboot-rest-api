package dev.hectorgallego.springbootrestapi.events;

import dev.hectorgallego.springbootrestapi.model.User;

public class RegisterUserEvent {

    private User user;
    private String url;
    public RegisterUserEvent(User user, String url) {
        this.user = user;
        this.url = url;
    }

    public RegisterUserEvent(){

    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
