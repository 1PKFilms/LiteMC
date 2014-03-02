/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.world.Util;

import ch.spacebase.mc.protocol.data.game.ByteArray3d;
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.world.Chunk;
import com.minecraft.world.Region;
import com.minecraft.world.Section;
import com.minecraft.world.World;

/**
 *
 * @author pascal
 */
public class NetworkUtil {
        public static void loadChunk(Chunk chunk,World world,int x,int z){
         int[] regPosition = PositionUtil.getChunkRegion(x, z);
            Region reg = world.getDimension().get(regPosition[0], regPosition[1]);
            if(chunk == null){
                if(reg == null)return;
                reg.unloadChunks(x, z);
                return;
            }
           
            if(reg == null){
                reg = new Region(regPosition[0], regPosition[1],world.getDimension());
               world.getDimension().loadRegion(reg);
           }
          
           reg.loadChunks(chunk); 
    }
       public static Chunk consturctChunk(World world ,int x,int z,boolean fullChunk,byte[] biomeData,ch.spacebase.mc.protocol.data.game.Chunk[] sections){
       Chunk chunk;
       int[] regionPos = PositionUtil.getChunkRegion(x, z);
       Region region   = world.getDimension().get(regionPos[0], regionPos[1]);
       if(fullChunk||region == null||region.get(x, z) == null)chunk = new Chunk(x, z);
       else chunk = region.get(x, z);
       
       boolean load = false;
       Section current;
       int y = 0;
       while(y < 16){
           if((current = construktSection(y, sections[y++])) == null)continue;
           load = true;
           chunk.unload(y);
           if(!current.isEmpty())chunk.load(current);
           
       }
       if(load == false)return null;
       if(fullChunk){
       x = 0;
       z = 0;
       while(z < 16){
           while(x < 16){
               chunk.setBiomeID(x, z, biomeData[z*16+x]);
               x++;
           }
           z++;
       }
       }
       return chunk;
           
    }
   public static Section construktSection(int y,ch.spacebase.mc.protocol.data.game.Chunk data){
       if(data == null)return null;
       Section section = new Section(y);
       if(data.isEmpty())return section;
            int x = 0;
            y = 0;
            int z = 0;
            ByteArray3d blocks = data.getBlocks();
            while(y < 16){
             while(z < 16){
                while(x < 16){
                    
                   section.set(ContentManager.getInstance().getBlockManager().createBlock((short)(blocks.getData()[((y * 16 + z) * 16 + x)]+(data.getExtendedBlocks().get(x, y, z)<<8))), x, y, z);
                   section.get(x, y, z).setMetadata(data.getMetadata().getData()[((y * 16 + z) * 16 + x)]);
                x++;   
                
            }
                x =0;
                z++;
            }
             x =0;
             y++;
            }
            return section;
    
   }
}
