package xyz.olympiccode.plugins.API;

import net.milkbowl.vault.economy.Economy;
import sun.security.action.GetLongAction;
import xyz.olympiccode.plugins.API.Funções.ActionBar;
import xyz.olympiccode.plugins.API.Funções.Updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class IOlympicCode extends JavaPlugin {

    protected static IOlympicCode IOlympicCode;
    protected static OlympicCodeAPI OlympicCodeAPI;
    
    public Economy econ = null;

    public static IOlympicCode IOlympicCodeClass() {
    	return IOlympicCode;
    }
    
    public static OlympicCodeAPI getAPI() {
    	return OlympicCodeAPI;
    }
    
    protected boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
    	getLogger().info("Habilitando outras APIs");
        setupEconomy();

        getLogger().info("Carregando OlympicCodeAPI");

        ActionBar.nmsver = Bukkit.getServer().getClass().getPackage().getName();
        ActionBar.nmsver = ActionBar.nmsver.substring(ActionBar.nmsver.lastIndexOf(".") + 1);

        IOlympicCode = this;
        OlympicCodeAPI = new OlympicCodeAPI();
        IOlympicCode api = (IOlympicCode) this.getServer().getPluginManager().getPlugin("OlympicCodeAPI");
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Updater(this), 1L, 1L);
        api.getClass();
        OlympicCodeAPI.getActionBar();
        OlympicCodeAPI.getCooldowns();
        OlympicCodeAPI.getConsole();
        OlympicCodeAPI.getJsonManager();
        OlympicCodeAPI.getMenuManager();
        OlympicCodeAPI.getNearPlayers();
        OlympicCodeAPI.getUpdater();
        OlympicCodeAPI.getMath();
        OlympicCodeAPI.getTimer();
        OlympicCodeAPI.getSphere();
        OlympicCodeAPI.getItem();
        OlympicCodeAPI.getItemBuilder();
        OlympicCodeAPI.getIConomy();
        
        getLogger().info("OlympicCodeAPI foi habilitado com sucesso!");
    }  
    
}
