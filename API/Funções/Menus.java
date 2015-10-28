package xyz.olympiccode.plugins.API.Funções;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.olympiccode.plugins.API.IOlympicCode;

public class Menus implements Listener {

	 IOlympicCode pl = IOlympicCode.IOlympicCodeClass();
	 
	 public Menus() {}

	private JavaPlugin plugin;
	private String title = null;
	private int rows = 0;
	private boolean closeonclick = true;
	private HashMap<Integer, ArrayList<Object>> content = new HashMap<Integer, ArrayList<Object>>();
	private Inventory inventory;

	public Menus(JavaPlugin plugin, String title, int rows)
			throws IndexOutOfBoundsException {
		this.plugin = plugin;
		this.title = title;
		if (rows < 1 || rows > 6) {
			throw new IndexOutOfBoundsException(
					"Inventários só podem haver até 6 linhas.");
		}
		this.rows = rows;
		this.inventory = Bukkit.createInventory(null, rows * 9, title);
	}

	public Menus(JavaPlugin plugin, String title, int rows,
			ItemStack[] contents) throws IndexOutOfBoundsException {
		this(plugin, title, rows);
		setContents(contents);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getRawSlot();

		if (inv.getName().equals(title)) {
			event.setCancelled(true);

			if (slot <= (rows * 9) - 1) {
				if (closeonclick) {
					player.closeInventory();
				}
				if (hasCommand(slot)) {
					ArrayList<String> cmds = getCommands(slot);

					if (!cmds.isEmpty()) {
						for (String s : cmds) {
							if (s != null && s != "") {
								runCommand(player, s);
							}
						}
					}
				}
			}
		}
	}

	private boolean hasCommand(int slot) {
		if (content.containsKey(slot)) {
			for (Object o : content.get(slot)) {
				if (o instanceof String) {
					return true;
				}
			}
		}
		return false;
	}

	private void runCommand(Player player, String cmd) {
		cmd = cmd.replaceAll(Pattern.quote("<p>"), player.getName());

		if (cmd.startsWith("<tell>")) {
			cmd = cmd.replaceFirst(Pattern.quote("<tell>"), "");
			player.sendMessage(cmd);
		} else if (cmd.startsWith("<close>")) {
			player.closeInventory();
		} else if (cmd.startsWith("<player>")) {
			cmd = cmd.replaceFirst(Pattern.quote("<player>"), "");
			player.performCommand(cmd);
		} else {
			if (cmd.startsWith("/")) {
				cmd = cmd.replaceFirst(Pattern.quote("/"), "");
			}
			plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
		}
	}

	private ArrayList<String> getCommands(int slot) {
		if (content.containsKey(slot)) {
			ArrayList<String> cmd = new ArrayList<String>();
			for (Object o : content.get(slot)) {
				if (o instanceof String) {
					String[] cmds = ((String) o).split(Pattern.quote("^$"));
					for (String s : cmds) {
						cmd.add(s);
					}
				}
			}
			return cmd;
		}
		return null;
	}

	public void closeOnClick(boolean b) {
		closeonclick = b;
	}

	public int nextAvailableSlot() {
		int h = 0;
		for (Integer i : content.keySet()) {
			if (i > h) {
				h = i;
			}
		}
		for (int i = 0; i <= h; i++) {
			if (!content.containsKey(i)) {
				return i;
			}
		}
		return h + 1;
	}

	public void setContents(ItemStack[] contents)
			throws ArrayIndexOutOfBoundsException {
		if (contents.length > rows * 9) {
			throw new ArrayIndexOutOfBoundsException(
					"Contents are larger than the inventory.");
		}
		for (int i = 0; i < contents.length; i++) {
			ItemStack item = contents[i];

			if (item != null && item.getType() != Material.AIR) {
				ArrayList<Object> list = new ArrayList<Object>();
				list.add(item);
				content.put(i, list);
			}
		}
	}

