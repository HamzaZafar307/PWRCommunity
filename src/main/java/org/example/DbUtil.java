package org.example;

import org.example.dto.ProjectDTO;
import org.example.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }

    public static List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setTelegramURL(resultSet.getString("telegram_url"));
                user.setBio(resultSet.getString("bio"));
                user.setWebsiteURL(resultSet.getString("website_url"));
                user.setTwitterURL(resultSet.getString("twitter_url"));
                user.setLinkedinURL(resultSet.getString("linkedin_url"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void addUser(UserDTO user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, telegram_url, bio, website_url, twitter_url, linkedin_url) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getTelegramURL());
            preparedStatement.setString(3, user.getBio());
            preparedStatement.setString(4, user.getWebsiteURL());
            preparedStatement.setString(5, user.getTwitterURL());
            preparedStatement.setString(6, user.getLinkedinURL());

            preparedStatement.executeUpdate();

            // Retrieve the generated user ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projects = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM projects")) {
            while (resultSet.next()) {
                ProjectDTO project = new ProjectDTO();
                project.setId(resultSet.getLong("id"));
                project.setProjectName(resultSet.getString("project_name"));
                UserDTO user = getUserById(resultSet.getLong("user_id"));
                project.setUser(user);
                project.setRepoLink(resultSet.getString("repo_link"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static void addProject(ProjectDTO project) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO projects (project_name, user_id, repo_link) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setLong(2, project.getUser().getUserId());
            preparedStatement.setString(3, project.getRepoLink());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserDTO getUserById(Long userId) {
        UserDTO user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?")) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setTelegramURL(resultSet.getString("telegram_url"));
                    user.setBio(resultSet.getString("bio"));
                    user.setWebsiteURL(resultSet.getString("website_url"));
                    user.setTwitterURL(resultSet.getString("twitter_url"));
                    user.setLinkedinURL(resultSet.getString("linkedin_url"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void storeUserData(String username, String bio, String websiteUrl, String twitterUrl, String linkedinUrl, String telegramProfileUrl) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create the SQL insert query
            String insertQuery = "INSERT INTO thirdPartyUser" +  " (username, bio, website_url, twitter_url, linkedin_url, telegram_profile_url) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set parameters for the insert query
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, bio);
                preparedStatement.setString(3, websiteUrl);
                preparedStatement.setString(4, twitterUrl);
                preparedStatement.setString(5, linkedinUrl);
                preparedStatement.setString(6, telegramProfileUrl);

                // Execute the insert query
                preparedStatement.executeUpdate();

                System.out.println("User data stored in the database successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database-related exceptions
        }
    }


}
