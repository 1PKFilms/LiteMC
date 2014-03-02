/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.network;

import ch.spacebase.mc.auth.exceptions.AuthenticationException;
import ch.spacebase.mc.protocol.MinecraftProtocol;
import ch.spacebase.mc.protocol.packet.ingame.client.ClientKeepAlivePacket;
import ch.spacebase.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import ch.spacebase.mc.protocol.packet.ingame.server.ServerKeepAlivePacket;
import ch.spacebase.packetlib.Client;
import ch.spacebase.packetlib.event.session.ConnectedEvent;
import ch.spacebase.packetlib.event.session.DisconnectedEvent;
import ch.spacebase.packetlib.event.session.DisconnectingEvent;
import ch.spacebase.packetlib.event.session.PacketReceivedEvent;
import ch.spacebase.packetlib.event.session.PacketSentEvent;
import ch.spacebase.packetlib.event.session.SessionAdapter;
import ch.spacebase.packetlib.event.session.SessionListener;
import ch.spacebase.packetlib.packet.Packet;
import ch.spacebase.packetlib.tcp.TcpSessionFactory;
import com.minecraft.Minecraft;
import com.minecraft.world.Dimension;
import com.minecraft.world.World;
import com.sun.jndi.toolkit.dir.SearchFilter;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pascal
 */
public class Server extends SessionAdapter{
     private static MinecraftProtocol protocol;
     private World world;
    static{
        try {
            protocol = new MinecraftProtocol(Minecraft.USERNAME,Minecraft.PASSWORD,false);
        } catch (AuthenticationException ex) {
            JOptionPane.showMessageDialog(null, "Username - Password combination is wrong", "Login-Failed",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    
    private String host;
    private int    port;
    private Client client;
    public Server(String host,int port){
        this.host = host;
        this.port = port;
        client = new Client(host, port, protocol, new TcpSessionFactory());
        client.getSession().addListener(this);
    }
    public void connect(){
        client.getSession().connect();
    }
     public void disconnect(String reason){
       client.getSession().disconnect(reason);
         world = null;
    }
    public void send(Packet packet){
        client.getSession().send(packet);
    }
    public World getWorld(){
        return world;
    }
    
    
    
    
    
    @Override
    public void packetReceived(PacketReceivedEvent e) {
         //KeepAlive
       if(e.getPacket() instanceof ServerKeepAlivePacket){
           send(new ServerKeepAlivePacket(((ClientKeepAlivePacket)e.getPacket()).getPingId()));
           return;
       }
       if(e.getPacket() instanceof ServerJoinGamePacket){
            ServerJoinGamePacket p = (ServerJoinGamePacket) e.getPacket();
            world = new World(p.getHardcore());
            getWorld().loadDimension(new Dimension(p.getDimension()));
            //TODO: Gamemode
           return;
       }
    }
    @Override

    public void packetSent(PacketSentEvent e) {
    }
    @Override

    public void connected(ConnectedEvent e) {
    }
    @Override

    public void disconnecting(DisconnectingEvent e) {
        
    }
    @Override
    public void disconnected(DisconnectedEvent e) {
    }
    
    
   
}
