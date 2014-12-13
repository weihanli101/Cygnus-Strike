package game;

import com.badlogic.gdx.Game;

//scratch program that plays with the idea of changing screens (screen #1 is the black screen, screen #2 is the white screen)
//this concept will be implemented later to switch between different screens at the main menu
//https://code.google.com/p/libgdx-users/wiki/ScreenAndGameClasses

public class MyGame extends Game {

    BlackScreen blackScreen;
    WhiteScreen whiteScreen;

    @Override
    public void create() {

        blackScreen = new BlackScreen(this);
        whiteScreen = new WhiteScreen(this);
        setScreen(blackScreen);


    }
}
