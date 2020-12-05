package net.mcreator.ninja_mod;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.Minecraft;

public class ninja_modVariables {
	public static String UnlockStatus = "";

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "ninja_mod_mapvars";
		public double CombatLevel = 0;
		public double AgilityLevel = 0;
		public double StealthLevel = 0;
		public double CraftingLevel = 0;
		public double CombatProgress = 0;
		public double AgilityProgress = 0;
		public double StealthProgress = 0;
		public double CraftingProgress = 0;
		public double CombatMax = 0;
		public double AgilityMax = 0;
		public double StealthMax = 0;
		public double CraftingMax = 0;

		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
			CombatLevel = nbt.getDouble("CombatLevel");
			AgilityLevel = nbt.getDouble("AgilityLevel");
			StealthLevel = nbt.getDouble("StealthLevel");
			CraftingLevel = nbt.getDouble("CraftingLevel");
			CombatProgress = nbt.getDouble("CombatProgress");
			AgilityProgress = nbt.getDouble("AgilityProgress");
			StealthProgress = nbt.getDouble("StealthProgress");
			CraftingProgress = nbt.getDouble("CraftingProgress");
			CombatMax = nbt.getDouble("CombatMax");
			AgilityMax = nbt.getDouble("AgilityMax");
			StealthMax = nbt.getDouble("StealthMax");
			CraftingMax = nbt.getDouble("CraftingMax");
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			nbt.setDouble("CombatLevel", CombatLevel);
			nbt.setDouble("AgilityLevel", AgilityLevel);
			nbt.setDouble("StealthLevel", StealthLevel);
			nbt.setDouble("CraftingLevel", CraftingLevel);
			nbt.setDouble("CombatProgress", CombatProgress);
			nbt.setDouble("AgilityProgress", AgilityProgress);
			nbt.setDouble("StealthProgress", StealthProgress);
			nbt.setDouble("CraftingProgress", CraftingProgress);
			nbt.setDouble("CombatMax", CombatMax);
			nbt.setDouble("AgilityMax", AgilityMax);
			nbt.setDouble("StealthMax", StealthMax);
			nbt.setDouble("CraftingMax", CraftingMax);
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				ninja_mod.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(0, this));
			} else {
				ninja_mod.PACKET_HANDLER.sendToAll(new WorldSavedDataSyncMessage(0, this));
			}
		}

		public static MapVariables get(World world) {
			MapVariables instance = (MapVariables) world.getMapStorage().getOrLoadData(MapVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new MapVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "ninja_mod_worldvars";

		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				ninja_mod.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(1, this));
			} else {
				ninja_mod.PACKET_HANDLER.sendToDimension(new WorldSavedDataSyncMessage(1, this), world.provider.getDimension());
			}
		}

		public static WorldVariables get(World world) {
			WorldVariables instance = (WorldVariables) world.getPerWorldStorage().getOrLoadData(WorldVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new WorldVariables();
				world.getPerWorldStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldSavedDataSyncMessageHandler implements IMessageHandler<WorldSavedDataSyncMessage, IMessage> {
		@Override
		public IMessage onMessage(WorldSavedDataSyncMessage message, MessageContext context) {
			if (context.side == Side.SERVER)
				context.getServerHandler().player.getServerWorld().addScheduledTask(
						() -> syncData(message, context, context.getServerHandler().player.world));
			else
				Minecraft.getMinecraft().addScheduledTask(() -> syncData(message, context, Minecraft.getMinecraft().player.world));
			return null;
		}

		private void syncData(WorldSavedDataSyncMessage message, MessageContext context, World world) {
			if (context.side == Side.SERVER) {
				if (message.type == 0)
					ninja_mod.PACKET_HANDLER.sendToAll(message);
				else
					ninja_mod.PACKET_HANDLER.sendToDimension(message, world.provider.getDimension());
			}
			if (message.type == 0) {
				world.getMapStorage().setData(MapVariables.DATA_NAME, message.data);
			} else {
				world.getPerWorldStorage().setData(WorldVariables.DATA_NAME, message.data);
			}
		}
	}

	public static class WorldSavedDataSyncMessage implements IMessage {
		public int type;
		public WorldSavedData data;

		public WorldSavedDataSyncMessage() {
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(this.type);
			ByteBufUtils.writeTag(buf, this.data.writeToNBT(new NBTTagCompound()));
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			this.type = buf.readInt();
			if (this.type == 0)
				this.data = new MapVariables();
			else
				this.data = new WorldVariables();
			this.data.readFromNBT(ByteBufUtils.readTag(buf));
		}
	}
}
