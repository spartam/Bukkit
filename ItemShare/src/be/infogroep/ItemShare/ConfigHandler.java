package be.infogroep.ItemShare;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

	private ItemShare itemshare_ = null;
	private File configFile_ = null;
	private String EnchantRoot_ = "Enchantements";
	
	public FileConfiguration Config_ = null;

	ConfigHandler(ItemShare itemshare) {

		itemshare_ = itemshare;
		File DataFolder_ = itemshare_.getDataFolder();
		configFile_ = new File(DataFolder_ + "/config.yml");
		Config_ = itemshare_.getConfig();
		CreateOnMissing();
		
		
	}

	public boolean CreateOnMissing() {
		if (!configFile_.exists()) {
			//if(!itemshare_.getDataFolder().exists()){
			//	itemshare_.getDataFolder().mkdir();
			//}
			itemshare_.saveDefaultConfig();
			Config_.set("Logging", true);
			Config_.set("LogToFile", false);
			itemshare_.saveConfig();
//			try {
				Config_.createSection(EnchantRoot_);
				Config_.getConfigurationSection(EnchantRoot_).set("ARROW_INFINITE", "Infinity");
				itemshare_.saveConfig();
//			} catch (IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}

		if (!configFile_.exists())
			return false;
		else{
			itemshare_.reloadConfig();
			return true;
		}
	}
	
	public Object GetValue(String key){
		return itemshare_.getConfig().get(key);
	}
	
	public void SaveEnchant(String Enchant){
		Bukkit.getLogger().info("SaveEnchant: " + Enchant);
		itemshare_.getConfig().getConfigurationSection(EnchantRoot_).set(Enchant, Enchant);
		Config_ = itemshare_.getConfig();

		itemshare_.saveConfig();
	}

}
