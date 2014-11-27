package com.mygdx.game;

import com.badlogic.gdx.Game;

/*Description=======================================================================================
This Class is the Main class where the game will run

References:
- Matt Brock showed us the proper structure and imports for a Main class

Naming:
- Texture will be referenced with a "t"
- Sprites will be referenced with "sp"
- Camera will be referenced with "cam" *we are only using the orthographic camera
- Shapes will be referenced with "rect" or "cir" etc. you get the idea
- ArrayLists (just called Arrays in android) will be name with a prefix of "Ar"
- Wrapper long will be referenced with a "l"
===================================================================================================*/

public class Main extends Game {

    Background backGround = new Background();
    SpaceShip spaceShip = new SpaceShip();
    Aliens aliens = new Aliens();

    @Override
    public void create() {

        backGround.create();
        spaceShip.create();
        aliens.create();

    }

    @Override
    public void render(){

        backGround.render();
        spaceShip.render();
        aliens.render();

    }
}
