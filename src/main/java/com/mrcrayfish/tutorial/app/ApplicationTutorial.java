package com.mrcrayfish.tutorial.app;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Image;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public class ApplicationTutorial extends Application
{
    private Button btnLeft;
    private Button btnRight;
    private Image imageDisplay;

    private int currentIndex = 0;

    @Override
    public void init()
    {
        final List<String> URLS = new ArrayList<>();
        URLS.add("https://minecraft.net/static/realms/img/realms-description-1.8c2cdb55f46c.png");
        URLS.add("https://media.mojang.com/blog-image/0db18353862a2f2d4d029b757914935a930311a4/0_17_Update_Mojang_Blog_1024x576.png");
        URLS.add("http://is4.mzstatic.com/image/thumb/Purple122/v4/f9/a8/3b/f9a83b28-e38a-b70b-284f-3d89918f016e/source/720x405bb.jpg");

        btnLeft = new Button("<", 5, 5, 20, 20);
        btnLeft.setClickListener((component, mouseButton) ->
        {
            if(mouseButton == 0)
            {
                if(currentIndex > 0)
                {
                    imageDisplay.setImage(URLS.get(--currentIndex));
                }
            }
        });
        super.addComponent(btnLeft);

        btnRight = new Button(">", 175, 5, 20, 20);
        btnRight.setClickListener((component, mouseButton) ->
        {
            if(mouseButton == 0)
            {
                if(currentIndex < URLS.size() - 1)
                {
                    imageDisplay.setImage(URLS.get(++currentIndex));
                }
            }
        });
        super.addComponent(btnRight);

        imageDisplay = new Image(30, 5, 140, 90, URLS.get(currentIndex));
        super.addComponent(imageDisplay);
    }

    @Override
    public void load(NBTTagCompound nbtTagCompound)
    {

    }

    @Override
    public void save(NBTTagCompound nbtTagCompound)
    {

    }
}
