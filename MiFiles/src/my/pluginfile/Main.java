package my.pluginfile;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private PlayerFile playerFile = new PlayerFile(this, "player.yml", "PlayerFolder");
	private ConfigFile configFile = new ConfigFile(this, "config.yml");

	public void onEnable() {

		configFile.create();

		// This method always has to go first so that it creates the folder before the file
		createFolder("PlayerFolder");
		playerFile.create();
	}

	public void onDisable() {
	}

	public void createFolder(String name) {
		File folder = new File(getDataFolder(), name);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public PlayerFile getPlayerFile() {
		return playerFile;
	}

	public ConfigFile getConfigFile() {
		return configFile;
	}

}

