package my.pluginfile;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MyCommand implements CommandExecutor{
	
	private Main plugin;
	
	public MyCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("MyCommand").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player player = (Player) sender;
		
		if(player.hasPermission("MyPlugin.Command")) {
			plugin.reloadConfig();
			player.sendMessage(config.getString("Reload"));
		}else {
			player.sendMessage(config.getString("NoPermission"));
		}
		return true;
	}
	
}
