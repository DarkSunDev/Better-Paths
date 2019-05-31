package darksun.betterpaths.blocks;

import darksun.betterpaths.BetterPaths;
import darksun.betterpaths.lists.BlockList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StonePath extends BlockCarpet
{
	public StonePath()
	{
		super
		(
			EnumDyeColor.WHITE,
			Block.Properties.create(Material.IRON)
				.hardnessAndResistance(1, 10)
				.sound(SoundType.STONE)
		);
	}

	
}
