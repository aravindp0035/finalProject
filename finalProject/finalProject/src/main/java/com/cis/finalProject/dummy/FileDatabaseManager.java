package com.cis.finalProject.dummy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FileDatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/blockchain_db";
    private static final String user = "root";
    private static final String password = "Aravind@123";

    // Method to store ML data file in the database
    public void storeFile(String hash, String modelName, String filePath, String reviewsFile) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             FileInputStream fis = new FileInputStream(filePath);
        		FileInputStream bin = new FileInputStream(reviewsFile);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ml_files (hash, model_name, file_data, reviews_file) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, hash);
            statement.setString(2, modelName);
            statement.setBinaryStream(3, fis);
            statement.setBinaryStream(4, bin);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve ML data file from the database
    public void retrieveFile(String fileName, String targetFilePath) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             FileOutputStream fos = new FileOutputStream(targetFilePath);
             PreparedStatement statement = connection.prepareStatement("SELECT file_data FROM ml_files WHERE file_name = ?")) {
            statement.setString(1, fileName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                InputStream inputStream = resultSet.getBinaryStream("file_data");
                byte[] buffer = new byte[1024];
                while (inputStream.read(buffer) > 0) {
                    fos.write(buffer);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
        public static void main(String[] args) {
        	
        
        	
        	        try {
        	            Class.forName("com.mysql.cj.jdbc.Driver");
        	        } catch (ClassNotFoundException e) {
        	            System.out.println("MySQL JDBC driver not found.");
        	            e.printStackTrace();
        	            return;
        	        }

        	        // Connection parameters
        	        String url = "jdbc:mysql://localhost:3306/blockchain_db";
        	        String user = "root";
        	        String password = "Aravind@123";

        	        // Attempt to establish a connection
        	        try (Connection connection = DriverManager.getConnection(url, user, password)) {
        	            // Connection successful
        	            System.out.println("Connected to the database.");
        	            FileDatabaseManager fileDatabaseManager = new FileDatabaseManager();

        	            // Store ML data file in the database
        	            String hash = ".";
        	            String fileName = "model5";
        	            String filePath = "C:/Users/aravi/eclipse-workspace/mastersProject/Blockchain1/Block5.txt";
        	            String revFile = "C:/Users/aravi/eclipse-workspace/mastersProject/Blockchain1/Block5.txt";
        	            fileDatabaseManager.storeFile(hash, fileName, filePath, revFile);
        	            System.out.println("Model file inserted");
        	            //Retrieve ML data file from the database
        	            //String targetFilePath = "C:/Users/aravi/OneDrive/Desktop/project/Block1.txt";
        	           // fileDatabaseManager.retrieveFile(fileName, targetFilePath);
        	            
        	        } catch (SQLException e) {
        	            // Connection failed
        	            System.out.println("Failed to connect to the database.");
        	            e.printStackTrace();
        	        }
        	    }
        	

        	
        
        }
