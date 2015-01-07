package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;


//Description=======================================================================================
//This is the the spaceship object class, it also handles Spaceship's movement

//References:
//https://github.com/libgdx/libgdx/wiki/Accelerometer
//https://github.com/libgdx/libgdx/wiki/A-simple-game

//Mr. Matt Brock yay
//==================================================================================================

public class Spaceship {
    private float fX;
    private float fY;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite spSpaceship;
    private Texture imgSpacespace;
    public Rectangle rectShip;
    private Array<Alien> ArAlien;


    public Spaceship(Array<Alien> ArAlien_, OrthographicCamera camera_,SpriteBatch batch_,float fX_, float fY_){
        fX = fX_;
        fY = fY_;
        camera = camera_;
        batch = batch_;
        ArAlien = ArAlien_;
    }

    public void spaceshipCreate(){
        imgSpacespace = new Texture("StarShip.png");
        spSpaceship = new Sprite(imgSpacespace);
        rectShip = new Rectangle(spSpaceship.getBoundingRectangle());
    }
    public void spaceshipRender(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spSpaceship,fX,fY);
        batch.end();

    }
    public float SpaceshipX(){
        return(fX);
    }
    public float SpaceshipY(){
        return(fY);
    }

    public void HandleTouch(){
        float fSprite_Width = spSpaceship.getWidth();
        float fSprite_Height = spSpaceship.getWidth();
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            fX = touchPos.x - (fSprite_Width/ 2);
            fY = touchPos.y - (fSprite_Height/ 2);
        }
        rectShip.x = fX;
        rectShip.y = fY;

    }

  /*  public void HandleTilt(){
        float fAccelX = Gdx.input.getAccelerometerX();
        float fAccelY = Gdx.input.getAccelerometerY();

        if(fAccelX>3){
            fY += 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelX<-3){
            fY -= 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelY<-3){
            fX -= 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelY>3){
            fX += 500 *Gdx.graphics.getDeltaTime();
        }
*/
    //}
    public void HandleKeys(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            fY -= 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            fY += 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            fX -= 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            fX += 500*Gdx.graphics.getDeltaTime();
        }
    }
}
