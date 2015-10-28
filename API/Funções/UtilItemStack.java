package xyz.olympiccode.plugins.API.Funções;


import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


public class UtilItemStack {

	/**
	 * Criar um ItemStack
	 * 
	 * @param tipo - Tipo Material.TIPO
	 * @param nome - Nome do item
	 * @param lore - DescriÃ§Ã£o do item Arrays.asList("");
	 * @param quantidade - Quantidade
	 * @param data - Byte, data do item
	 * @return item
	 */
	
	public ItemStack newItemStack(Material tipo, String nome, List<String> lore, int quantidade, byte data)
	  {
	    ItemStack i = new ItemStack(tipo, quantidade, data);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(nome);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    return i;
	  }
	  
	/**
	 * 
	 * @param tipo - Tipo da armadura
	 * @param enchant - Encantamento (pode ser null)
	 * @param level - Level (pode ser 0)
	 * @return item;
	 */
	  public ItemStack buildArmor(Material tipo, Enchantment enchant, int level) {
		  ItemStack i = new ItemStack(tipo);
		  ItemMeta im = i.getItemMeta();
		  im.addEnchant(enchant, level, true);
		  i.setItemMeta(im);
		  return i;
	  }
	  
	  /**
	   * 
	   * @param tipo - Tipo (Deve ser couro)
	   * @param color - Cor (org.bukkit.Color)
	   * @return armor;
	   */
	  
	  public ItemStack buildColoredArmor(Material tipo, Color color) {
		  
		  ItemStack i = new ItemStack(tipo);
		  if((tipo != Material.LEATHER_BOOTS) && 
				  (tipo != Material.LEATHER_LEGGINGS) && 
				    (tipo != Material.LEATHER_CHESTPLATE) && 
				     (tipo != Material.LEATHER_HELMET)) {
			  throw new IllegalArgumentException("O tipo de armadura deve ser de couro!");
		  }
		  
		  LeatherArmorMeta l = (LeatherArmorMeta)i.getItemMeta();
		  l.setColor(color);
		  i.setItemMeta(l);
		  return i;
	  }
}