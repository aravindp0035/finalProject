package com.cis.finalProject.superPeer1;

import java.util.Date;

public class Block {
    private int index;
    private String prevHash;
    private String modelID;
    private long timestamp;
    private String hash;

    // Constructor
    public Block(int index, String prevHash, String modelID) {
        this.index = index;
        this.prevHash = prevHash;
        this.modelID = modelID;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getModelID() {
        return modelID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    // Calculate hash method
    public String calculateHash() {
        String dataToHash = "" + getIndex() + getPrevHash() + getModelID() + getTimestamp();
        return applySha256(dataToHash);
    }

    // Utility method to apply SHA-256 hashing
    private String applySha256(String input) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
