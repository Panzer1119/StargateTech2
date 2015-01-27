package lordfokas.stargatetech2.modules.enemy.tileentity;

import lordfokas.stargatetech2.api.bus.IBusInterface;
import lordfokas.stargatetech2.lib.tileentity.ITile;
import lordfokas.stargatetech2.lib.tileentity.ITileContext;
import lordfokas.stargatetech2.modules.automation.ISyncBusDevice;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;

public class ShieldControllerClient extends ShieldControllerCommon implements ITileContext.Client{
	public boolean hasUpdated;
	private ITile.Client tile;
	
	@Override
	public void readNBTData(NBTTagCompound nbt) {
		super.readNBTData(nbt);
		hasUpdated = true;
	}
	
	@Override public void tick(){}
	@Override public boolean canTick(){ return false; }
	
	@Override
	public void setTile(ITile.Client tile){
		this.tile = tile;
	}
	
	public IFluidTank getTank(){
		return tank;
	}
}