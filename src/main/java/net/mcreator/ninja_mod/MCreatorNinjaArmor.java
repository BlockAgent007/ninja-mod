package net.mcreator.ninja_mod;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

@Elementsninja_mod.ModElement.Tag
public class MCreatorNinjaArmor extends Elementsninja_mod.ModElement {
	@GameRegistry.ObjectHolder("ninja_mod:ninjaarmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("ninja_mod:ninjaarmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("ninja_mod:ninjaarmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("ninja_mod:ninjaarmorboots")
	public static final Item boots = null;

	public MCreatorNinjaArmor(Elementsninja_mod instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("NINJAARMOR", "ninja_mod:ninja", 7, new int[]{1, 3, 3, 1}, 0,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("item.armor.equip_leather")),
				0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("ninjaarmorhelmet")
				.setRegistryName("ninjaarmorhelmet").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("ninjaarmorbody")
				.setRegistryName("ninjaarmorbody").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("ninjaarmorlegs")
				.setRegistryName("ninjaarmorlegs").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("ninjaarmorboots")
				.setRegistryName("ninjaarmorboots").setCreativeTab(CreativeTabs.COMBAT));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("ninja_mod:ninjaarmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("ninja_mod:ninjaarmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("ninja_mod:ninjaarmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("ninja_mod:ninjaarmorboots", "inventory"));
	}
}
