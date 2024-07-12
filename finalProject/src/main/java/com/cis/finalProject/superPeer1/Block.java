package superPeer1;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

public class Block {
    public int index;
    public String previousHash;
    public String hash;
    public List<Transaction> transactions;
    public long timestamp;

    public Block(int index, String previousHash, List<Transaction> transactions) {
        this.index = index;
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + previousHash + Long.toString(timestamp) + transactions.toString();
        return applySha256(input);
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
