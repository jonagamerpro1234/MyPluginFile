package my.pluginfile;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main instance;
	public PlayerFile playerFile = new PlayerFile(this, "player.yml", "Data");
	
	public void onEnable() {
		System.out.println("Enable Test Plugin");
		
		instance = this;
		
		createVoidFolder("Data");
		playerFile.create();
	}
	
	public void onDisable() {
		
	}
	
    public void createVoidFolder(String name) {
        File folder = new File(getDataFolder(), name);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
	
    public FileConfiguration getPlayerConfig() {
    	return this.playerFile.getConfig();
    }
    
    
    
}
