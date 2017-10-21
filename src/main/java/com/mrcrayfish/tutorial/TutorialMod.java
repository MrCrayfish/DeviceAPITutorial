package com.mrcrayfish.tutorial;

import com.mrcrayfish.device.api.ApplicationManager;
import com.mrcrayfish.tutorial.app.ApplicationTutorial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Author: MrCrayfish
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MC_VERSION, dependencies = Reference.DEPENDS)
public class TutorialMod
{
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ApplicationManager.registerApplication(new ResourceLocation(Reference.MOD_ID, "tutorial_app"), ApplicationTutorial.class);
    }
}
