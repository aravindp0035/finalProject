package superPeer2;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class blockchainServer {
    public static void main(String[] args) {
    	
        try {
        	Properties properties = new Properties();
            FileInputStream input = new FileInputStream("C:/Users/aravi/eclipse-workspace/mastersProject/config.properties");
            properties.load(input);
            int port = Integer.parseInt(properties.getProperty("super.peer2.port"));
        	
            LocateRegistry.createRegistry(port);
           

            
          
            Blockchain blockchain = new Blockchain();
            Naming.rebind("superPeer1", blockchain);

            System.out.println("Super Peer 1 server is ready to connect....");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        
    }
}
