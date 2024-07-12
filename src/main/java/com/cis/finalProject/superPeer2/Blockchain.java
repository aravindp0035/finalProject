package com.cis.finalProject.superPeer2;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Blockchain extends UnicastRemoteObject implements BlockchainInterface {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Block> blockchain;
    private List<Transaction> pendingTransactions;

    protected Blockchain() throws RemoteException {
        blockchain = new ArrayList<>();
        pendingTransactions = new ArrayList<>();
        blockchain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, "0", new ArrayList<>());
    }

    public boolean addBlock(Block block) {
        if (isValidNewBlock(block, getLastBlock())) {
            blockchain.add(block);
            pendingTransactions.clear();
            return true;
        }
        return false;
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public boolean addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
        return true;
    }

    public List<Transaction> getPendingTransactions() {
        return pendingTransactions;
    }

    private boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (previousBlock.index + 1 != newBlock.index) {
            return false;
        }
        if (!previousBlock.hash.equals(newBlock.previousHash)) {
            return false;
        }
        if (!newBlock.hash.equals(newBlock.calculateHash())) {
            return false;
        }
        return true;
    }

    private Block getLastBlock() {
        return blockchain.get(blockchain.size() - 1);
    }
}
