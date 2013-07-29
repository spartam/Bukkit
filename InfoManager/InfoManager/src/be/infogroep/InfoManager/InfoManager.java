package be.infogroep.InfoManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class InfoManager extends JavaPlugin{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (cmd.getName().equalsIgnoreCase("infomanager")){
			
		}
		
		return true;
	}
	
}
