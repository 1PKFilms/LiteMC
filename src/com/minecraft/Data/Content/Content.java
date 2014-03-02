/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data.Content;

import com.minecraft.Data.Config;

/**
 *
 * @author Pascal
 */
public interface Content {

    public void PreInit(ContentManager manager,Config cfg);
    
    public void Init(ContentManager manager );
    
    
    public void PostInit(ContentManager manager );
    
    public String getName();

}
