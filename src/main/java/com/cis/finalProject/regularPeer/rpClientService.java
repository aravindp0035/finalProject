package com.cis.finalProject.regularPeer;


import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rpClientService {

    @Autowired
    private BlockchainInterface blockchain;
    
    public rpClientService() throws RemoteException {
    	System.out.println(blockchain.getRequester());
    }

    public void Search(String modelID) {
        try {
            blockchain.Search(modelID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void updateReviewFile(String modelID, String text) {
		// TODO Auto-generated method stub
		try {
            blockchain.updateReviewFile(modelID,text);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	public byte[] ExtractMLfile(String modelID) {
		// TODO Auto-generated method stub
		try {
            return blockchain.ExtractMLfile(modelID);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public byte[] ExtractReviewsfile(String modelID) {
		// TODO Auto-generated method stub
		try {
            return blockchain.ExtractReviewsfile(modelID);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}

}
