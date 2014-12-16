package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/*Description=======================================================================================
This Class will Deal with Handling user input for the spaceship and any other spaceship related
things

References:
- https://github.com/libgdx/libgdx/wiki/A-simple-game       *orthographic Cam + structure

Naming:
- Texture will be referenced with a "t"
- Sprites will be referenced with "sp"
- Camera will be referenced with "cam" *we are only using the orthographic camera
- Shapes will be referenced with "rect" or "cir" etc. you get the idea
- ArrayLists (just called Arrays in android) will be name with a prefix of "Ar"
- Wrapper long will be referenced with a "l"
===================================================================================================*/

public class SpaceShip implements ApplicationListener {
    Camera camera = new Camera();
    Texture tSpaceShip;
    Sprite spSpaceShip;
    SpriteBatch batch;

    Rectangle rectShip;
    public float x = 1280/2;
    public float y = 800/2;


    @Override
    public void create() {
        camera.create();

        batch = new SpriteBatch();

        tSpaceShip = new Texture("StarShip.png");
        spSpaceShip = new Sprite(tSpaceShip);

        //HitBox (*its just a rectangle with no fill the size of the image nothing really special)
        rectShip = new Rectangle();
        rectShip.x = x;//Coordinates of the Ship + rectangle since they are on top of each other only need one variable for both
        rectShip.y = y;
        rectShip.width = 173;//Width of StarShip.png *the rectangle's dimensions
        rectShip.height = 109;//Height of StarShip.png
    }


    @Override
    public void render() {

        camera.camMain.update();//Updates Camera
        batch.setProjectionMatrix(camera.camMain.combined);//indicates to spritebatch to use cam coor system instead of the default one
        batch.begin();
        batch.draw(spSpaceShip,rectShip.x,rectShip.y);//displays the spaceship at specified x,y coordinates
        batch.end();

        //Handles user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();// handles 3-D coordinates however we are only using 2-D atm
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);// handles 3-D coordinates might be useful later for tilt
            camera.camMain.unproject(touchPos); // Setting the entered coor to the cam's coor, they might be the same but lets not take chances
            rectShip.x = touchPos.x - (173/2); //centering the touch on the middle of the Spaceship for x coor
            rectShip.y = touchPos.y - (109/2); //centering the touch on the middle of the Spaceship for y coor
        }
    }

    @Override
         public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
