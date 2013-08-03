package be.infogroep.ItemShare;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class LoggingClass {

	private String LogHeader_ = "[ItemShare]";
	private String Class_ = "";
	private Boolean Logging = true;
	private Boolean LogToFile = false;
	private String LogFile_ = null;
	Writer Stream = null;

	LoggingClass(String ClassFile) {
		Class_ = "[" + ClassFile + "] ";

		ConfigHandler CH = ItemShare.ConfigHandler_;

		if (CH == null) {
			Bukkit.getLogger().info(LogHeader_ + "Confighandler failure caused loging to keep default values");
		} else {
			Logging = CH.Config_.getConfigurationSection("General").getBoolean("Logging");
			LogToFile = CH.Config_.getConfigurationSection("General").getBoolean("LogToFile");
			LogFile_ = CH.DataFolder_ + "/" + "LogFile";
		}
		
		if(!LogToFile){
			if(new File(LogFile_).exists()){
				new File(LogFile_).delete();
			}
				
		}
	}

	public void Log(String msg) {
		
		if (Logging)
			Bukkit.getLogger().info(LogHeader_ + Class_ + msg);
		if (LogToFile)
			try {
				if(LogFile_ != null)
				Stream = new FileWriter(LogFile_, true);
				
				Stream.append(LogHeader_ + Class_ + msg);
				Stream.append("\r\n");
				Stream.flush();
				Stream.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
