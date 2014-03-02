/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Block;

import com.minecraft.world.Block.Block;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pascal
 */
public class BlockManager {
    private HashMap<Short,Class<?>> registeredBlocks = new HashMap<Short,Class<?>>();
    public void register(short id,Class<?> block){
        if(registeredBlocks.containsKey(id))throw new IllegalArgumentException("Block is already registered");
        registeredBlocks.put(id, block);
    }
    public Block createBlock(short id){
        try {
            return (Block)registeredBlocks.get(id).newInstance();
        } catch (Exception ex) {
        } 
        return null;
    }
}
