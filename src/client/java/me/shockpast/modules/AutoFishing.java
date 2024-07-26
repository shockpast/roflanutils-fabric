package me.shockpast.modules;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.util.Hand;

public class AutoFishing implements ClientCommandRegistrationCallback {
    private static int ticks = -1;
    private static boolean enabled = false;

    public static void setTicks(int ticks) {
        AutoFishing.ticks = ticks;
    }

    public static int getTicks() {
        return ticks;
    }

    public static void onTick(MinecraftClient client) {
        if (ticks > 0)
            ticks--;
        if (ticks != 0)
            return;

        client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);

        ticks = -1;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public int execute_state(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        AutoFishing.enabled = BoolArgumentType.getBool(context, "state");
        return 1;
    }

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registry) {
        dispatcher.register(ClientCommandManager.literal("autofishing")
            .then(ClientCommandManager.argument("state", BoolArgumentType.bool())
                .executes(this::execute_state)));
    }
}
