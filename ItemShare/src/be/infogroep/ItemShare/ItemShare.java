package be.infogroep.ItemShare;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemShare extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (cmd.getName().equalsIgnoreCase("item")) {

			new ShowItem(sender, args);

			return true;
		} else
			return false;

	}

}
