package superPeer1;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class superPeer1Server {
    public void Service() {
    	
        try {
        
            LocateRegistry.createRegistry(1099);
            Blockchain blockchain = new Blockchain();
            
            Naming.rebind("superPeer1", blockchain);

            System.out.println("Super Peer 1 server is ready to connect....");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        
    }
}
