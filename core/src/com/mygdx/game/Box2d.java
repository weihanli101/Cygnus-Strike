package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

//Box2d Scratch=====================================================================================
//Reference:
//http://www.gamefromscratch.com/post/2014/09/10/LibGDX-Tutorial-13-Physics-with-Box2D-Part-2-Force-Impulses-and-Torque.aspx
//https://github.com/libgdx/libgdx/wiki/box2d#sprites-and-bodies
//Jose
//==================================================================================================

public class Box2d implements ApplicationListener {
    Camera camera = new Camera();
    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    float fPtoM = 100;
    float fX;
    float fY;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Alien.png");
        sprite = new Sprite(img);
        sprite.setPosition(fX,fY);//Setting the position of the sprite in the middle of the sprite

        world = new World(new Vector2(0, 0),true);// creating a world with no gravity atm

        BodyDef bodyDef = new BodyDef();// Creating a body for the sprite
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) /fPtoM,(sprite.getY() + sprite.getHeight()/2) / fPtoM);//setting the body to the middle of the sprite

        body = world.createBody(bodyDef);// Making a new body in the world, with the specs of the defined body above

        PolygonShape shape = new PolygonShape();// giving the body a shape
        shape.setAsBox(sprite.getWidth()/2 / fPtoM, sprite.getHeight()/2 / fPtoM);// making the shape the same size as the body

        FixtureDef fixtureDef = new FixtureDef();//giving the body a density, can be used for collions later on
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        body.createFixture(fixtureDef);// set the fixture to the body we just made

        debugRenderer = new Box2DDebugRenderer();
        camera.create();

    }
    public void HandleTouch() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.camMain.unproject(touchPos);
            fX = touchPos.x - (sprite.getWidth()/fPtoM / 2);
            fY = touchPos.y - (sprite.getHeight()/fPtoM / 2);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        camera.camMain.update();
        body.setTransform(fX/fPtoM,fY/fPtoM,0);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        sprite.setPosition((body.getPosition().x*fPtoM)-(sprite.getWidth()/2),((body.getPosition().y*fPtoM)-(sprite.getHeight()/2)));

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugMatrix = batch.getProjectionMatrix().cpy().scale(fPtoM,fPtoM, 0);
        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        batch.draw(sprite,sprite.getX(),sprite.getY());
        batch.end();

        debugRenderer.render(world, debugMatrix);

        HandleTouch();

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
