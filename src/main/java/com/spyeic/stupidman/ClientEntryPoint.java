package com.spyeic.stupidman;

import com.spyeic.stupidman.entity.render.StupidManRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class ClientEntryPoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(StupidMan.STUPID_MAN, (dispatcher, context) -> {
            return new StupidManRenderer(dispatcher);
        });
    }
}
