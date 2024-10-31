package mchhui.customnpcsfix.util;

import noppes.npcs.controllers.data.TransportLocation;

public class BukkitHelper {
    public static boolean teleport(String playerName, String worldName, TransportLocation loc) {
        try {
            Class<?> bukkitClass = Class.forName("org.bukkit.Bukkit");
            Object getWorld = bukkitClass.getDeclaredMethod("getWorld").invoke(worldName);

            Object getPlayer = bukkitClass.getDeclaredMethod("getPlayer", String.class).invoke(null, playerName);
            Class<?> locationClass = Class.forName("org.bukkit.Location");
            Object o = locationClass
                    .getDeclaredConstructor(getWorld.getClass(), int.class, int.class, int.class)
                    .newInstance(getWorld, loc.getX(), loc.getY(), loc.getZ());
            return (boolean) getPlayer.getClass().getDeclaredMethod("teleport", o.getClass()).invoke(getPlayer, o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isBukkitAlive() {
        try {
            Class.forName("org.bukkit.Bukkit");
        } catch (ClassNotFoundException err) {
            return false;
        }
        return true;
    }
}
