package xyz.olympiccode.plugins.API.Fun��es;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleHandler {
	
	/**
	 * <p> Enviar t�tulo / subt�tulo
	 * 
	 * @param player - Player que receber� o t�tulo/subt�tulo.
	 * @param title - T�tulo
	 * @param subtitle - Subt�tulo
	 * @param fadeIn - Delay que o t�tulo ir� aparecer
	 * @param showTime Tempo que o t�tulo ir� ficar
	 * @param fadeOut Delay que o t�tulo ir� desaparecer
	 * 
	 */
	
	public void sendTitleSubTitle(Player player, String title, String subtitle, int fadeIn, int showTime, int fadeOut) {

            try {
            	
                Object sitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
                Object subTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{'text': '" + title + "'}");
                Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{'text': '" + subtitle + "'}");
                Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object packet = titleConstructor.newInstance(sitle, chat, fadeIn, showTime, fadeOut);
                Object packetSub = titleConstructor.newInstance(subTitle, chatTitle, fadeIn, showTime, fadeOut);
               
                sendPacket(player, packet);
                sendPacket(player, packetSub);
        }
       
        catch (Exception e) {
                e.printStackTrace();
    }
}
	/**
	 * <p> Enviar t�tulo
	 * 
	 * @param player - Player que receber� o t�tulo
	 * @param title - T�tulo
	 * @param fadeIn - Delay que o t�tulo ir� aparecer
	 * @param showTime Tempo que o t�tulo ir� ficar
	 * @param fadeOut Delay que o t�tulo ir� desaparecer
	 * 
	 */
	public void sendTitle(Player player, String title, int fadeIn, int showTime, int fadeOut) {


        try {
            Object sitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{'text': '" + title + "'}");     
            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(sitle, chat, fadeIn, showTime, fadeOut);  
            sendPacket(player, packet);
    }
   
    catch (Exception e) {
            e.printStackTrace();
    }
}
	
	/**
	 * <p> Enviar sub-t�tulo
	 * 
	 * @param player - Player que receber� o sub-t�tulo
	 * @param subtitle - Sub-t�tulo
	 * @param fadeIn - Delay que o t�tulo ir� aparecer
	 * @param showTime Tempo que o t�tulo ir� ficar
	 * @param fadeOut Delay que o t�tulo ir� desaparecer
	 * 
	 */
	public void sendSubTitle(Player player, String subtitle, int fadeIn, int showTime, int fadeOut) {


        try {
            Object subTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{'text': '" + subtitle + "'}");      
            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);   
            Object packetSub = titleConstructor.newInstance(subTitle, chatTitle, fadeIn, showTime, fadeOut);          
            sendPacket(player, packetSub);
    }
   
    catch (Exception e) {
            e.printStackTrace();
    }
}
	
		
        protected void sendPacket(Player player, Object packet) {
            try {
                    Object handle = player.getClass().getMethod("getHandle").invoke(player);
                    Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                    playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
            }
           
            catch (Exception e) {
                    e.printStackTrace();
            }
      }
   
    protected Class<?> getNMSClass(String name) {
            String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            try {
                    return Class.forName("net.minecraft.server." + version + "." + name);
            }
           
            catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
         }
    }
}

