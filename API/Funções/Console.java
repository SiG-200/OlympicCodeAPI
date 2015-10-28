package xyz.olympiccode.plugins.API.Funções;



import xyz.olympiccode.plugins.API.IOlympicCode;

public class Console {
	
	/**
	 * Forçar a enviar uma mensagem pelo Console.
	 * 
	 * @param message - Mensagem a ser enviada
	 * 
	 */
	
	 IOlympicCode plugin = IOlympicCode.IOlympicCodeClass();
	
	public void consoleSenderMessage(String message) 
	{
		plugin.getServer().getConsoleSender().sendMessage(message);
	}
	
	/**
	 * Executar um comando pelo Console.
	 * 
	 * @param command - Comando a ser executado.
	 * "/" ignorado.
	 * 
	 */
	
	public void consoleCommand(String command) {
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command);
	}
	
	
}
	