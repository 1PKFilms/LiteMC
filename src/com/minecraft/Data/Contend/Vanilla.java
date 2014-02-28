/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data.Contend;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import com.minecraft.Data.Config;
import com.minecraft.world.Block.BlockManager;
import com.minecraft.world.Block.Glass;

/**
 *
 * @author Pascal
 */
public class Vanilla implements Contend{

    

    public void PreInit(ContendManager manager, Config cfg) {
        this.initTextures(manager.getAssetManager());

    }

    public void Init(ContendManager manager) {
        initBlocks(manager.getBlockManager());
    }
    public void initBlocks(BlockManager blockManager){
        blockManager.register(20, Glass.class);
    }
     public void initTextures(AssetManager assetManagere){
        Glass.glassTexture = assetManagere.loadTexture("Textures/Blocks/glass.png");
    }

    public void PostInit(ContendManager manager) {}

    public String getName() {return "Vanilla";}
    
}
