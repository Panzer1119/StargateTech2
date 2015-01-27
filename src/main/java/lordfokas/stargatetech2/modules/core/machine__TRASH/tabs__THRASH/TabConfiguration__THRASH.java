package lordfokas.stargatetech2.modules.core.machine__TRASH.tabs__THRASH;

import lordfokas.stargatetech2.modules.core.base__THRASH.BaseTab__OLD_AND_FLAWED;
import lordfokas.stargatetech2.modules.core.machine__TRASH.FaceColor__THRASH;
import lordfokas.stargatetech2.modules.core.machine__TRASH.Face__THRASH;
import lordfokas.stargatetech2.modules.core.machine__TRASH.TileMachine__THRASH;
import lordfokas.stargatetech2.modules.core.packets__THRASH.PacketToggleMachineFace__THRASH;
import lordfokas.stargatetech2.reference.TextureReference;
import lordfokas.stargatetech2.util.IconRegistry;
import lordfokas.stargatetech2.util.Stacks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

public class TabConfiguration__THRASH extends BaseTab__OLD_AND_FLAWED{
	private static final Side[] sides = new Side[]{
		new Side(Face__THRASH.BOTTOM, 45, 61),
		new Side(Face__THRASH.TOP, 45, 25),
		new Side(Face__THRASH.LEFT, 27, 43),
		new Side(Face__THRASH.FRONT, 45, 43),
		new Side(Face__THRASH.RIGHT, 63, 43),
		new Side(Face__THRASH.BACK, 63, 61)
	};
	
	private static class Side{
		public final int x, y;
		public final Face__THRASH face;
		
		public Side(Face__THRASH face, int x, int y){
			this.face = face;
			this.x = x;
			this.y = y;
		}
	}
	
	private final IIcon side, top, bottom;
	private final TileMachine__THRASH machine;
	
	public TabConfiguration__THRASH(TileMachine__THRASH machine) {
		super("Configuration", Stacks.circuit, TabColor.RED);
		this.machine = machine;
		side = IconRegistry.blockIcons.get(TextureReference.MACHINE_SIDE_I);
		top = IconRegistry.blockIcons.get(TextureReference.MACHINE_TOP_I);
		bottom = IconRegistry.blockIcons.get(TextureReference.MACHINE_BOTTOM_I);
	}

	@Override
	public int getSizeY() {
		return 80;
	}

	@Override
	public void render() {
		gui.drawDarkerArea(getColor(), 23, 21, 60, 60);
		for(int i = 0; i < 6; i++){
			Side s = sides[i];
			FaceColor__THRASH color = machine.getColor(s.face);
			if(!color.isColored()){
				gui.drawIcon(s.x, s.y, machine.getBlockType().getIcon(i, 0), TextureMap.locationBlocksTexture, 16);
			}else{
				IIcon icon;
				switch(s.face){
				case BOTTOM:
					icon = bottom;
					break;
				case TOP:
					icon = top;
					break;
				default:
					icon = side;
					break;
				}
				gui.drawIcon(s.x, s.y, icon, TextureMap.locationBlocksTexture, 16);
				gui.drawIcon(s.x, s.y, IconRegistry.blockIcons.get(color.getTexture()), TextureMap.locationBlocksTexture, 16);
			}
		}
	}
	
	@Override
	public boolean handleClick(int x, int y){
		for(Side side : sides){
			if(elementHit(side.x, side.y, x, y, 16, 16)){
				gui.playClick();
				PacketToggleMachineFace__THRASH packet = new PacketToggleMachineFace__THRASH();
				packet.x = machine.xCoord;
				packet.y = machine.yCoord;
				packet.z = machine.zCoord;
				// packet.face = side.face; // this is thrash anyways.
				packet.sendToServer();
			}
		}
		return !elementHit(23, 21, x, y, 60, 60);
	}
}