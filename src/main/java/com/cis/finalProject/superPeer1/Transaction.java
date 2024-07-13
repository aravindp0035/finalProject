package com.cis.finalProject.superPeer1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String transactionID;
    private String requester;
    private String provider;
    private String DES;
    private String modelID;

    public Transaction() {
    }

    public Transaction(String requester, String provider, String DES, String modelID) {
        this.transactionID = setTransactionID();
        this.requester = requester;
        this.provider = provider;
        this.DES = DES;
        this.modelID = modelID;
    }

    // Getters and Setters
    public String getTransactionID() {
        return transactionID;
    }

    public String setTransactionID() {
       
    	return null;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDES() {
        return DES;
    }

    public void setDES(String DES) {
        this.DES = DES;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    // Save Transaction to JSON
    public void saveToJSON(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<Transaction> transactions = new ArrayList<>();
        try {
            // Check if file exists and read existing data
            File file = new File(fileName);
            if (file.exists() && file.length() > 0) {
                transactions = mapper.readValue(file, new TypeReference<List<Transaction>>() {});
            }
            // Add the current object to the list
            transactions.add(this);

            // Write the updated list back to the file
            mapper.writeValue(file, transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieve transactions by modelID from JSON
    public static List<Transaction> retrieveTransactionsByModelID(String fileName, String modelID) {
        ObjectMapper mapper = new ObjectMapper();
        List<Transaction> filteredTransactions = new ArrayList<>();
        try {
            List<Transaction> transactions = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));

            for (Transaction transaction : transactions) {
                if (transaction.getModelID().equals(modelID)) {
                    filteredTransactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredTransactions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", requester='" + requester + '\'' +
                ", provider='" + provider + '\'' +
                ", DES='" + DES + '\'' +
                ", modelID='" + modelID + '\'' +
                '}';
    }
}
