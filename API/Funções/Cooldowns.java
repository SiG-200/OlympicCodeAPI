package xyz.olympiccode.plugins.API.Funções;

import org.bukkit.entity.Player;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import xyz.olympiccode.plugins.API.IOlympicCode;

public class Cooldowns {
	
	 IOlympicCode plugin = IOlympicCode.IOlympicCodeClass();
	
	
    private Table<String, String, Long> cooldowns = HashBasedTable.create();
    
    /**
     * @param player - Jogador que receberá o Cooldown
     * @param key - Nome do Cooldown
     */
    
    public long getCooldown(Player player, String key) {
        return calculateRemainder(cooldowns.get(player.getName(), key));
    }
 
    /**
     * Atualizar o cooldown
     * @param player - Jogador
     * @param key - Nome do cooldown para atualizar
     * @param delay - Tempo para o cooldown expirar novamente.
     */
    
    public long setCooldown(Player player, String key, long delay) {
        return calculateRemainder(
                cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
    }
 
    /**
     * Tentar o Cooldown
     * @param player - Jogador que receberá o cooldown
     * @param key - Nome do Cooldown
     * @param delay - Tempo para expirar
     * @return = True
     */
    
    public boolean tryCooldown(Player player, String key, long delay) {
        if (getCooldown(player, key) <= 0) {
            setCooldown(player, key, delay);
            return true;
        }
        return false;
    }
 
    private long calculateRemainder(Long expireTime) {
        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
    }

}
