package be.infogroep.ItemShare;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import com.rit.sucy.*;

public class EnchantementHandler {

	private boolean CustomEnchants_ = false;
	private ItemStack item_ = null;
	private String[] message = null;
	
	private LoggingClass LC = null;

	EnchantementHandler(ItemStack Item, Boolean EnchantmentAPIEnabled) {
		LC = new LoggingClass("EnchantementHandler");
		
		LC.Log("Entered constructor");
		item_ = Item;
		CustomEnchants_ = EnchantmentAPIEnabled;
		CreateMessage();
	}

	private void CreateMessage() {

		LC.Log("Entered CreateMessage");

		if (CustomEnchants_) {

			LC.Log("with custom Enchants");

			Map<CustomEnchantment, Integer> enchants = EnchantmentAPI
					.getAllEnchantments(item_);

			if (enchants.size() > 0) {
				String[] Result = new String[enchants.size()];
				int count = 0;

				for (Map.Entry<CustomEnchantment, Integer> current : enchants
						.entrySet()) {
					Result[count] = new EchantementConverter().Convert(current
							.getKey().name())
							+ " "
							+ new IntegerToRomanConverter().Convert(current
									.getValue());
					count = count + 1;
				}
				message = Result;

			} else {
				message = new String[0];
			}
		} else {

			LC.Log("without custom Enchants");
			Map<Enchantment, Integer> enchants = item_.getEnchantments();

			if (enchants.size() > 0) {
				String[] Result = new String[enchants.size()];
				int count = 0;

				for (Map.Entry<Enchantment, Integer> current : enchants
						.entrySet()) {
					Result[count] = new EchantementConverter().Convert(current
							.getKey().getName())
							+ " "
							+ new IntegerToRomanConverter().Convert(current
									.getValue());
					count = count + 1;
				}
				message = Result;

			} else {
				message = new String[0];
			}

		}
	}

	public int Size() {
		LC.Log("Size = " + message.length);
		return message.length;
	}

	public String[] ToStringArray() {

		LC.Log("ToStringArray");
		return message;
	}

}
