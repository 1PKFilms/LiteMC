/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data.Content;

import com.jme3.asset.AssetManager;
import com.minecraft.Data.Config;
import com.minecraft.Data.Content.Content;
import com.minecraft.world.Block.BlockManager;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Pascal
 */
public class ContentManager {
    private static final ContentManager instance = new ContentManager();
    public static ContentManager getInstance(){
        return instance;
    }
    private BlockManager blockManager;
    private ArrayList<Content> content = new ArrayList<Content>();
    private boolean initialized = false;
    private AssetManager assetManager;
    private ContentManager(){
        
        this.blockManager = new BlockManager();
       
    }
    public void registerContent(Content content){
        if(initialized)throw new IllegalAccessError("Content must be started before Content is initialized");
        this.content.add(content);
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
         while(i < content.size()){
            content.get(i).PreInit(this,new Config(new File("")));
            i++;
        }
         while(i < content.size()){
            content.get(i).Init(this);
            i++;
        }
        while(i < content.size()){
            content.get(i).PostInit(this);
            i++;
        }
    }
    public Content getContent(String name){
        int i = 0;
        while(i < content.size()){
            if(content.get(i).getName().equals(name))return content.get(i);
            i++;
            
        }
        return null;
    }
}
