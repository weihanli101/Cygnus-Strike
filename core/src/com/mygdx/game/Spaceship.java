package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Spaceship implements ApplicationListener {
    Camera camera = new Camera();
    SpaceWorld spaceWorld = new SpaceWorld();

    Texture imgSpaceship;
    Sprite spSpaceship;
    SpriteBatch batch;

    float fXSpaceship = 600;
    float fYSpaceship = 400;

    @Override
    public void create() {
        camera.create();
        spaceWorld.create();

        batch = new SpriteBatch();
        imgSpaceship = new Texture("StarShip.png");
        spSpaceship = new Sprite(imgSpaceship);

        HandleTouch();

    }
    public void HandleTouch(){
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.camMain.unproject(touchPos);
            fXSpaceship = touchPos.x - (spSpaceship.getWidth() / 2);
            fYSpaceship = touchPos.y - (spSpaceship.getHeight() / 2);
        }
    }

    @Override
    public void render() {
        camera.camMain.update();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        batch.draw(spSpaceship,fXSpaceship,fYSpaceship);
        batch.end();

        HandleTouch();

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
