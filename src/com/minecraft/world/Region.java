/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world;

import com.jme3.scene.Node;

/**
 *
 * @author Pascal
 */
public class Region extends Node{
    private Chunk[][] chunks = new Chunk[32][32];
    private int x;
    private int z;

  
    public void loadChunks(Chunk chunk){
        this.attachChild(chunk);
        chunks[chunk.getX()][chunk.getZ()] = chunk;
    }
    public void unloadChunks(int x,int z){
        Chunk current = chunks[x][z];
        current.removeFromParent();
        chunks[x][z] = null;
    }
     public Region(int x,int z){
        this.x = x;
        this.z = z;
    }
    public Chunk get(int x,int z){
        return chunks[x][z]; 
    }
    public int getX(){
        return x;
    }
    public int getZ(){
            return z;
     }
}
