package my.pluginfile;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {
	
	private Main plugin;
	private String path;
	private File file;
	private FileConfiguration config;
	
	public ConfigFile(Main plugin, String path) {
		this.plugin = plugin;
		this.path = path;
		this.file = null;
		this.config = null;
	}
	
    public void create() {
        this.file = new File(plugin.getDataFolder(), this.path);
        if(!this.file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getConfig() {
        if(this.config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void reloadConfig() {
        if(this.config == null) {
            this.file = new File(plugin.getDataFolder(), this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(plugin.getResource(this.path), "UTF8");
            if(defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        }catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
       
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void saveDefaultConfig() {
        if(this.file == null) {
            this.file = new File(plugin.getDataFolder(), this.path);
        }
        if(!this.file.exists()) {
        	plugin.saveResource(this.path, false);
        }
    }
    
    public String getPath() {
        return this.path;
    }
    
}
