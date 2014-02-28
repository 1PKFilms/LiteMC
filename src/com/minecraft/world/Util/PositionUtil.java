/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Util;

/**
 *
 * @author Pascal
 */
public class PositionUtil {
 public static int[] getChunkRegion(int chunkX,int chunkZ) {
        int[] res = new int[2];
        res[0] = (int)Math.floor(chunkX / 32.0);
        res[1] = (int)Math.floor(chunkZ / 32.0);
        return res;
 }
 public static int[] getChuk(int x,int z){
      int[] res = new int[2];
        res[0] = (int)(x / 16.0);
        res[1] = (int)(z / 16.0);
        return res;
 }
 public static int[] getRegion(int x,int z){
     int[] chunkCoords = getChuk(x, z);
     return getChunkRegion(chunkCoords[0], chunkCoords[1]);
 }
 public static int getSection(int y){
     return y/16;
 }
}
