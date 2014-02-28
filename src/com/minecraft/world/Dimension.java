/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world;

import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.world.Block.Block;
import com.minecraft.world.Sky.Sun;
import com.minecraft.world.Util.PositionUtil;
import java.awt.Color;
import java.util.ArrayList;

public class Dimension extends Node{
    //Loaded regions
    private ArrayList<Region> regions = new ArrayList<Region>();
    private int id;


     public Dimension(int id){
        this.id = id;
      attachChild(SkyFactory.createSky(
            ContentManager.getInstance().getAssetManager(), "Textures/enviroment/BrightSky.dds", false));        addLight(new Sun());
         AmbientLight al = new AmbientLight();
         al.setColor(ColorRGBA.White.mult(0.6f));
         addLight(al);
    }
    public Region get(int x,int z){
        int i = 0;
        while(i < regions.size()){
            if(regions.get(i).getX()==x&&regions.get(i).getZ()==z)return regions.get(i);

            i++;
        }
        return null;
    }
    public void loadRegion(Region region){
        regions.add(region);
        this.attachChild(region);
    }
    public void unloadRegion(Region region){
        regions.remove(region);
        region.removeFromParent();
        System.gc();
    }
    public void load(int x,int z){
        //Server only;
     }
    public void unload(){
        regions = new ArrayList<Region>();
        System.gc();
    }
    public int getID(){
        return id;
    }
    //throws Exception if position is not loaded
    public void setBlock(Block block,int x,int y,int z)throws Exception{
        int[] regionCord = PositionUtil.getRegion(x, z);
        Region reg = get(regionCord[0], regionCord[1]);
        int[] chunkCord = PositionUtil.getChuk(x, z);
        Chunk chunk = reg.get(chunkCord[0], chunkCord[1]);
        Section sec = chunk.get(PositionUtil.getSection(y));
        sec.set(block, x, y, z);
    }
    public void setBlock(Block block)throws Exception{
        if(block == null)throw  new IllegalArgumentException("Block mustn't be null!");
        setBlock(block, (int)block.getX(), (int) block.getY(), (int) block.getZ());
    }
}
