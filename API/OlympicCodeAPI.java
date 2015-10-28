package xyz.olympiccode.plugins.API;

import xyz.olympiccode.plugins.API.Funções.ActionBar;
import xyz.olympiccode.plugins.API.Funções.Console;
import xyz.olympiccode.plugins.API.Funções.Cooldowns;
import xyz.olympiccode.plugins.API.Funções.IConomyHelper;
import xyz.olympiccode.plugins.API.Funções.JsonBuilder;
import xyz.olympiccode.plugins.API.Funções.Menus;
import xyz.olympiccode.plugins.API.Funções.NearbyPlayers;
import xyz.olympiccode.plugins.API.Funções.SpigotPluginUpdater;
import xyz.olympiccode.plugins.API.Funções.TitleHandler;
import xyz.olympiccode.plugins.API.Funções.UtilItem;
import xyz.olympiccode.plugins.API.Funções.UtilItemStack;
import xyz.olympiccode.plugins.API.Funções.UtilMath;
import xyz.olympiccode.plugins.API.Funções.UtilSphere;
import xyz.olympiccode.plugins.API.Funções.UtilTime;

public class OlympicCodeAPI {
	
    public NearbyPlayers getNearPlayers() {
    	NearbyPlayers targetN = new NearbyPlayers();
    	return targetN;
    }
    
    public ActionBar getActionBar() {
        ActionBar action = new ActionBar();
        return action;
    }

    public Cooldowns getCooldowns() {
        Cooldowns cmanager = new Cooldowns();
        return cmanager;
    }
    
    public SpigotPluginUpdater getUpdater() {
    	SpigotPluginUpdater updater = new SpigotPluginUpdater();
        return updater;
    }

    @Deprecated
    public IConomyHelper getIConomy() {
        IConomyHelper overCO = new IConomyHelper();
        return overCO;
    }
    
    public IConomyHelper getIConomyHelper() {
        IConomyHelper overCO = new IConomyHelper();
        return overCO;
    }

    public Console getConsole() {
        Console console = new Console();
        return console;
    }
    
    public JsonBuilder getJsonManager() {
    	JsonBuilder j = new JsonBuilder();
    	return j;
    }
    
    public Menus getMenuManager() {
       Menus um = new Menus();
    	return um;
    }

    public UtilMath getMath() {
    	UtilMath math = new UtilMath();
    	return math;
    }
    
    public UtilTime getTimer() {
    	UtilTime time = new UtilTime();
    	return time;
    }
    
    public UtilSphere getSphere() {
    	UtilSphere sphere = new UtilSphere();
    	return sphere;
    }
    public UtilItem getItem() {
    	UtilItem iut = new UtilItem();
    	return iut;
    }
    
    public UtilItemStack getItemBuilder() {
    	UtilItemStack itk = new UtilItemStack();
    	return itk;
    }
   
    public TitleHandler getTitleHandler() {
    	TitleHandler t = new TitleHandler();
    	return t;
    }

}
