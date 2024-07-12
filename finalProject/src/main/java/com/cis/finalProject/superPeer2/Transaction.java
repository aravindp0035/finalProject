package com.cis.finalProject.superPeer2;

public class Transaction {
	private String sender;
    private String recipient;
    

    public Transaction(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
       
    }

    
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

   
}
