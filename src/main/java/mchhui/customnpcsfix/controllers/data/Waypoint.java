package mchhui.customnpcsfix.controllers.data;

import mchhui.customnpcsfix.Config;
import mchhui.customnpcsfix.client.gui.HueihueaGuiQuestEdit;
import mchhui.customnpcsfix.client.gui.HueihueaGuiQuestWaypoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import noppes.npcs.controllers.data.Quest;

public class Waypoint {
    public int questID = 0;
    public String displayName = "";
    public int worldDIM;
    public String worldName = "world";
    public String setName = "gui.xaero_default";
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public boolean isEnabled = false;

    public Waypoint() {
        // TODO 自动生成的构造函数存根
    }

    public Waypoint bind(Quest quest) {
        questID = quest.id;
        displayName = quest.getName();
        return this;
    }

    public void readNBT(NBTTagCompound compound) {
        if (compound.isEmpty()) {
            return;
        }
        questID = compound.getInteger("QuestID");
        displayName = compound.getString("DisplayName");
        worldDIM = compound.getInteger("WorldDIM");
        worldName = compound.getString("WorldName");
        setName = compound.getString("SetName");
        x = compound.getInteger("x");
        y = compound.getInteger("y");
        z = compound.getInteger("z");
        isEnabled = compound.getBoolean("IsEnabled");
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("QuestID", questID);
        nbt.setString("DisplayName", displayName);
        nbt.setString("WorldName", worldName);
        nbt.setString("SetName", setName);
        nbt.setInteger("x", x);
        nbt.setInteger("y", y);
        nbt.setInteger("z", z);
        nbt.setBoolean("IsEnabled", isEnabled);
        if (!Config.QuestWaypointFromWorldName
                || (FMLCommonHandler.instance().getSide() == Side.CLIENT && HueihueaGuiQuestWaypoint.isFromDIM)) {
            writeNbtWithDIM(nbt);
        }
        return nbt;
    }

    public NBTTagCompound writeNbtWithDIM(NBTTagCompound nbt) {
        nbt.setInteger("WorldDIM", worldDIM);
        return nbt;
    }

    public NBTTagCompound writeNbtWithDIM() {
        return writeNbtWithDIM(writeNBT());
    }
}
