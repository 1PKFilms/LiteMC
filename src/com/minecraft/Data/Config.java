/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pascal
 */
public class Config {
    private File file;
    private Properties props;
    private String des = null;
    public Config(File file){
        this.file = file;
        props = new Properties();
        
    }
      public Config(File file,String des){
        this(file);
        this.des = des;
        if(file.exists())try {
            load();
        } catch (IOException ex) {
        }
        
    }
    public void load()throws IOException{
        props.load(new FileInputStream(file));
    }
    public void save()throws IOException{
        props.store(new FileOutputStream(file), des);
    }
}
