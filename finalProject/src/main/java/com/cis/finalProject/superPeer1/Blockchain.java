package com.cis.finalProject.superPeer1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Blockchain extends UnicastRemoteObject implements BlockchainInterface{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Block> blockchain = new ArrayList<>();;

	
    MetaBlock mb;
    
    
    
    
    private static final String url = "jdbc:mysql://localhost:3306/blockchain_db";
    private static final String user = "root";
    private static final String password = "Aravind@123";
    private static final String fileName = "metablock.json";
    
    protected Blockchain() throws RemoteException {
        super();
    }

	@Override
	public List<Block> getBlockchain() throws RemoteException {
		return blockchain;
	}
	
	@Override
	public int bcSize() {
		System.out.println(mb.getSize(fileName));
		return mb.getSize(fileName);
	}
	
	
	public Block getLatestBlock() {
		if(blockchain.isEmpty()) {
			return null;
		}
        return blockchain.get(blockchain.size() - 1);
    }
	
	@Override
	public void createNewBlock(String modelID, MultipartFile mlfile, MultipartFile reviewfile) throws RemoteException {
		
    	Block latestBlock = getLatestBlock();
    	int newIndex=0;
    	String newPrevHash="0";
    	if(latestBlock != null) {
    		
    	newIndex = latestBlock.getIndex() + 1;
    	newPrevHash = latestBlock.getHash();
    	}
    	
		Block newBlock = new Block(newIndex, newPrevHash, modelID);
		blockchain.add(newBlock);
		mb = new MetaBlock(newIndex, newPrevHash, newBlock.getHash(), modelID);
		System.out.println("Block created with prev hash: "+newPrevHash+", Block Hash: "+newBlock.getHash()+", Model ID: "+ modelID);
		mb.saveToJSON(fileName);
		System.out.println("MetaBlock is updated");
    	String hash = mb.retrieveHashByModelID(fileName, modelID);
    	
        try (Connection connection = DriverManager.getConnection(url, user, password);
        		
               PreparedStatement statement = connection.prepareStatement("INSERT INTO ml_files (hash, model_name, file_data, reviews_file) VALUES (?, ?, ?, ?)")) {
               statement.setString(1, hash);
               statement.setString(2, modelID);
               statement.setBinaryStream(3, mlfile.getInputStream());
               statement.setBinaryStream(4, reviewfile.getInputStream());

               statement.executeUpdate();
           } catch (SQLException | IOException e) {
               e.printStackTrace();
           
           }
	}
	
	public byte[] ExtractMLfile(String modelID) throws RemoteException {
	    byte[] response = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL JDBC driver not found.");
	        e.printStackTrace();
	        return null;
	    }

	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        // Add logging to verify connection success
	        System.out.println("Database connected successfully.");
	        mb = new MetaBlock();
	        String hash = mb.retrieveHashByModelID(fileName, modelID);
	        // Log the hash to ensure it's correct
	        System.out.println("Hash for modelID " + modelID + ": " + hash);

	        String sql = "SELECT file_data FROM ml_files WHERE hash = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, hash);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                InputStream inputStream = resultSet.getBinaryStream("file_data");
	                
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[1024];
	                int bytesRead;

	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	                response = outputStream.toByteArray();
	                return response;
	            } else {
	                // Add logging if no data is found
	                System.out.println("No file data found for hash: " + hash);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Exception:");
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.out.println("IO Exception:");
	        e.printStackTrace();
	    }
	    return null;
	}

		public byte[] ExtractReviewsfile(String modelID) throws RemoteException{
			
			
			byte[] response = null;
			try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        System.out.println("MySQL JDBC driver not found.");
		        e.printStackTrace();
		    }
		
		   try (Connection connection = DriverManager.getConnection(url, user, password)) {
		        String hash = mb.retrieveHashByModelID(fileName, modelID);
		        String sql = "SELECT reviews_file FROM ml_files WHERE hash = ?";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setString(1, hash);
		            ResultSet resultSet = statement.executeQuery();
		
		            if (resultSet.next()) {
		                InputStream inputStream = resultSet.getBinaryStream("reviews_file");
		              
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                byte[] buffer = new byte[1024];
		                int bytesRead;
		
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);
		                }
		                response = outputStream.toByteArray();
		                return response;
		            }
		        }
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		    }
		    return null;
		}

	@Override
	public void Search(String modelID) throws RemoteException {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void updateReviewFile(String modelID, String text) throws RemoteException {
		// TODO Auto-generated method stub
		String existingData = new String(ExtractReviewsfile(modelID));
		String concatenatedData = existingData +"/n"+"New Review"+"/n"+ text;
	    
		String hash = mb.retrieveHashByModelID(fileName, modelID);
		InputStream targetStream = new ByteArrayInputStream(concatenatedData.getBytes());
        try (Connection connection = DriverManager.getConnection(url, user, password);
        		
               PreparedStatement statement = connection.prepareStatement("UPDATE ml_files SET reviews_file = ? WHERE hash = ?")) {
               statement.setBinaryStream(1, targetStream);
               statement.setString(2, hash);
              

               statement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           
           }
	    
		
	}
    
	
	public static void main(String[] args) {
        try {
            // Start RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            Blockchain server = new Blockchain();
            Naming.rebind("rmi://localhost:1099/Blockchain", server);
            String hash ="f0dfa0a1a9a6ee466091b646ff953654374f094ae35968ecd6a47f2064f4f6d4";
            MetaBlock mb = new MetaBlock(0, "0",hash,"AparV2017");
            
            mb.saveToJSON(fileName);
            
            System.out.println("Blockchain Server is ready.");
        } catch (Exception e) {
            System.err.println("Blockchain Server failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

	
    
    
}
