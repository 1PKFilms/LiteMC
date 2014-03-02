/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world;

import com.jme3.asset.AssetManager;
import com.minecraft.world.Block.Block;
import java.util.ArrayList;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.world.Block.Glass;
import com.minecraft.world.Sky.Sun;

/**
 *
 * @author Pascal
 */
public class World  extends Node{
    private Dimension current;
    private boolean  hardcore;
    private int time;
    private AssetManager assetManager;
    public World(boolean hardcore){
        this.assetManager = ContentManager.getInstance().getAssetManager();
        this.hardcore = hardcore;

 
    }
    public void creatLocalTestWorld(){
        
           Dimension dim = new Dimension(0);
                Region    reg1 = new Region(0, 0,dim);
                Region    reg2 = new Region(1, 0,dim);
                
                dim.loadRegion(reg1);
                dim.loadRegion(reg2);
                Chunk tmp1 = new Chunk(0, 0);
                Chunk tmp2 = new Chunk(1, 0);

                reg1.loadChunks(tmp1);
                reg1.loadChunks(tmp2);
                reg2.loadChunks(new Chunk(0, 0));
                reg2.loadChunks(new Chunk(1, 0));
                tmp1.load(new Section(0));
                tmp1.load(new Section(1));
                tmp2.load(new Section(0));
                tmp2.load(new Section(1));
        try {
            dim.setBlock(new Glass(),0,0,0);
            dim.setBlock(new Glass(),17,30,0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
               loadDimension(dim);
        
    }
    public void loadDimension(Dimension dim){
        this.current = dim;
        attachChild(current);
        
    }
    public void unloadDimension(){
        current.removeFromParent();
        current = null;
    }
    public void setBlock(Block block,int x,int y,int z){
        try {
            current.setBlock(block, x, y, z);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void setBlock(Block block){
        try {
            current.setBlock(block);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public Dimension getDimension(){
         return current;
     }
}
