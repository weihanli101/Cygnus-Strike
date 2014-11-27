package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;

/*Description=======================================================================================
This Class will deal with the Enemy/Aliens' hit-boxes and other Alien related stuff

References:
- https://github.com/libgdx/libgdx/wiki/A-simple-game *orthographic Cam + structure + Array of Sprites
- http://ics3ui.sgrondin.ca/ss14/ArrayLists.html for ArrayLists

Naming:
- Texture will be referenced with a "t"
- Sprites will be referenced with "sp"
- Camera will be referenced with "cam" *we are only using the orthographic camera
- Shapes will be referenced with "rect" or "cir" etc. you get the idea
- ArrayLists (just called Arrays in android) will be name with a prefix of "Ar"
- Wrapper long will be referenced with a "l"
===================================================================================================*/

public class Aliens implements ApplicationListener {

    Texture tAlien;
    Sprite spAlien;
    SpaceShip spaceShip = new SpaceShip();

    Rectangle rectShip;
    Rectangle rectAlien;
    SpriteBatch batch;

    Camera cam = new Camera();
    ShapeRenderer shapeRenderer;
    Array<Rectangle> ArAliens;

    long lLastSpawnTime;// Spawn time for alien using long wrapper since using milliseconds

    @Override
    public void create() {
        cam.create();
        spaceShip = new SpaceShip();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();


        tAlien = new Texture("Alien.png");
        spAlien = new Sprite(tAlien);

        rectShip = new Rectangle();
        rectShip.x = spaceShip.x;
        rectShip.y = spaceShip.y;
        rectShip.width = 110;
        rectShip.height = 64;

        ArAliens = new Array<Rectangle>();// The ArrayList for Aliens
        spawnAlien();// Making an instance of the spawnAlien method to can be used later in the render method
    }
    public void spawnAlien(){// Spawns Alien and adds it to the ArrayList of aliens
        rectAlien = new Rectangle();
        rectAlien.x = -200;// Displaying the Alien off the Screen
        rectAlien.y = MathUtils.random(20,780);//Displaying an Alien at a random y coordinate
        rectAlien.width = 50;// dimensions of Alien.png will also be size of rectangle
        rectAlien.height = 64;
        ArAliens.add(rectAlien);
        lLastSpawnTime = TimeUtils.millis();
    }

    @Override
    public void render() {
        cam.camMain.update();//Updates Camera
        batch.setProjectionMatrix(cam.camMain.combined);//indicates to spritebatch to use cam coor system instead of the default one

        batch.begin();
        for (Rectangle rectAlien : ArAliens) {//looping through the ArrayList and drawing the Alien.png for every Rectangle
            batch.draw(spAlien, rectAlien.x-47, rectAlien.y-68);
        }
        batch.end();

      /*  shapeRenderer.setProjectionMatrix(camera.combined);//displays a green rectangle that is used for collision detection debugging
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        for(Rectangle rectAlien: ArAliens){
            shapeRenderer.rect(rectAlien.x, rectAlien.y, rectAlien.getWidth(), rectAlien.getHeight());
        }
        shapeRenderer.rect(rectShip.x, rectShip.y, rectShip.getWidth(), rectShip.getHeight());
        shapeRenderer.end();*/


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.camMain.unproject(touchPos);
            rectShip.x = touchPos.x - (64 / 2);
            rectShip.y = touchPos.y - (64 / 2);
        }

        if ((TimeUtils.millis() - lLastSpawnTime) > 2000) {//Spawns Alien every 2 seconds
            spawnAlien();
        }
        Iterator<Rectangle> itAlien = ArAliens.iterator();// looping through the Arraylist of aliens and moving them
        while (itAlien.hasNext()) {
            Rectangle rectAlien = itAlien.next();
            rectAlien.x += 200 * Gdx.graphics.getDeltaTime();//moves alien 200 px every second getDeltaTime default = 1 second 100 was too slow
            if (rectAlien.overlaps(rectShip)) {// collision detection
                itAlien.remove();
            }
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
