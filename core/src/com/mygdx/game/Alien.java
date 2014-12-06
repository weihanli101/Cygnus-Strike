package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Alien{
    float fX;
    float fY;
    Sprite spAlien;

    public Alien(float fX_, float fY_,Sprite sprite_ ){
        fX = fX_;
        fY = fY_;
        spAlien = sprite_;
        spAlien.setPosition(fX,fY);
    }
}
