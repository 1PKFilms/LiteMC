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
    private HashMap<Integer,Class<?>> registeredBlocks = new HashMap<Integer,Class<?>>();
    public void register(int id,Class<?> block){
        if(registeredBlocks.containsKey(id))throw new IllegalArgumentException("Block is already registered");
        registeredBlocks.put(id, block);
    }
    public Block createBlock(int id){
        try {
            return (Block)registeredBlocks.get(id).newInstance();
        } catch (Exception ex) {
        } 
        return null;
    }
}
