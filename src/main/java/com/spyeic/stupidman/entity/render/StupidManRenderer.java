package com.spyeic.stupidman.entity.render;

import com.spyeic.stupidman.entity.mob.StupidManEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class StupidManRenderer extends MobEntityRenderer<StupidManEntity, PlayerEntityModel<StupidManEntity>> {
    public StupidManRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new PlayerEntityModel<>(0.0F, false), 0.5F);
    }

    @Override
    public Identifier getTexture(StupidManEntity entity) {
        return new Identifier("stupidman", "textures/entity/stupid_man.png");
    }
}
