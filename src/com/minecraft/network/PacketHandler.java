/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.network;

import ch.spacebase.packetlib.Session;
import ch.spacebase.packetlib.packet.Packet;

/**
 *
 * @author pascal
 */
public interface PacketHandler {
    public void handle(Packet packet,Server server);
}
