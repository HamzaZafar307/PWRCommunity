package org.example.dto;
import com.google.gson.Gson;
import com.google.gson.Gson;

public class UserDTO {
    private Long userId;
    private String username;
    private String telegramURL;
    private String bio;
    private String websiteURL;
    private String twitterURL;
    private String linkedinURL;

    // Constructors

    // Getter and Setter methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelegramURL() {
        return telegramURL;
    }

    public void setTelegramURL(String telegramURL) {
        this.telegramURL = telegramURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    public String getLinkedinURL() {
        return linkedinURL;
    }

    public void setLinkedinURL(String linkedinURL) {
        this.linkedinURL = linkedinURL;
    }

    // Serialization to JSON
    public String toJson() {
        return new Gson().toJson(this);
    }

    // Deserialization from JSON
    public static UserDTO fromJson(String json) {
        return new Gson().fromJson(json, UserDTO.class);
    }
}
