package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.tile.TEElementaryResearchTable;
import com.gonggongjohn.eok.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ItemHandler.items.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BlockHandler.blocks.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TEElementaryResearchTable.class, new ResourceLocation(EOK.MODID, "te_elementary_research_table"));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        for(Item item : ItemHandler.items){
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModel();
            }
        }
        for(Block block : BlockHandler.blocks){
            if(block instanceof IHasModel){
                ((IHasModel)block).registerModel();
            }
        }
    }


}
