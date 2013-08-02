package be.infogroep.ItemShare;


import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemShare extends JavaPlugin {
	
	private boolean EnchantmentAPI_ = false;
	private LoggingClass LC = null;
	static ConfigHandler ConfigHandler_ = null;
	
	
	@Override
	public void onLoad(){
		Plugin EnchantmentAPI = Bukkit.getServer().getPluginManager().getPlugin("EnchantmentAPI");
		if (EnchantmentAPI != null){
				EnchantmentAPI_ = true;
		}
		ConfigHandler_ = new ConfigHandler(this);
		LC = new LoggingClass("main");
	}
	
	@Override
	public void onEnable(){
		
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(EnchantmentAPI_){
			LC.Log("Enchantment API support has been enabled");
		}
		else{
			LC.Log("Enchantment API support has been disabled");
		}
		
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		LC.Log(sender.getName() + " executed " + cmd.getName());
		

		if (cmd.getName().equalsIgnoreCase("item")) {
			
			new ShowItem(sender, args, EnchantmentAPI_);

			return true;
		} else
			return false;

	}

}
