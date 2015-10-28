package xyz.olympiccode.plugins.API.Funções;

import org.bukkit.plugin.java.JavaPlugin;

public class Updater
  implements Runnable
{
  private JavaPlugin _plugin;
  
  public Updater(JavaPlugin paramJavaPlugin)
  {
    this._plugin = paramJavaPlugin;
    this._plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this._plugin, this, 0L, 1L);
  }
  
  public void run()
  {
    UpdateType[] arrayOfUpdateType;
    int j = (arrayOfUpdateType = (UpdateType[])UpdateType.class.getEnumConstants()).length;
    for (int i = 0; i < j; i++)
    {
      UpdateType localUpdateType = arrayOfUpdateType[i];
      if (localUpdateType.Elapsed()) {
        this._plugin.getServer().getPluginManager().callEvent(new UpdateEvent(localUpdateType));
      }
    }
  }
}
