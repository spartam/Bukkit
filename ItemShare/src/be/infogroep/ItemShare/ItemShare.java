package be.infogroep.ItemShare;


import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemShare extends JavaPlugin {
	
	private boolean EnchantmentAPI_ = false;
	
	public void onLoad(){
		Plugin EnchantmentAPI = Bukkit.getServer().getPluginManager().getPlugin("EnchantmentAPI");
		if (EnchantmentAPI != null){
				EnchantmentAPI_ = true;
		}
	}
	
	public void onEnable(){
		Bukkit.getLogger().info("spartam created: ");
		Bukkit.getLogger().info("ItemShare");
		
		if(EnchantmentAPI_){
			Bukkit.getLogger().info("Enchantment API has been enabled");
		}
		else{
			Bukkit.getLogger().info("Enchantment API has been disabled");
		}
		
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		Bukkit.getLogger().info(sender.getName() + " executed " + cmd.getName());
		

		if (cmd.getName().equalsIgnoreCase("item")) {
			
			new ShowItem(sender, args, EnchantmentAPI_);

			return true;
		} else
			return false;

	}

}
