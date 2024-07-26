package me.shockpast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shockpast.modules.AutoFishing;
import me.shockpast.modules.XRay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;

public class RoflanClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("RoflanClient");

    @Override
    public void onInitializeClient() {
        Event<ClientCommandRegistrationCallback> commands = ClientCommandRegistrationCallback.EVENT;

        // Modules
        commands.register(new AutoFishing());
        commands.register(new XRay());

        // Events
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    public void onTick(MinecraftClient client) {
        AutoFishing.onTick(client);
    }
}