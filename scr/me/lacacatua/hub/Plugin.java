package me.maybeitsmike.hub;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class BungeeHub extends JavaPlugin
{
  public void onEnable()
  {
    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    saveDefaultConfig();
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("hub")) || cmd.getName().equalsIgnoreCase("lobby") || cmd.getName().equalsIgnoreCase("salir") {
      if (!(sender instanceof Player)) return true;

      Player p = (Player)sender;

      ByteArrayOutputStream b = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(b);
      try {
        out.writeUTF("Connect");
        out.writeUTF(getConfig().getString("nombre server"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }

    return true;
  }
}
