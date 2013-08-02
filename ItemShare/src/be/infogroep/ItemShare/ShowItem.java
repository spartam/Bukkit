package be.infogroep.ItemShare;

import java.util.Collection;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

public class ShowItem {

	private int header = 5;
	private boolean potion = false;

	private Player receiver_ = null;
	private Player sender_;
	private ItemStack item_;
	private ItemMeta meta_;
	private String itemName_;
	private String itemType_;
	private EnchantementHandler EH;

	ShowItem(CommandSender sender, String[] msg, Boolean EnchantmentAPIEnabled) {
		Bukkit.getLogger().info("ShowItem constructor");
		
		if(EnchantmentAPIEnabled)
			Bukkit.getLogger().info("EnchantementAPI is enabled");
		else
			Bukkit.getLogger().info("EnchantementAPI is disabled");

		if (msg.length > 0) {
			String name = msg[0];
			receiver_ = Bukkit.getServer().getPlayer(name);
			Bukkit.getLogger().info("Given name: " + name);
		}else Bukkit.getLogger().info("Broadcast to all");

		sender_ = (Player) sender;
		item_ = sender_.getItemInHand();

		meta_ = item_.getItemMeta();
		
		Map<Enchantment, Integer> enchants = null;
		Collection<PotionEffect> effects = null;

		Material material_ = item_.getType();
		itemType_ = material_.name().replace("_", " ").toLowerCase();
		
		Bukkit.getLogger().info("ID: " + material_.getId());

		if (material_.getId() == 387) {// special case book
			BookMeta BM = (BookMeta) meta_;
			itemName_ = BM.getTitle() + " - " + BM.getAuthor();

		}
		else if (material_.getId() == 403) {// special case enchanted book
			EnchantmentStorageMeta ESM = (EnchantmentStorageMeta) meta_;
			enchants = ESM.getStoredEnchants();
		}

		else if (material_.getId() == 373) {// special case potions
			potion = true;
			PotionMeta PM = (PotionMeta) meta_;
			if (PM.hasDisplayName())
				itemName_ = PM.getDisplayName();
			Potion P = new Potion(item_.getDurability());
			effects = P.getEffects();
			if (P.isSplash()) {
				itemName_ = "Splash: " + itemName_;
			}
		}

		else if (material_.getId() == 35) {// special case wool
			Wool W = new Wool(item_.getType(), item_.getData().getData());
			itemType_ = W.getColor().name().toLowerCase() + " " + itemType_;
		}

		if (material_.getId() == 351) {// special case dye
			Dye D = new Dye(item_.getType(), item_.getData().getData());
			itemType_ = D.getColor().name().toLowerCase() + " dye";
		}
		// Recipe R = ShapedRecipe(item_);
		Bukkit.getLogger().info("Handeled special case materials");
		
		
		int EnchantsSize = 0;
		if (enchants != null) {
			Bukkit.getLogger().info("Enchants was not null");
			EnchantsSize = enchants.size();
		}
		else Bukkit.getLogger().info("Enchants was null");
			

		int EnchantsHeaderSize = 1;
		if (EnchantsSize > 0)
			EnchantsHeaderSize = EnchantsSize;
		else if (effects != null) {
			if (effects.size() != 0)
				EnchantsHeaderSize = effects.size();
		} else {
			EH = new EnchantementHandler(item_, EnchantmentAPIEnabled);
			if (EH.Size() > 0) {
				EnchantsSize = EH.Size();
				EnchantsHeaderSize = EnchantsSize;
			}
		}

		if (meta_ != null) {
			if (meta_.hasDisplayName())
				itemName_ = meta_.getDisplayName();
			else
				itemName_ = item_.getType().name();
		} else
			itemName_ = // item_.getType().name();
			item_.getData().getClass().getName();

		Bukkit.getLogger().info("Starting to create message");
		
		String[] message = new String[header + EnchantsHeaderSize];
		message[0] = "§5" + sender.getName() + ": §6shared an item";
		message[1] = "§aItem name: §b" + itemName_;
		message[2] = "§aType: §b" + itemType_;
		if (potion)
			message[3] = "§aEffect: ";
		else
			message[3] = "§aEnchanments: ";

		int index = 4;

		if (EnchantsSize > 0) {

			if (EH != null) {
				Bukkit.getLogger().info("Found EnchantementHandler");
				for (String CurrentLine : EH.ToStringArray()) {
					message[index] = CurrentLine;
					index = index + 1;
				}
			} else {
				Bukkit.getLogger().info("No EnchantementHandler");
				for (Map.Entry<Enchantment, Integer> current : enchants
						.entrySet()) {
					message[index] = current.getKey().getName() + " Level: "
							+ current.getValue();
					index = index + 1;
				}
			}

		} else if (effects != null) {
			if (!effects.isEmpty()) {
				for (PotionEffect P : effects) {
					String type = P.getType().getName();
					int time = P.getDuration();
					int seconds = time / 20;
					int minutes = seconds / 60;
					seconds = seconds - (minutes * 60);
					message[index] = type
							+ ": "
							+ ((new Potion(item_.getData().getData())
									.hasExtendedDuration()) ? "EXTENDED"
									: "NORMAL");
					index = index + 1;
				}
			} else {
				message[index] = "None";
				index = index + 1;
			}
		} else {
			message[index] = "None";
			index = index + 1;
		}
		message[index] = "§a-----------------";

		// Bukkit.getServer().broadcastMessage(
		// String.valueOf(item_.getDurability()));

		if (null == receiver_) {
			if (msg.length == 0) {
				Bukkit.getServer().broadcastMessage("§a§nPublicly shared item");
				for (String s : message) {
					Bukkit.getServer().broadcastMessage(s);
				}

			} else {
				sender.sendMessage("§ccould not find player: " + msg[0]);
			}
		} else {
			receiver_.sendMessage(message);
			sender.sendMessage("§asuccesfully shared your item with " + msg[0]);
		}
	}

}
