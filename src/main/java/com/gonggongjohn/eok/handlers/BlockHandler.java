package com.gonggongjohn.eok.handlers;

import java.util.ArrayList;
import java.util.List;

import com.gonggongjohn.eok.blocks.BlockBasketBall;
import com.gonggongjohn.eok.blocks.BlockDecomposedHaystack;
import com.gonggongjohn.eok.blocks.BlockDriedHaystack;
import com.gonggongjohn.eok.blocks.BlockElementaryResearchTable;
import com.gonggongjohn.eok.blocks.BlockFirstMachine;
import com.gonggongjohn.eok.blocks.BlockHayTorchBase;
import com.gonggongjohn.eok.blocks.BlockHayTorchBaseLit;
import com.gonggongjohn.eok.blocks.BlockHaystack;
import com.gonggongjohn.eok.blocks.BlockStick;
import com.gonggongjohn.eok.blocks.BlockStoneRock;
import com.gonggongjohn.eok.blocks.BlockStoneTable;
import com.gonggongjohn.eok.blocks.BlockTest2DCore;
import com.gonggongjohn.eok.blocks.BlockTest3DCore;
import com.gonggongjohn.eok.blocks.BlockTwoBarrelVacuumPump;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BlockHandler {
	public static final List<Block> BLOCK_REGISTRY = new ArrayList<Block>();

	public static final Block blockStoneTable = new BlockStoneTable();
	public static final Block blockTwoBarrelVacuumPump = new BlockTwoBarrelVacuumPump();
	public static final Block blockElementaryResearchTable = new BlockElementaryResearchTable();
	public static final Block blockFirstMachine = new BlockFirstMachine();
	public static final Block blockHaystack = new BlockHaystack();
	public static final Block blockDriedHaystack = new BlockDriedHaystack();
	public static final Block blockDecomposedHaystack = new BlockDecomposedHaystack();
	public static final Block blockHayTorchBase = new BlockHayTorchBase();
	public static final Block blockHayTorchBaseLit = new BlockHayTorchBaseLit();
	public static final Block blockStoneRock = new BlockStoneRock();
	public static final Block blockStick = new BlockStick();
	public static final Block blockBasketBall = new BlockBasketBall();

	public static final Block blockTest2DCore = new BlockTest2DCore();
	public static final Block blockTest3DCore = new BlockTest3DCore();

	public static void registerBlock(Block block, Item item) {
		BLOCK_REGISTRY.add(block);
		ItemHandler.ITEM_REGISTRY.add(item);
	}

	@SubscribeEvent
	public static void onBlockRegister(Register<Block> e) {
		e.getRegistry().registerAll(BLOCK_REGISTRY.toArray(new Block[0]));
	}
}