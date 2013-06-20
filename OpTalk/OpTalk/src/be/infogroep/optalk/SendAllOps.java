package be.infogroep.optalk;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendAllOps {
	SendAllOps(CommandSender sender, String label, String message) {
		
		Bukkit.getLogger().info("Entered SendAllOps");
		message = "§3" + sender.getName() + ": §a" + message;
		
		Bukkit.getLogger().info(message);

		try {
			for(Player P : Bukkit.getServer().getOnlinePlayers()){
				Bukkit.getLogger().info(P.getName());
				if(P.isOp()){
					P.sendMessage(message);
				}
			}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

}
