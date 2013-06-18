package be.infogroep.optalk;

//import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public class optalk extends JavaPlugin{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
		if(cmd.getName().equalsIgnoreCase("o")){
			String msg = "";
			for(String s: args){
				msg = msg + s + " ";
			}
			
			new SendAllOps(sender, label, msg);
			// execute whisper all ops
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("item")){
			
			new ItemShare(sender, args);
			
			return true;
		}
		else	
			return false;
	
	}

}
