package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.JDA;

public class DiscordBot extends JavaPlugin {
	
	public static DiscordBot instance;
	public static FileConfiguration config;
	public static JDA api;
	public static String dbversion = "0.2.0 ('Benmore')";
	public static String apiversion = "JDA v1.3.0, Build 188";
	
	@Override
	public void onEnable() {
		instance = this;
		loadConfig();
		getLogger().info("DiscordBot has started!");
	}
	
	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("DiscordBot has stopped!");
	}
	
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			copy(getResource("config.yml"), configFile);
			getLogger().info("Config file created.");
		}
		config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to load config!");
		}
	}
	
	public void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to save config!");
		}
	}
}