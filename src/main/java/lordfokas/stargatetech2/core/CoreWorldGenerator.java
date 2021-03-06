package lordfokas.stargatetech2.core;

import java.util.Random;

import lordfokas.stargatetech2.ModuleCore;
import lordfokas.stargatetech2.api.world.EventWorldGen;
import lordfokas.stargatetech2.api.world.EventWorldGen.GenType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class CoreWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int cX, int cZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		if(world.provider.dimensionId == 1 || world.provider.dimensionId == -1 || ConfigServer.wgNaquadah < 1) return;
		if((random.nextInt() % ConfigServer.wgNaquadah) != 0) return;
			
		EventWorldGen event = new EventWorldGen(world, cX, cZ, GenType.VEIN_NAQUADAH);
		MinecraftForge.ORE_GEN_BUS.post(event);
		if (event.getResult() == Result.DENY) return;
		
		WorldGenMinable wgm = new WorldGenMinable(ModuleCore.naquadahBlock, 20);
		wgm.generate(world, random, (cX * 16) + (random.nextInt() % 16), 16 + (random.nextInt() % 20), (cZ * 16) + (random.nextInt() % 16));
	}
}