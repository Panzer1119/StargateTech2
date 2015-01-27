package lordfokas.stargatetech2.modules.core.machine__TRASH;

import lordfokas.stargatetech2.lib.tileentity.IOwnedMachine;
import lordfokas.stargatetech2.modules.integration.te4.CoFHFriendHelper;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileOwnedMachine__THRASH extends TileMachine__THRASH implements IOwnedMachine{
	private static final String NO_OWNER = "__*";
	
	public enum AccessMode{
		PRIVATE, FRIENDS, PUBLIC
	}
	
	private AccessMode mode = AccessMode.PRIVATE;
	private String owner = NO_OWNER;
	
	public final AccessMode getAccessMode(){
		return mode;
	}
	
	public final void setAccessMode(AccessMode mode){
		this.mode = mode;
	}
	
	@Override
	public final String getOwner() {
		return owner;
	}

	@Override
	public final void setOwner(String owner) {
		if(this.owner.equals(NO_OWNER))
			this.owner = owner;
	}

	@Override
	public boolean hasAccess(String player) {
		if(!owner.equals(NO_OWNER)){
			switch(mode){
				case FRIENDS:
					if(CoFHFriendHelper.isSystemEnabled()){
						return player.equalsIgnoreCase(owner) || CoFHFriendHelper.isFriend(player, owner);
					}
				case PRIVATE:
					return player.equalsIgnoreCase(owner);
				case PUBLIC:
					return true;
			}
		}
		return true;
	}
	
	protected final void writeOwnedMachineData(NBTTagCompound nbt){
		nbt.setString("__owner", owner);
		nbt.setInteger("__mode", mode.ordinal());
	}
	
	protected final void readOwnedMachineData(NBTTagCompound nbt){
		owner = nbt.getString("__owner");
		mode = AccessMode.values()[nbt.getInteger("__mode")];
	}
}