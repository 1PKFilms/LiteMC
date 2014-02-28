/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Block;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.asset.AssetManager;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;

/**
 *
 * @author Pascal
 */
public abstract class Block extends Geometry{
    public static AssetManager assetManager;
    
    private int id;
    private Texture texture;
    private byte biomeID;
  
    public Block(int id,Texture texture){
        super("Block", new Box(0.5f, 0.5f, 0.5f));
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setTexture("DiffuseMap", texture);
        
        this.setMaterial(mat);
        this.texture = texture;
    
    }
    public final void setBiomID(byte biomeID){
        this.biomeID = biomeID;
    }
    public final long getBiomID(){
        return biomeID;
    }    
    
    public final void setX(int x){
        getLocalTranslation().setX(x);
    }
    public final void setY(int y){
        getLocalTranslation().setY(y);
    }
    public final void setZ(int z){
        getLocalTranslation().setZ(z);
    }
    public final float getX(){
        return getLocalTranslation().getX();
    }
    public final float getY(){
        return getLocalTranslation().getY();
    }
    public final float getZ(){
        return getLocalTranslation().getZ();
    }
    public final int getID(){return id;}
    public abstract void renderEffects(RenderManager renderManager);
 
}
