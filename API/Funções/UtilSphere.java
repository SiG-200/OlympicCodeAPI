package xyz.olympiccode.plugins.API.Funções;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class UtilSphere {

	/**
	 * 
	 * @param loc - Apartir do local
	 * @param r - Tamanho
	 * @param h - Altura
	 * @param hollow - Terá blocos por dentro? (Oco)
	 * @param sphere - Será esfera ou aberto?
	 * @param plus_y - Acima do Y
	 * @return Location[]
	 */
	
	  public List<Location> getSphere(Location loc, int r, int h, boolean hollow, boolean sphere, int plus_y)
	  {
	    List<Location> circleblocks = new ArrayList<Location>();
	    int cx = loc.getBlockX();
	    int cy = loc.getBlockY();
	    int cz = loc.getBlockZ();
	    for (int x = cx - r; x <= cx + r; x++) {
	      for (int z = cz - r; z <= cz + r; z++) {
	        for (int y = sphere ? cy - r : cy; y < (sphere ? cy + r : cy + h); y++)
	        {
	          double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
	          if ((dist < r * r) && ((!hollow) || (dist >= (r - 1) * (r - 1))))
	          {
	            Location l = new Location(loc.getWorld(), x, y + plus_y, z);
	            circleblocks.add(l);
	          }
	        }
	      }
	    }
	    return circleblocks;
	  }
}
