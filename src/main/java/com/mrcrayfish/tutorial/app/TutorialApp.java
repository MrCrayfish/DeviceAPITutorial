package com.mrcrayfish.tutorial.app;

import com.mrcrayfish.device.Reference;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.api.app.component.TextField;
import com.mrcrayfish.device.api.utils.BankUtil;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Example application covering Layouts, Components, Click Listener, Init Listener
 * and the Bank Utility.
 *
 * Author: MrCrayfish
 */
public class TutorialApp extends Application
{
    private static final String USERNAME = "MrCrayfish";
    private static final String PASSWORD = "Cheese";

    /*
     * All components should be declared on a global scope
     * so we don't have any forward reference problems
     */
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
            // If left clicked on button
            if(mouseButton == 0)
            {
                // Test if username and password field match the credentials
                if(textFieldUsername.getText().equals(USERNAME) && textFieldPassword.getText().equals(PASSWORD))
                {
                    // Change the layout of the application to display the balance
                    this.setCurrentLayout(layoutBalance);
                }
            }
        });
        layoutLogin.addComponent(buttonLogin);

        layoutBalance = new Layout(100, 50);
        layoutBalance.setInitListener(() -> //Creates an init listener. Called when set as current layout
        {
            //Gets the balance of the player
            BankUtil.getBalance((nbtTagCompound, success) ->
            {
                // If request was successful, set the label to player's balance.
                if(success)
                {
                    labelBalance.setText("$" + nbtTagCompound.getInteger("balance"));
                }
            });
        });

        labelBalance = new Label("$0", 5, 5);
        labelBalance.setScale(2.0);
        layoutBalance.addComponent(labelBalance);

        // Sets the current/initial layout to the login screen
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
