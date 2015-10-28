package xyz.olympiccode.plugins.API.Funções;

import org.bukkit.entity.Player;

import xyz.olympiccode.plugins.API.IOlympicCode;

public class IConomyHelper {
	
	 IOlympicCode plugin = IOlympicCode.IOlympicCodeClass();
	
	/**
	 * [Vault] Adicionar coins para um player de forma fácil.
	 * 
	 * @param player - Player que irá receber.
	 * @param coins - O tanto de coins
	 * 
	 */
	
	public void addCoins(Player player, double coins) {
         plugin.econ.depositPlayer(player, coins);
	}
	
	/**
	 * [Vault] Remover coins de um Player
	 * 
	 * @param player - Player que irá perder.
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
