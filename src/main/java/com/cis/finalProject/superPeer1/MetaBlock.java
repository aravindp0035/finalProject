package com.cis.finalProject.superPeer1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetaBlock {
    private int blockIndex;
    private String prevHash;
    private String hash;
    private String modelID;
    
    public MetaBlock() {
    	
    }

    public MetaBlock(int blockIndex, String prevHash, String hash, String modelID) {
        this.blockIndex = blockIndex;
        this.prevHash = prevHash;
        this.hash = hash;
        this.modelID = modelID;
    }

    // Getters and Setters
    public int getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(int blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    // Save MetaBlock to JSON
    public void saveToJSON(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<MetaBlock> blocks = new ArrayList<>();
        try {
            // Check if file exists and read existing data
            File file = new File(fileName);
            if (file.exists() && file.length() > 0) {
                blocks = mapper.readValue(file, new TypeReference<List<MetaBlock>>() {});
            }
            // Add the current object to the list
            blocks.add(this);
            
            // Write the updated list back to the file
            mapper.writeValue(file, blocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieve hash by modelID from JSON
    public String retrieveHashByModelID(String fileName, String modelID) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<MetaBlock> blocks = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, MetaBlock.class));

            for (MetaBlock block : blocks) {
                if (block.getModelID().equals(modelID)) {
                    return block.getHash();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getSize(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<MetaBlock> blocks = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, MetaBlock.class));

            return blocks.size();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Method to load MetaBlocks from JSON file
    public static List<MetaBlock> loadBlocksFromJSON(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<MetaBlock> blocks = new ArrayList<>();
        try {
            blocks = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, MetaBlock.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blocks;
    }

    // Method to return the latest MetaBlock
    public static MetaBlock getLatestMetaBlock(String fileName) {
        List<MetaBlock> blocks = loadBlocksFromJSON(fileName);
        if (blocks.isEmpty()) {
            return null;
        }
        MetaBlock latestBlock = blocks.get(0);
        for (MetaBlock block : blocks) {
            if (block.getBlockIndex() > latestBlock.getBlockIndex()) {
                latestBlock = block;
            }
        }
        return latestBlock;
    }


    @Override
    public String toString() {
        return "MetaBlock{" +
                "blockIndex=" + blockIndex +
                ", prevHash='" + prevHash + '\'' +
                ", hash='" + hash + '\'' +
                ", modelID='" + modelID + '\'' +
                '}';
    }

}
