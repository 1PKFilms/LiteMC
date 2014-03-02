/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.network.Handlers;

import ch.spacebase.mc.protocol.data.game.BlockChangeRecord;
import ch.spacebase.mc.protocol.data.game.ByteArray3d;
import ch.spacebase.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import ch.spacebase.mc.protocol.packet.ingame.server.world.ServerBlockValuePacket;
import ch.spacebase.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import ch.spacebase.mc.protocol.packet.ingame.server.world.ServerMultiBlockChangePacket;
import ch.spacebase.mc.protocol.packet.ingame.server.world.ServerMultiChunkDataPacket;
import ch.spacebase.packetlib.packet.Packet;
import com.minecraft.Data.Content.Content;
import com.minecraft.Data.Content.ContentManager;
import com.minecraft.Minecraft;
import com.minecraft.network.PacketHandler;
import com.minecraft.network.Server;
import com.minecraft.world.Block.Block;
import com.minecraft.world.Chunk;
import com.minecraft.world.Region;
import com.minecraft.world.Util.NetworkUtil;
import com.minecraft.world.Util.PositionUtil;
import com.minecraft.world.World;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascal
 */
public class WorldHandler  implements PacketHandler{

    public void handle(Packet packet, Server server) {
        
           if(packet instanceof ServerMultiChunkDataPacket){
            ServerMultiChunkDataPacket p = (ServerMultiChunkDataPacket)packet;
            int i = 0;
            Chunk current;
            while(i < p.getColumns()){
                current = NetworkUtil.consturctChunk(server.getWorld(), p.getX(i), p.getZ(i),true,p.getBiomeData(i),p.getChunks(i));  
                NetworkUtil.loadChunk(current, server.getWorld(), p.getX(i), p.getZ(i));
            }
            return;

        }
        if(packet instanceof ServerChunkDataPacket){
            ServerChunkDataPacket p = (ServerChunkDataPacket)packet;
            Chunk chunk = NetworkUtil.consturctChunk(server.getWorld(), p.getX(), p.getZ(), p.isFullChunk(),p.getBiomeData(),p.getChunks());  
            NetworkUtil.loadChunk(chunk, server.getWorld(), p.getX(), p.getZ());
            return;
        }
        if(packet instanceof ServerMultiBlockChangePacket){
            ServerMultiBlockChangePacket p = (ServerMultiBlockChangePacket)packet;
            int i = 0;
            Block current;
            for(BlockChangeRecord record:p.getRecords()){
                try{
                     current = ContentManager.getInstance().getBlockManager().createBlock((short)record.getId());
                     current.setMetadata((byte)record.getMetadata());
                     server.getWorld().getDimension().setBlock(current, record.getX(),record.getY(), record.getZ());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
             return;
            }

        
        if(packet instanceof ServerBlockChangePacket){
                      ServerBlockChangePacket p = (ServerBlockChangePacket) packet;

            try {
                Block block = ContentManager.getInstance().getBlockManager().createBlock((short)p.getRecord().getId());
                block.setMetadata((byte)p.getRecord().getMetadata());
                server.getWorld().getDimension().setBlock(block, p.getRecord().getX(),p.getRecord().getY(), p.getRecord().getZ());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        if(packet instanceof ServerBlockValuePacket){
            ServerBlockValuePacket p = (ServerBlockValuePacket) packet;
           //TODO: MOJANG IS STUPID;
        }
       
 
    }

}