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
    private Dimension dim;
    private int x;
    private int z;
    
     public Region(int x,int z,Dimension dim){
        this.x = x;
        this.z = z;
        this.dim = dim;
    }
  
    public void loadChunks(Chunk chunk){
        this.attachChild(chunk);
        chunks[chunk.getX()][chunk.getZ()] = chunk;
    }
    public void unloadChunks(int x,int z){
        Chunk current = chunks[x][z];
        current.removeFromParent();
        chunks[x][z] = null;
        if(isEmpty())dim.unloadRegion(this);
        System.gc();
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
    public boolean isEmpty(){
        int i = 0;
        int i2 = 0;
        while(i < 32){
            while(i2 < 32){
                if(chunks[i][i2] != null)return false;
                i2++;
            }
            i2 = 0;
            i++;
        }
        return true;
    }
}
