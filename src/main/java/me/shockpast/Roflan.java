package me.shockpast;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Roflan implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("RoflanUtils");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
    }
}