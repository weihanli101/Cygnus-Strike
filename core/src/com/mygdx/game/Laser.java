package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by WL on 12/9/2014.
 */
public class Laser {
    Sprite spLaser;
    float fX;
    float fVelX;
    float fY;
    Rectangle rectLaser;

    public Laser(float fX_, float fY_, Sprite sprite_){
        fX = fX_;
        fY = fY_;
        fVelX = 900;
        spLaser = sprite_;
        spLaser.setPosition(fX, fY);
        rectLaser = spLaser.getBoundingRectangle();
    }
    public void updateLaser(){
       rectLaser.x = fX;
       rectLaser.y = fY;
    }

}
