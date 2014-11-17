package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;


public class HitTest implements ApplicationListener {
    Texture tSpaceShip;
    Texture tAlien;
    Sprite spAlien;
    Sprite spSpaceShip;

    Rectangle rectShip;
    Rectangle rectAlien;
    SpriteBatch batch;

    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true,1200,800);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        tAlien = new Texture("Alien.png");
        spAlien = new Sprite(tAlien);
        tSpaceShip = new Texture("StarShip.png");
        spSpaceShip = new Sprite(tSpaceShip);

        rectShip = new Rectangle();
        rectShip.x = 1200/2;
        rectShip.y = 800/2;
        rectShip.width = 110;
        rectShip.height = 64;

        rectAlien = new Rectangle();
        rectAlien.x = -200;
        rectAlien.y = 800/2;
        rectAlien.width = 50;
        rectAlien.height = 64;
    }



    @Override
    public void render() {
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spSpaceShip,rectShip.x-35,rectShip.y-25);
        batch.draw(spAlien,rectAlien.x-47,rectAlien.y-68);
        batch.end();
       // shapeRenderer.setProjectionMatrix(camera.combined);
       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.GREEN);
       // shapeRenderer.rect(rectAlien.x, rectAlien.y, rectAlien.getWidth(), rectAlien.getHeight());
        //shapeRenderer.rect(rectShip.x, rectShip.y, rectShip.getWidth(), rectShip.getHeight());
        //shapeRenderer.end();


        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            rectShip.x = touchPos.x - (64 / 2);
            rectShip.y = touchPos.y -(64/2);
        }
        rectAlien.x += 200*Gdx.graphics.getDeltaTime();
        if(rectAlien.x>1200){
            rectAlien.x = -200;
        }
        if(rectAlien.overlaps(rectShip)){
            System.out.println("HIT");
            rectAlien.x = -200;
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
