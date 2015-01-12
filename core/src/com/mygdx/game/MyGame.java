package com.mygdx.game;

import com.badlogic.gdx.Game;


//scratch program that plays with the idea of changing screens (screen #1 is the black screen, screen #2 is the white screen)
//this concept will be implemented later to switch between different screens at the main menu
// https://code.google.com/p/libgdx-users/wiki/ScreenAndGameClasses
public class MyGame extends Game {

    MainMenuScreen mainMenuScreen;
    PlayScreen playScreen;
    ProfileScreen profileScreen;
    OptionScreen optionScreen;
    HelpScreen helpScreen;
    ShopScreen shopScreen;
    GameOverScreen gameOverScreen;
    WinScreen winScreen;

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen(this);
        playScreen = new PlayScreen(this);
        profileScreen = new ProfileScreen(this);
        optionScreen = new OptionScreen(this);
        helpScreen = new HelpScreen(this);
        shopScreen = new ShopScreen(this);
        gameOverScreen = new GameOverScreen(this);
        winScreen = new WinScreen(this);
        setScreen(mainMenuScreen);

    }


}
