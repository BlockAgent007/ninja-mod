package net.mcreator.ninja_mod;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

@Elementsninja_mod.ModElement.Tag
public class MCreatorSenseiNPCRightClickedOnEntity extends Elementsninja_mod.ModElement {
	public MCreatorSenseiNPCRightClickedOnEntity(Elementsninja_mod instance) {
		super(instance, 9);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorSenseiNPCRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorSenseiNPCRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((entity.getEntityData().getDouble("timesTalked")) == 0)) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: I was the great white ninja. Was."), (false));
			}
			entity.getEntityData().setDouble("timesTalked", 1);
		} else if (((entity.getEntityData().getDouble("timesTalked")) == 1)) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: You are not worthy enough to fight me."), (false));
			}
			entity.getEntityData().setDouble("timesTalked", 2);
		} else if (((entity.getEntityData().getDouble("timesTalked")) == 2)) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: You want me to train you? I won't."), (false));
			}
			entity.getEntityData().setDouble("timesTalked", 3);
		} else if (((entity.getEntityData().getDouble("timesTalked")) == 3)) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: Let the old man rest."), (false));
			}
			entity.getEntityData().setDouble("timesTalked", 4);
		} else if ((((entity.getEntityData().getDouble("timesTalked")) == 4) && (!(((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer))
				? ((EntityPlayerMP) entity)
						.getAdvancements()
						.getProgress(
								((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjaquest")))
						.isDone()
				: false)))) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString(
						"[Sensei]: Fine. I'll teach you. Your first assignment will be to bring me a full set of leather armor."), (false));
			}
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager().getAdvancement(
						new ResourceLocation("ninja_mod:ninjaquest"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		} else if (((entity.getEntityData().getDouble("timesTalked")) == 4)) {
			if ((((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer)) ? ((EntityPlayerMP) entity).getAdvancements()
					.getProgress(((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjatime")))
					.isDone() : false)) {
				if ((!(((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer))
						? ((EntityPlayerMP) entity)
								.getAdvancements()
								.getProgress(
										((WorldServer) entity.world).getAdvancementManager().getAdvancement(
												new ResourceLocation("ninja_mod:ninjatraining2"))).isDone()
						: false))) {
					if (entity instanceof EntityPlayer && !world.isRemote) {
						((EntityPlayer) entity).sendStatusMessage(new TextComponentString(
								"[Sensei]: You're back? Excellent. Now bring me a sword made of iron for your next piece of gear."), (false));
					}
					entity.getEntityData().setDouble("timesTalked", 5);
				}
			} else if ((!(((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == new ItemStack(
					Items.LEATHER_HELMET, (int) (1)).getItem()) || (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity)
					.getHeldItemMainhand() : ItemStack.EMPTY).getItem() == new ItemStack(Items.LEATHER_CHESTPLATE, (int) (1)).getItem())) || ((((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getItem() == new ItemStack(Items.LEATHER_LEGGINGS, (int) (1)).getItem()) || (((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getItem() == new ItemStack(Items.LEATHER_BOOTS, (int) (1)).getItem()))))) {
				if (entity instanceof EntityPlayer && !world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: Bring me the entire leather armor set."), (false));
				}
			}
		} else if (((entity.getEntityData().getDouble("timesTalked")) == 5)) {
			if ((((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer)) ? ((EntityPlayerMP) entity).getAdvancements()
					.getProgress(((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjatime")))
					.isDone() : false)) {
				if (((!(((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer))
						? ((EntityPlayerMP) entity)
								.getAdvancements()
								.getProgress(
										((WorldServer) entity.world).getAdvancementManager().getAdvancement(
												new ResourceLocation("ninja_mod:ninjatraining2"))).isDone()
						: false)) && (!(((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(Items.IRON_SWORD, (int) (1)).getItem())))) {
					if (entity instanceof EntityPlayer && !world.isRemote) {
						((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: Bring me an iron sword."), (false));
					}
				} else {
					if (entity instanceof EntityPlayer && !world.isRemote) {
						((EntityPlayer) entity)
								.sendStatusMessage(
										new TextComponentString(
												"[Sensei]: Alright. Now that you're ready and geared up, how about some actual training? I need you to collect 5 experience levels and report back to me, once you've done it."),
										(false));
					}
					entity.getEntityData().setDouble("timesTalked", 6);
				}
			}
		}
		if ((((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer)) ? ((EntityPlayerMP) entity).getAdvancements()
				.getProgress(((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjaquest")))
				.isDone() : false)) {
			if ((((new ItemStack(Items.LEATHER_HELMET, (int) (1)).getItem() == ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity)
					.getHeldItemMainhand() : ItemStack.EMPTY).getItem()) || (new ItemStack(Items.LEATHER_CHESTPLATE, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getItem())) || ((new ItemStack(Items.LEATHER_LEGGINGS, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getItem()) || (new ItemStack(Items.LEATHER_BOOTS, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getItem())))) {
				if (entity instanceof EntityPlayer && !world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: Here's your ninja suit piece."), (false));
				}
				if ((new ItemStack(Items.LEATHER_HELMET, (int) (1)).getItem() == ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity)
						.getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.LEATHER_HELMET, (int) (1)).getItem(), -1, (int) 1,
								null);
					if (entity instanceof EntityPlayer)
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), new ItemStack(MCreatorNinjaArmor.helmet, (int) (1)));
				} else if ((new ItemStack(Items.LEATHER_CHESTPLATE, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
						? ((EntityLivingBase) entity).getHeldItemMainhand()
						: ItemStack.EMPTY).getItem())) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.LEATHER_CHESTPLATE, (int) (1)).getItem(), -1,
								(int) 1, null);
					if (entity instanceof EntityPlayer)
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), new ItemStack(MCreatorNinjaArmor.body, (int) (1)));
				} else if ((new ItemStack(Items.LEATHER_LEGGINGS, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
						? ((EntityLivingBase) entity).getHeldItemMainhand()
						: ItemStack.EMPTY).getItem())) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.LEATHER_LEGGINGS, (int) (1)).getItem(), -1, (int) 1,
								null);
					if (entity instanceof EntityPlayer)
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), new ItemStack(MCreatorNinjaArmor.legs, (int) (1)));
				} else if ((new ItemStack(Items.LEATHER_BOOTS, (int) (1)).getItem() == ((entity instanceof EntityLivingBase)
						? ((EntityLivingBase) entity).getHeldItemMainhand()
						: ItemStack.EMPTY).getItem())) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.LEATHER_BOOTS, (int) (1)).getItem(), -1, (int) 1,
								null);
					if (entity instanceof EntityPlayer)
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), new ItemStack(MCreatorNinjaArmor.boots, (int) (1)));
				}
			}
		}
		if ((((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer)) ? ((EntityPlayerMP) entity).getAdvancements()
				.getProgress(((WorldServer) entity.world).getAdvancementManager().getAdvancement(new ResourceLocation("ninja_mod:ninjatime")))
				.isDone() : false)) {
			if ((new ItemStack(Items.IRON_SWORD, (int) (1)).getItem() == ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity)
					.getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (entity instanceof EntityPlayer && !world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString("[Sensei]: Here's your katana."), (false));
				}
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.IRON_SWORD, (int) (1)).getItem(), -1, (int) 1, null);
				if (entity instanceof EntityPlayer)
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), new ItemStack(MCreatorKatana.block, (int) (1)));
			}
		}
	}
}
