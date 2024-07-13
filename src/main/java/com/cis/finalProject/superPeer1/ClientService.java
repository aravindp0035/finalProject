package com.cis.finalProject.superPeer1;


import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClientService {

    @Autowired
    private BlockchainInterface blockchain;
    
    public ClientService() throws RemoteException {
    	System.out.println(blockchain.getRequester());
    }

    public void Search(String modelID) {
        try {
            blockchain.Search(modelID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void createNewBlock(String modelID, MultipartFile mlfile, MultipartFile reviewfile) {
		// TODO Auto-generated method stub
		 try {
	            blockchain.createNewBlock(modelID, reviewfile, reviewfile);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

	public void updateReviewFile(String modelID, String text) {
		// TODO Auto-generated method stub
		try {
            blockchain.updateReviewFile(modelID, text);
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

	public int bcSize() {
		try {
            return blockchain.bcSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
	}

}
