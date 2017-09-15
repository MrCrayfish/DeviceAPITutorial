package com.mrcrayfish.tutorial.app;

import com.mrcrayfish.device.Reference;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.api.app.component.TextField;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.api.app.listener.InitListener;
import com.mrcrayfish.device.api.task.Callback;
import com.mrcrayfish.device.api.utils.BankUtil;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Author: MrCrayfish
 */
public class TutorialApp extends Application
{
    private static final String USERNAME = "MrCrayfish";
    private static final String PASSWORD = "Cheese";

    private Layout layoutLogin;
    private Label labelUsername;
    private TextField textFieldUsername;
    private Label labelPassword;
    private TextField textFieldPassword;
    private Button buttonLogin;

    private Layout layoutBalance;
    private Label labelBalance;

    public TutorialApp()
    {
        super(Reference.MOD_ID + "TutorialApp", "Bank Displayer");
    }

    @Override
    public void init()
    {
        super.init();

        layoutLogin = new Layout(100, 100);

        labelUsername = new Label("Username", 5, 5);
        layoutLogin.addComponent(labelUsername);

        textFieldUsername = new TextField(5, 20, 90);
        layoutLogin.addComponent(textFieldUsername);

        labelPassword = new Label("Password", 5, 40);
        layoutLogin.addComponent(labelPassword);

        textFieldPassword = new TextField(5, 55, 90);
        layoutLogin.addComponent(textFieldPassword);

        buttonLogin = new Button("Login", 5, 75, 90, 20);
        buttonLogin.setClickListener((component, mouseButton) ->
        {
            if(mouseButton == 0)
            {
                if(textFieldUsername.getText().equals(USERNAME) && textFieldPassword.getText().equals(PASSWORD))
                {
                    this.setCurrentLayout(layoutBalance);
                }
            }
        });
        layoutLogin.addComponent(buttonLogin);

        layoutBalance = new Layout(100, 50);
        layoutBalance.setInitListener(() ->
        {
            BankUtil.getBalance((nbtTagCompound, success) ->
            {
                if(success)
                {
                    labelBalance.setText("$" + nbtTagCompound.getInteger("balance"));
                }
            });
        });

        labelBalance = new Label("$0", 5, 5);
        labelBalance.setScale(2.0);
        layoutBalance.addComponent(labelBalance);

        this.setCurrentLayout(layoutLogin);
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
