/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.network;

import ch.spacebase.packetlib.Session;
import ch.spacebase.packetlib.packet.Packet;
import java.util.ArrayList;

/**
 *
 * @author pascal
 */
public class PacketManager {
    private ArrayList<Class<?>> registered = new ArrayList<Class<?>>();
        private ArrayList<PacketHandler> handlers = new ArrayList<PacketHandler>();

    
    public void register(Class<?> packetClass,PacketHandler handler){
        registered.add(packetClass);
        handlers.add(handler);
    }
    public void handlePacket(Packet packet, Server server){
        int i = 0;
        while(i < registered.size()){
            if(registered.get(i).isInstance(packet)){
                handlers.get(i).handle(packet,server);
                return;
            }
            i++;
        }
        throw new RuntimeException("Packet not registered: "+packet);
    }
}
