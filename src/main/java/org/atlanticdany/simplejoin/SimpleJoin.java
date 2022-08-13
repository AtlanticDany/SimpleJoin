package org.atlanticdany.simplejoin;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;

import java.io.*;

public class SimpleJoin extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info(TextFormat.BLUE + "SimpleJoin" + " " + TextFormat.GREEN + "Plugin Enabled!");
        this.getServer().getPluginManager().registerEvents(this, this);
        Config config = new Config(
                new File(this.getDataFolder(), "config.yml"),
                Config.YAML,
                new ConfigSection(
                        "join-message", "Welcome to the server," + " " + "%1"
                )
        );
        if(config.get("join-message") != null){
            this.getLogger().info("Missing join-message on config.yml.");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }

    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Config config = this.getConfig();
        event.setJoinMessage(this.JoinMessage(config.get("join-message", "Player"), player));
    }

    private String JoinMessage(String mess, Player player){
        return mess.replace("%1", player.getName());
    }
}
