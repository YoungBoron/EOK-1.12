package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.client.gui.GUIElementaryResearchTable;
import com.gonggongjohn.eok.client.gui.GUIRefractingTelescope;
import com.gonggongjohn.eok.inventory.ContainerElementaryResearchTable;
import com.gonggongjohn.eok.inventory.ContainerRefractingTelescope;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
    public static final int GUIRefractingTelescope = 1;
    public static final int GUIElementaryResearchTable = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GUIRefractingTelescope:
                return new ContainerRefractingTelescope(player);
            case GUIElementaryResearchTable:
                return new ContainerElementaryResearchTable(player);
            default:
                return null;

        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GUIRefractingTelescope:
                return new GUIRefractingTelescope(new ContainerRefractingTelescope(player));
            case GUIElementaryResearchTable:
                return new GUIElementaryResearchTable(new ContainerElementaryResearchTable(player));
            default:
                return null;
        }
    }
}
