/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft;

/**
 *
 * @author Pascal
 */

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.Data.Content.Vanilla;
import com.minecraft.network.Server;
import com.minecraft.world.Block.Block;
import com.minecraft.world.Block.Glass;
import com.minecraft.world.Chunk;
import com.minecraft.world.Dimension;
import com.minecraft.world.Region;
import com.minecraft.world.Section;
import com.minecraft.world.Sky.Moon;
import com.minecraft.world.Sky.Sun;
import com.minecraft.world.World;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class Minecraft extends SimpleApplication {
    public static final String USERNAME = "MCUSER";
    public static final String PASSWORD = "MCPASSWD";
    public static void main(String[] args) {
        Minecraft mc = new Minecraft();
        mc.start();
    }
  
    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(2);    /** Translucent/transparent cube. Uses Texture from jme3-test-data library! */

                ContentManager.getInstance().setAssetManager(assetManager);
                ContentManager.getInstance().setMinecraft(this);
                Block.assetManager = assetManager;
                ContentManager.getInstance().registerContent(new Vanilla());
                //TODO: Modloading
                ContentManager.getInstance().init();
             ;
    }
 
    @Override
    public void simpleUpdate(float tpf) {
        
    }
  
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
    private Server server;
    public World getWorld(){
        if(server != null)return server.getWorld();
        return null;
    }
}
