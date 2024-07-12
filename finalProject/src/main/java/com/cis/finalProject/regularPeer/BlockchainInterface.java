package com.cis.finalProject.regularPeer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BlockchainInterface extends Remote{
	
	byte[] ExtractMLfile(String modelID) throws RemoteException;
	byte[] ExtractReviewsfile(String modelID) throws RemoteException;
	void Search(String modelID) throws RemoteException;
	void updateReviewFile(String modelID, String text) throws RemoteException;
}
