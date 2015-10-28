package xyz.olympiccode.plugins.API.Funções;

import org.bukkit.inventory.ItemStack;

public class UtilItem {
	
	/**
	 * Corrigir NullPointer checando null
	 * 
	 * @param is - Confirmar o item
	 * @param nome - Nome do item
	 * @return false
	 */
	
	  public boolean isItem(ItemStack is, String nome)
	  {
	    try
	    {
	      if (!is.hasItemMeta()) {
	        return false;
	      }
	      if (!is.getItemMeta().hasDisplayName()) {
	        return false;
	      }
	      if (is.getItemMeta().getDisplayName().equalsIgnoreCase(nome)) {
	        return true;
	      }
	      return false;
	    }
	    catch (Exception e) {}
		return false;
	  }
	  

}
