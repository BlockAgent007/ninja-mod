package net.mcreator.ninja_mod;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

@Elementsninja_mod.ModElement.Tag
public class MCreatorNinjatimeTrigger extends Elementsninja_mod.ModElement {
	public MCreatorNinjatimeTrigger(Elementsninja_mod instance) {
		super(instance, 7);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorNinjatimeTrigger!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(3) : ItemStack.EMPTY).getItem() == new ItemStack(
				MCreatorNinjaArmor.helmet, (int) (1)).getItem()) && (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.armorInventory.get(2)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorNinjaArmor.body, (int) (1)).getItem())) && (((((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.armorInventory.get(1)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorNinjaArmor.legs, (int) (1)).getItem()) && (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.armorInventory.get(0)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorNinjaArmor.boots, (int) (1)).getItem())) && (((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer))
				? ((EntityPlayerMP) entity)
						.getAdvancements()
						.getProgress(
								((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjaquest")))
						.isDone()
				: false)))) {
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager().getAdvancement(
						new ResourceLocation("ninja_mod:ninjatime"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
