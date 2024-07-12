package superPeer1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BlockchainInterface extends Remote {
    boolean addBlock(Block block) throws RemoteException;
    List<Block> getBlockchain() throws RemoteException;
    boolean addTransaction(Transaction transaction) throws RemoteException;
    List<Transaction> getPendingTransactions() throws RemoteException;
}

