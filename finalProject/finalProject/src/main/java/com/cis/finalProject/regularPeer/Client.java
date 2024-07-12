package com.cis.finalProject.regularPeer;

import com.cis.finalProject.superPeer1.Blockchain;
import java.rmi.Naming;

public class Client {
	public static void main(String[] args) {

	     try {
	         Blockchain bsc = (Blockchain) Naming.lookup("superPeer1");
	         
	         System.out.println("Connected to Super Peer 2");
	         
	         //System.out.println(bsc.toString());
	        
	         

	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	}
}