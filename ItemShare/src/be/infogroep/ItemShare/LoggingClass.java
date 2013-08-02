package be.infogroep.ItemShare;

import org.bukkit.Bukkit;

public class LoggingClass {

	private String LogHeader_ = "[ItemShare]";
	private String Class_ = "";
	private Boolean Logging = true;
	private Boolean LogToFile = false;

	LoggingClass(String ClassFile) {
		Class_ = "[" + ClassFile + "] ";

		ConfigHandler CH = ItemShare.ConfigHandler_;

		if (CH == null) {
			Bukkit.getLogger().info(LogHeader_ + "Confighandler failure caused loging to keep default values");
		} else {
			Logging = CH.Config_.getConfigurationSection("General").getBoolean("Logging");
			LogToFile = CH.Config_.getConfigurationSection("General").getBoolean("LogToFile");
		}
	}

	public void Log(String msg) {
		if (Logging)
			Bukkit.getLogger().info(LogHeader_ + Class_ + msg);
		if (LogToFile)
			Bukkit.getLogger().info(
					LogHeader_ + "LoggingClass: To be implemented");
	}

}
