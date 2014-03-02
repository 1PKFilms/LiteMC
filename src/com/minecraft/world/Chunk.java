/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world;
import com.jme3.scene.Node;
import com.minecraft.world.Block.Block;
import java.util.ArrayList;


/**
 *
 * @author Pascal
 */
public class Chunk extends Node{
    private final int xPos;
    private final int zPos;
    private boolean populated = false;
    private long LastUpdate;
    //loaded (not empty sections)
    private Section[] section = new Section[16];
    private byte[][]   biomeID  = new byte[16][16];
    /*
     * TODO: Entitys and TileEntitys
     */
    public Chunk(int xPos,int zPos ){
        this.xPos = xPos;
        this.zPos = zPos;
        this.parent = parent;
        
      
    }
    public void load(Section section){
        this.attachChild(section);
        this.section[section.getID()] = section;

        
        
    }
      public void unload(int y){
        if(section[y] != null)section[y].removeFromParent();
        section[y] = null;
          System.gc();

        
        
    }
      public byte getBiomeID(int x,int z){
          return biomeID[x][z];
      }
      public void setBiomeID(int x,int z,byte BiomeID){
          biomeID[x][z] = BiomeID;
      }
        
        public Section get(int id){
            return this.section[id];
        }
        public int getX(){
            return xPos;
        }
        public int getZ(){
            return zPos;
        }
        
    
    @Override
    public String toString() {
       return "Chunck at "+xPos+" "+zPos+" in "+parent.toString();
    }
    
    
}
