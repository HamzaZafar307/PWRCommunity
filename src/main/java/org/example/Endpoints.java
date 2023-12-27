package org.example;
import com.google.gson.Gson;
import org.example.DbUtil;
import org.example.dto.ProjectDTO;
import org.example.dto.UserDTO;

import java.util.List;

import static spark.Spark.*;
public class Endpoints {

    public static void main(String[] args) {
        port(4567);

        // Get all users endpoint
        get("/users", (request, response) -> {
            List<UserDTO> users = DbUtil.getAllUsers();
            return new Gson().toJson(users);
        });

        // Add user endpoint
        post("/users", (request, response) -> {
            UserDTO user = new Gson().fromJson(request.body(), UserDTO.class);
            DbUtil.addUser(user);
            response.status(201); // Created
            return "User added successfully";
        });

        // Get all projects endpoint
        get("/projects", (request, response) -> {
            List<ProjectDTO> projects = DbUtil.getAllProjects();
            return new Gson().toJson(projects);
        });

        //  project endpoint
        post("/projects", (request, response) -> {
            ProjectDTO project = new Gson().fromJson(request.body(), ProjectDTO.class);
            DbUtil.addProject(project);
            response.status(201); // Created
            return "Project added successfully";
        });


    }
}
