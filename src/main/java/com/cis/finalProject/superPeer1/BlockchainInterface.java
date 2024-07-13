package com.cis.finalProject.superPeer1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BlockchainInterface extends Remote{
	
    List<Block> getBlockchain() throws RemoteException;
//    boolean addTransaction(Transaction transaction) throws RemoteException;
//    List<Transaction> getPendingTransactions() throws RemoteException;
	void createNewBlock(String modelID, MultipartFile mlfile, MultipartFile reviewfile) throws RemoteException;
	byte[] ExtractMLfile(String modelID) throws RemoteException;
	byte[] ExtractReviewsfile(String modelID) throws RemoteException;
	void Search(String modelID) throws RemoteException;
	int bcSize() throws RemoteException;
	void updateReviewFile(String modelID, String text) throws RemoteException;
	String getRequester() throws RemoteException;
}

