package xyz.olympiccode.plugins.API.Funções;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import xyz.olympiccode.plugins.API.IOlympicCode;

public class NearbyPlayers {
	
	 IOlympicCode plugin = IOlympicCode.IOlympicCodeClass();
	
	 /**
	  * 
	  * @param l - Centro.
	  * @param radius - Apartir do centro.
	  * @return entidades próximas.
	  * 
	  */
	 
    public Entity[]  getNearbyEntities(Location l, int radius){
        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16))/16;
        HashSet<Entity> radiusEntities = new HashSet<Entity>();
            for (int chX = 0 -chunkRadius; chX <= chunkRadius; chX ++){
                for (int chZ = 0 -chunkRadius; chZ <= chunkRadius; chZ++){
                    int x=(int) l.getX(),y=(int) l.getY(),z=(int) l.getZ();
                    for (Entity e : new Location(l.getWorld(),x+(chX*16),y,z+(chZ*16)).getChunk().getEntities()){
                        if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) radiusEntities.add(e);
                    }
                }
            }
        return radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }
}
