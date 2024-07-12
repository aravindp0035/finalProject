package com.cis.finalProject.superPeer2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import org.springframework.stereotype.Service;


@Service

public class superPeer2Server {
    public void spService() {
    	
        try {
        
            LocateRegistry.createRegistry(1098);
            Blockchain sps2 = new Blockchain();
            
            Naming.rebind("superPeer2", sps2);

            System.out.println("Super Peer 2 server is ready to connect....");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        
    }
}
