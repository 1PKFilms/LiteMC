/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data.Contend;

import com.minecraft.Data.Config;

/**
 *
 * @author Pascal
 */
public interface Contend {

    public void PreInit(ContendManager manager,Config cfg);
    public void Init(ContendManager manager );
    public void PostInit(ContendManager manager );

    public String getName();

}
