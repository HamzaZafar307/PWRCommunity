package org.example.dto;

import com.google.gson.Gson;

public class ProjectDTO {
    private Long id;
    private String projectName;
    private UserDTO user;
    private String repoLink;

    // Constructors

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getRepoLink() {
        return repoLink;
    }

    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    // Serialization to JSON
    public String toJson() {
        return new Gson().toJson(this);
    }

    // Deserialization from JSON
    public static ProjectDTO fromJson(String json) {
        return new Gson().fromJson(json, ProjectDTO.class);
    }
}
