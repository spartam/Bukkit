package be.infogroep.ItemShare;

public class EchantementConverter {
	
	private String Root_ = "Enchantements";
	private LoggingClass LC = new LoggingClass("EnchantementConvert");
	
	EchantementConverter(){
		
	}
	
	public String Convert(String Enchant){
		String Result = ItemShare.ConfigHandler_.Config_.getConfigurationSection(Root_).getString(Enchant);
		LC.Log("Converting");
		LC.Log(Enchant + " - " + Result);
		if(Result == null){
			LC.Log("Entered null");
			ItemShare.ConfigHandler_.SaveEnchant(Enchant);
			Result = Enchant;
		}
		return Result;
	}
}
