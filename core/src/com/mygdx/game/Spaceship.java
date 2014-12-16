package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//Description=======================================================================================
//This is the the spaceship object class, it also handles Spaceship's movement

//References:
//https://github.com/libgdx/libgdx/wiki/Accelerometer
//https://github.com/libgdx/libgdx/wiki/A-simple-game

//Mr. Matt Brock
//==================================================================================================

public class Spaceship {
    float fX;
    float fY;
    OrthographicCamera camera;
    Sprite spSpaceship;
    Rectangle rectShip;

    public Spaceship(float fX_, float fY_, OrthographicCamera camera_, Sprite sprite_){
        fX = fX_;
        fY = fY_;
        camera = camera_;
        spSpaceship = sprite_;
        rectShip = spSpaceship.getBoundingRectangle();
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
    }
    public void updateSpaceship(){
        rectShip.x = fX;
        rectShip.y = fY;
    }

    public void HandleTilt(){
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

    }
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
