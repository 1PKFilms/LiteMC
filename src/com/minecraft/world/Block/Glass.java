/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Block;

import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.texture.Texture;

/**
 *
 * @author Pascal
 */
public class Glass extends Block{
    public static Texture glassTexture;
    public Glass(){
        super(20,glassTexture);
        Material tmp = getMaterial();
        this.setQueueBucket(Bucket.Transparent);
        tmp.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        setMaterial(tmp);
      
    }
    @Override
    public void renderEffects(RenderManager renderManager) {
        
    }
    
}
