package com.cis.finalProject.superPeer1;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        blocks.add(this);

        try {
            mapper.writeValue(new File(fileName), blocks);
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
