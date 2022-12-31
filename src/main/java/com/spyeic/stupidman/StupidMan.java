package com.spyeic.stupidman;

import com.spyeic.stupidman.entity.mob.StupidManEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StupidMan implements ModInitializer {
    public static final EntityType<StupidManEntity> STUPID_MAN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("stupidman", "stupid_man_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, StupidManEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                    .build()
    );
    public static final Item STUPID_MAN_SPAWN_EGG = new SpawnEggItem(STUPID_MAN, 12895428, 11382189, new Item.Settings().group(ItemGroup.MISC));


    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(STUPID_MAN,
                StupidManEntity.createMobAttributes()
        );
        Registry.register(Registry.ITEM, new Identifier("stupidman", "stupid_man_spawn_egg"), STUPID_MAN_SPAWN_EGG);
    }
}
