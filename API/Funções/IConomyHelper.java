package xyz.olympiccode.plugins.API.Fun��es;

import org.bukkit.entity.Player;

import xyz.olympiccode.plugins.API.IOlympicCode;

public class IConomyHelper {
	
	 IOlympicCode plugin = IOlympicCode.IOlympicCodeClass();
	
	/**
	 * [Vault] Adicionar coins para um player de forma f�cil.
	 * 
	 * @param player - Player que ir� receber.
	 * @param coins - O tanto de coins
	 * 
	 */
	
	public void addCoins(Player player, double coins) {
         plugin.econ.depositPlayer(player, coins);
	}
	
	/**
	 * [Vault] Remover coins de um Player
	 * 
	 * @param player - Player que ir� perder.
	 * @param quantia - Quantidade de coins
	 * 
	 */
	
	public void removeCoins(Player p, double coins) {
		plugin.econ.withdrawPlayer(p, coins);
	}
	
	public Double getPlayerCoins(Player p) {
		double balance = plugin.econ.getBalance(p);
		return balance;
	}
}
