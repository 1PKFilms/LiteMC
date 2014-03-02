/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.minecraft.world.Block.Block;
/**
 *
 * @author Pascal
 */
public class Section extends Node{
    private final int id;
    private Block[][][] blocks = new Block[16][16][16];
    public Section(int id ){
        this.id = id;   
 
        
    }

    @Override
    public String toString() {
        return "Section "+id+" of "+parent.toString();
    }
      public int getID(){
            return id;
        }
    public void set(Block block,int x,int y,int z){
            
            Block current =  blocks[x%16][y%16][z%16];
            if(current  != null)current.removeFromParent();;
            if( block != null) block.addToWorld(x, y, z);
            blocks[x%16][y%16][z%16] = block;
     

    
        
    }
    
    
    public Block get(int x,int y,int z){
       return blocks[x][y][z];
    }
    public boolean isEmpty(){
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while(i < blocks.length){
            while(i2 < blocks[i].length){
             while(i3 < blocks[i][i2].length){
                 if(blocks[i][i2][i3] != null)return false;
                 i3++;
            }
               i2++;
        }
            i++;
        }
        return true;
    }
}
