package com.cis.finalProject.superPeer2;
import java.rmi.Naming;

import java.util.List;

public class BlockchainClient {
    public static void main(String[] args) {
        try {
            BlockchainInterface blockchain = (BlockchainInterface) Naming.lookup("rmi://localhost:1009/superPeer1");

            // Add transactions
            blockchain.addTransaction(new Transaction("Alice", "Bob"));
            blockchain.addTransaction(new Transaction("Bob", "Charlie"));

            // Create a new block with pending transactions
            Block newBlock = new Block(blockchain.getBlockchain().size(),
                    blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1).hash,
                    blockchain.getPendingTransactions());
            blockchain.addBlock(newBlock);

            // Retrieve and print the blockchain
            List<Block> chain = blockchain.getBlockchain();
            for (Block block : chain) {
                System.out.println("Index: " + block.index);
                System.out.println("Timestamp: " + block.timestamp);
                System.out.println("Transactions: " + block.transactions);
                System.out.println("Hash: " + block.hash);
                System.out.println("Previous Hash: " + block.previousHash);
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
