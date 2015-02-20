package lordfokas.stargatetech2.modules.enemy.tileentity;

import lordfokas.stargatetech2.api.shields.ShieldPermissions;
import lordfokas.stargatetech2.lib.tileentity.ISyncedGUI;
import lordfokas.stargatetech2.lib.tileentity.ITile;
import lordfokas.stargatetech2.lib.tileentity.ITileContext;
import lordfokas.stargatetech2.lib.tileentity.component.IComponentProvider;
import lordfokas.stargatetech2.lib.tileentity.component.IComponentRegistrar;
import lordfokas.stargatetech2.lib.tileentity.component.base.BasicFluidFilter;
import lordfokas.stargatetech2.lib.tileentity.component.base.BusComponent;
import lordfokas.stargatetech2.lib.tileentity.component.base.TankComponentFiltered;
import lordfokas.stargatetech2.lib.tileentity.faces.FaceColor;
import lordfokas.stargatetech2.lib.tileentity.faces.FaceColorFilter;
import lordfokas.stargatetech2.modules.enemy.IonizedParticles;
import lordfokas.stargatetech2.modules.enemy.ShieldControllerBusDriver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class ShieldControllerCommon implements ITileContext, ISyncedGUI.Flow, IComponentProvider{
	private static final int[] KEYS = new int[]{0, 1, 2};
	protected FluidTank tank = new FluidTank(16000);
	protected ShieldPermissions permissions = ShieldPermissions.getDefault();
	protected ShieldControllerBusDriver driver = new ShieldControllerBusDriver(this);
	protected boolean active;
	protected boolean enabled;
	private ITile tile;
	
	@Override
	public void registerComponents(IComponentRegistrar registrar) {
		TankComponentFiltered tankComponent = new TankComponentFiltered(tank, false, new BasicFluidFilter(IonizedParticles.fluid));
		BusComponent busComponent = new BusComponent(driver);
		
		registrar.registerComponent(tankComponent.setInputFilter(new FaceColorFilter.MatchColors(FaceColor.BLUE)));
		registrar.registerComponent(busComponent.setGenericAccessFilter(new FaceColorFilter.MatchColors(FaceColor.BLUE)));
	}
	
	public void readNBTData(NBTTagCompound nbt) {
		permissions = ShieldPermissions.readFromNBT(nbt.getCompoundTag("permissions"));
		active = nbt.getBoolean("active");
		enabled = nbt.getBoolean("enabled");
	}
	
	public void writeNBTData(NBTTagCompound nbt) {
		nbt.setTag("permissions", permissions.writeToNBT());
		nbt.setBoolean("active", active);
		nbt.setBoolean("enabled", enabled);
	}
	
	@Override
	public int[] getKeyArray() {
		return KEYS;
	}
	@Override
	public int getValue(int key) {
		switch(key){
			case 0: return tank.getFluidAmount();
			case 1: return active ? 1 : 0;
			case 2: return enabled ? 1 : 0;
		}
		return -1;
	}
	@Override
	public void setValue(int key, int val) {
		switch(key){
			case 0:
				tank.setFluid(new FluidStack(IonizedParticles.fluid, val));
				break;
			case 1:
				active = (val == 1);
				break;
			case 2:
				enabled = (val == 1);
				break;
		}
	}

	@Override public void tick(){}
	@Override public boolean canTick() { return false; }
	
	@Override
	public void setTile(ITile tile){
		this.tile = tile;
	}
	
	public boolean isShieldOn(){
		return active;
	}
	
	public ShieldPermissions getPermissions(){
		return permissions;
	}
	
	// is just a stub for the server.
	public void setShieldStatus(boolean enabled){}
}