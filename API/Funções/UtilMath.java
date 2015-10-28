package xyz.olympiccode.plugins.API.Funções;

import java.text.DecimalFormat;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class UtilMath
{
  public static double trim(int paramInt, double paramDouble)
  {
    String str = "#.#";
    for (int i = 1; i < paramInt; i++) {
      str = str + "#";
    }
    DecimalFormat localDecimalFormat = new DecimalFormat(str);
    return Double.valueOf(localDecimalFormat.format(paramDouble)).doubleValue();
  }
  
  public static Random random = new Random();
  
  public static int r(int paramInt)
  {
    return random.nextInt(paramInt);
  }
  
  public static double offset2d(Entity paramEntity1, Entity paramEntity2)
  {
    return offset2d(paramEntity1.getLocation().toVector(), paramEntity2.getLocation().toVector());
  }
  
  public static double offset2d(Location paramLocation1, Location paramLocation2)
  {
    return offset2d(paramLocation1.toVector(), paramLocation2.toVector());
  }
  
  public static double offset2d(Vector paramVector1, Vector paramVector2)
  {
    paramVector1.setY(0);
    paramVector2.setY(0);
    return paramVector1.subtract(paramVector2).length();
  }
  
  public static double offset(Entity paramEntity1, Entity paramEntity2)
  {
    return offset(paramEntity1.getLocation().toVector(), paramEntity2.getLocation().toVector());
  }
  
  public static double offset(Location paramLocation1, Location paramLocation2)
  {
    return offset(paramLocation1.toVector(), paramLocation2.toVector());
  }
  
  public static double offset(Vector paramVector1, Vector paramVector2)
  {
    return paramVector1.subtract(paramVector2).length();
  }
  
  public static Vector getRandomVector()
  {
    double d1 = random.nextDouble() * 2.0D - 1.0D;
    double d2 = random.nextDouble() * 2.0D - 1.0D;
    double d3 = random.nextDouble() * 2.0D - 1.0D;
    
    return new Vector(d1, d2, d3).normalize();
  }
  
  public static Vector getRandomCircleVector()
  {
    double d1 = random.nextDouble() * 2.0D * 3.141592653589793D;
    double d2 = Math.cos(d1);
    double d3 = Math.sin(d1);
    
    return new Vector(d2, 0.0D, d3);
  }
  
  public static Material getRandomMaterial(Material[] paramArrayOfMaterial)
  {
    return paramArrayOfMaterial[random.nextInt(paramArrayOfMaterial.length)];
  }
  
  public static double getRandomAngle()
  {
    return random.nextDouble() * 2.0D * 3.141592653589793D;
  }
}