	public void addItem(ItemStack item) throws IndexOutOfBoundsException {
		if (nextAvailableSlot() > (rows * 9) - 1) {
			throw new IndexOutOfBoundsException("Inventário está lotado.");
		}
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item);
		content.put(nextAvailableSlot(), list);
	}

	public void addItem(ItemStack item, String commands)
			throws IndexOutOfBoundsException {
		if (nextAvailableSlot() > (rows * 9) - 1) {
			throw new IndexOutOfBoundsException("Inventário está lotado.");
		}
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item);
		list.add(commands);
		content.put(nextAvailableSlot(), list);
	}

	public void setItem(int slot, ItemStack item)
			throws IndexOutOfBoundsException {
		if (slot > (rows * 9) - 1 || slot < 0) {
			throw new IndexOutOfBoundsException("Slot fora do inventário.");
		}
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item);
		content.put(slot, list);
	}

	public void setItem(int slot, ItemStack item, String commands)
			throws IndexOutOfBoundsException {
		if (slot > (rows * 9) - 1 || slot < 0) {
			throw new IndexOutOfBoundsException("Slot fora do inventário.");
		}
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item);
		list.add(commands);
		content.put(slot, list);
	}

	public void setAll(ItemStack item) {
		for (int i = 0; i < rows * 9; i++) {
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(item);
			content.put(i, list);
		}
	}

	public void addCommand(int slot, String command)
			throws IndexOutOfBoundsException {
		if (!content.containsKey(slot)) {
			throw new IndexOutOfBoundsException("Item não definido para slot.");
		}
		if (hasCommand(slot)) {
			ArrayList<String> cmds = getCommands(slot);
			cmds.add(command);
			String newcommand = StringUtils.join(cmds, "^$");
			setCommands(slot, newcommand);
		} else {
			setCommands(slot, command);
		}
	}

	public void setCommands(int slot, String commands)
			throws IndexOutOfBoundsException {
		if (!content.containsKey(slot)) {
			throw new IndexOutOfBoundsException("Item não definido para slot.");
		}
		ArrayList<Object> cmds = content.get(slot);
		for (Object o : cmds) {
			if (o instanceof String) {
				cmds.remove(o);
			}
		}
		cmds.add(commands);
		content.put(slot, cmds);
	}

	public ItemStack createItem(Material material, int amount, String name,
			String lore, short durability) throws IndexOutOfBoundsException {
		if (amount < 1 || amount > 64) {
			throw new IndexOutOfBoundsException("A quantia de de itens é 1-64");
		}
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();

		if (name != null && name != "") {
			meta.setDisplayName(name);
		}
		if (lore != null && lore != "") {
			String[] lines = lore.split(Pattern.quote("^$"));
			List<String> newlore = new ArrayList<String>();
			for (String s : lines) {
				newlore.add(s);
			}
			meta.setLore(newlore);
		}
		item.setDurability(durability);
		item.setItemMeta(meta);

		return item;
	}

	public ItemStack createItem(Material material, int amount, String name,
			String lore, short durability, byte data)
			throws IndexOutOfBoundsException {
		if (amount < 1 || amount > 64) {
			throw new IndexOutOfBoundsException("A quantia de de itens é 1-64");
		}
		ItemStack item = new ItemStack(material, amount, data);
		ItemMeta meta = item.getItemMeta();

		if (name != null && name != "") {
			meta.setDisplayName(name);
		}
		if (lore != null && lore != "") {
			String[] lines = lore.split(Pattern.quote("^$"));
			List<String> newlore = new ArrayList<String>();
			for (String s : lines) {
				newlore.add(s);
			}
			meta.setLore(newlore);
		}
		item.setDurability(durability);
		item.setItemMeta(meta);

		return item;
	}

	public void build() {
		inventory.clear();
		for (Map.Entry<Integer, ArrayList<Object>> entry : content.entrySet()) {
			for (Object o : entry.getValue()) {
				if (o instanceof ItemStack) {
					inventory.setItem(entry.getKey(), (ItemStack) o);
				}
			}
		}
	}

	public Inventory getMenu() {
		build();
		return inventory;
	}

	public void showMenu(Player player) {
		player.openInventory(getMenu());
	}

}