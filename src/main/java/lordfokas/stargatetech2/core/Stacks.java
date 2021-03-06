package lordfokas.stargatetech2.core;

import lordfokas.stargatetech2.ModuleAutomation;
import lordfokas.stargatetech2.ModuleCore;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Stacks {
	// Vanilla Stacks
	public static ItemStack book, bucket, chest, glass, glowDust, glowBlock, pearl, redDust, redBlock, slab, stick, stone;
	
	// ThermalExpansion Stacks
	public static ItemStack machine_0, machine_1, machine_2, signalum, coilGold;
	
	// StargateTech 2 Stacks
	public static ItemStack naqIngot, naqDust, naqPlate, naqOre, naqBlock, lattice, circuit, coilNaq, coilEnd, busCable;
	
	public static void init(){
		//##########################################################################################
		// VANILLA
		book = new ItemStack(Items.book);
		bucket = new ItemStack(Items.bucket);
		chest = new ItemStack(Blocks.chest);
		glass = new ItemStack(Blocks.glass_pane);
		glowBlock = new ItemStack(Blocks.glowstone);
		glowDust = new ItemStack(Items.glowstone_dust);
		pearl = new ItemStack(Items.ender_pearl);
		redBlock = new ItemStack(Blocks.redstone_block);
		redDust = new ItemStack(Items.redstone);
		slab = new ItemStack(Blocks.stone_slab);
		stick = new ItemStack(Items.stick);
		stone = new ItemStack(Blocks.stone);
		
		//##########################################################################################
		// THERMAl EXPANSION 4
		machine_0 =	fromTE4("frameMachineBasic");
		machine_1 =	fromTE4("frameMachineHardened");
		machine_2 =	fromTE4("frameMachineReinforced");
		signalum = GameRegistry.findItemStack("ThermalFoundation", "ingotSignalum", 1);
		coilGold =	fromTE4("powerCoilGold");
		
		//##########################################################################################
		// STARGATETECH 2
		naqIngot	= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.INGOT.ID);
		naqDust		= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.DUST.ID);
		naqPlate	= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.PLATE.ID);
		naqOre		= new ItemStack(ModuleCore.naquadahBlock, 1, BlockNaquadah.ORE);
		naqBlock	= new ItemStack(ModuleCore.naquadahBlock, 1, BlockNaquadah.BLOCK);
		circuit		= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.CIRCUIT.ID);
		coilNaq		= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.COIL_NAQ.ID);
		coilEnd		= new ItemStack(ModuleCore.naquadahItem, 1, ItemNaquadah.COIL_END.ID);
		busCable	= new ItemStack(ModuleAutomation.busCable);
	}
	
	private static ItemStack fromTE4(String name){
		return GameRegistry.findItemStack("ThermalExpansion", name, 1);
	}
}