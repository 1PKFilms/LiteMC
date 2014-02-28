/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Sky;

import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 *
 * @author pascal
 */
public class Sun extends DirectionalLight{
    public Sun(){
        setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        setColor(ColorRGBA.White.set(1.6f, 1.6f, 1f, 0f));
        this.setName("Sun");
      
  
    }
}
