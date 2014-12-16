package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Alien{
    float fX;
    float fY;
    Sprite spAlien;
    Rectangle rectAlien;

    public Alien(float fX_, float fY_,Sprite sprite_ ){
        fX = fX_;
        fY = fY_;
        spAlien = sprite_;
        spAlien.setPosition(fX,fY);
        rectAlien = spAlien.getBoundingRectangle();
    }
    public void updateAlien(){
        rectAlien.x = fX;
        rectAlien.y = fY;
    }

}
