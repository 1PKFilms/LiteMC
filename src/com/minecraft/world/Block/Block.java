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
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.world.Chunk;
import com.minecraft.world.Util.PositionUtil;

/**
 *
 * @author Pascal
 */
public abstract class Block extends Geometry{
    public static AssetManager assetManager;
    
    private char data;
    private Texture texture;
    private byte biomeID;
  
    public Block(short id,byte metadata,Texture texture){
        super("Block", new Box(0.5f, 0.5f, 0.5f));
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setTexture("DiffuseMap", texture);
        
        this.setMaterial(mat);
        this.texture = texture;
        data = (char)(id<<4);
        setMetadata(metadata);
    
    }
    
    public Block(short id,Texture texture){
        this(id, (byte)0,texture);
    
    }
    public final void setBiomeID(byte biomeID){
        if(currentChunk == null)return;
        currentChunk.setBiomeID((int)getX(), (int)getZ(), biomeID);
    }
    public final byte getBiomeID(){
       if(currentChunk == null)return -1;
        return currentChunk.getBiomeID((int)getX(), (int)getZ());
    }    
    
   //public final void setX(int x){
   //    getLocalTranslation().setX(x);
   // }
   // public final void setY(int y){
   //     getLocalTranslation().setY(y);
   // }
   // public final void setZ(int z){
       // getLocalTranslation().setZ(z);
    //}
    public final float getX(){
        return getLocalTranslation().getX();
    }
    public final float getY(){
        return getLocalTranslation().getY();
    }
    public final float getZ(){
        return getLocalTranslation().getZ();
    }
    private Chunk currentChunk;
    public void addToWorld(int x,int y,int z){
        setLocalTranslation(x, y, z);
        int[] chunkPos = PositionUtil.getChuk(x, z);
        int[] regionPos = PositionUtil.getChunkRegion(chunkPos[0], chunkPos[1]);
        currentChunk = ContentManager.getInstance().getMinecraft().getWorld().getDimension().get(regionPos[0], regionPos[1]).get(chunkPos[0], chunkPos[1]);
        currentChunk.get(y/16).attachChild(this);
    }
    public  void removeFromWorld(){
        removeFromParent();
        currentChunk = null;
    }
    public final short getID(){return (short)(data >> 4);}
    public final void setMetadata(byte metaData){
        short id = getID();
        metaData = (byte)(metaData << 4) ;
        metaData = (byte)(metaData >>> 4);
        data = (char)(id|metaData);

    }
    public final byte getMetadata(){return (byte)((((char)(data << 12)) >>> 12));}
    
    public abstract void renderEffects(RenderManager renderManager);
 
}
