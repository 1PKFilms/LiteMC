/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data.Contend;

import com.jme3.asset.AssetManager;
import com.minecraft.Data.Config;
import com.minecraft.Data.Contend.Contend;
import com.minecraft.world.Block.BlockManager;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Pascal
 */
public class ContendManager {
    private static final ContendManager instance = new ContendManager();
    public static ContendManager getInstance(){
        return instance;
    }
    private BlockManager blockManager;
    private ArrayList<Contend> contend = new ArrayList<Contend>();
    private boolean initialized = false;
    private AssetManager assetManager;
    private ContendManager(){
        
        this.blockManager = new BlockManager();
       
    }
    public void registerContend(Contend contend){
        if(initialized)throw new IllegalAccessError("Conted must be started bevor Contend is initalized");
        this.contend.add(contend);
    }
    public BlockManager getBlockManager(){
        return blockManager;
    }
    public AssetManager getAssetManager(){
        return assetManager;
    }
    public void setAssetManager(AssetManager assetManager){
        this.assetManager = assetManager;
    }
    public void init(){
        initialized = true;
        int i = 0;
         while(i < contend.size()){
            contend.get(i).PreInit(this,new Config(new File("")));
            i++;
        }
         while(i < contend.size()){
            contend.get(i).Init(this);
            i++;
        }
        while(i < contend.size()){
            contend.get(i).PostInit(this);
            i++;
        }
    }
    public Contend getContend(String name){
        int i = 0;
        while(i < contend.size()){
            if(contend.get(i).getName().equals(name))return contend.get(i);
            i++;
            
        }
        return null;
    }
}
