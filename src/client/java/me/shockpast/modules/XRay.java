package me.shockpast.modules;

import java.util.HashSet;
import java.util.Set;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;

public class XRay implements ClientCommandRegistrationCallback {
    private static boolean enabled = false;
    private static Set<String> blocks = new HashSet<>();

    public static Set<String> getBlocks() {
        return blocks;
    }

    public static Boolean hasBlock(String name) {
        return blocks.contains(name);
    }

    public static Boolean isEnabled() {
        return enabled;
    }

    public XRay() {
        blocks.add("Coal Ore");
        blocks.add("Deepslate Coal Ore");
        blocks.add("Iron Ore");
        blocks.add("Deepslate Iron Ore");
        blocks.add("Copper Ore");
        blocks.add("Deepslate Copper Ore");
        blocks.add("Gold Ore");
        blocks.add("Deepslate Gold Ore");
        blocks.add("Redstone Ore");
        blocks.add("Deepslate Redstone Ore");
        blocks.add("Emerald Ore");
        blocks.add("Deepslate Emerald Ore");
        blocks.add("Lapis Lazuli Ore");
        blocks.add("Deepslate Lapis Lazuli Ore");
        blocks.add("Diamond Ore");
        blocks.add("Deepslate Diamond Ore");
        blocks.add("Ancient Debris");
        blocks.add("Lava");
        blocks.add("Water");
    }

    public int execute_state(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        FabricClientCommandSource source = context.getSource();
        final MinecraftClient client = source.getClient();

        XRay.enabled = BoolArgumentType.getBool(context, "state");

        client.worldRenderer.reload();

        return 1;
    }

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(ClientCommandManager.literal("xray")
            .then(ClientCommandManager.argument("state", BoolArgumentType.bool())
                .executes(this::execute_state)));
    }
}
