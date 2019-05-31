package darksun.betterpaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import darksun.betterpaths.blocks.StonePath;
import darksun.betterpaths.lists.BlockList;
import darksun.betterpaths.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("betterpaths")
public class BetterPaths
{
	public static BetterPaths instance;
	public static final String modid = "betterpaths";
	public static final Logger logger = LogManager.getLogger(modid);
	public static IWorld world;
	public static final float SPEED_FACTOR = 1.11f;
	public static final float DEFAULT_SPEED_MAGNITUDE = .3f;
	
	public BetterPaths()
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(FMLCommonSetupEvent event)
	{
		logger.info("Setup method registered.");
	}
	private void clientRegistries(FMLClientSetupEvent event)
	{
		logger.info("clientRegistries method registered.");
	}

	/*
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		//BetterPaths.world = event.getWorld();
	}
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		return;
		Block downBlock = world.getBlockState(event.player.getPosition()).getBlock();
		if (downBlock == BlockList.stone_path_block)
		{
			if (event.player.motionY > 0)
			{
				double x = event.player.motionX;
				double z = event.player.motionZ;
				double magnitude = Math.sqrt(x*x + z*z);
				if (magnitude <= DEFAULT_SPEED_MAGNITUDE)
					return;
				
				event.player.motionX = x * DEFAULT_SPEED_MAGNITUDE / magnitude;
				event.player.motionZ = z * DEFAULT_SPEED_MAGNITUDE / magnitude;
				
				return;
			}

			event.player.motionX *= SPEED_FACTOR;
			event.player.motionZ *= SPEED_FACTOR;
		}
	}
	*/

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event)
		{
			// register items here
			event.getRegistry().registerAll
			(
				// regular items
				ItemList.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("tutorial_item")),
				
				// item blocks
				ItemList.stone_path_block = new ItemBlock(BlockList.stone_path_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.stone_path_block.getRegistryName())
			);
			logger.info("Items registered.");
		}
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
					BlockList.stone_path_block = new StonePath().setRegistryName(location("stone_path_block"))
			);
				
			logger.info("Blocks registered");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(modid, name);
		}
	}	
}
