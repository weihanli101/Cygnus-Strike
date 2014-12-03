package com.mygdx.game;


import com.badlogic.gdx.Gdx;

public class Alien{
    float fX;
    float fY;

    public Alien(float fX_, float fY_){
        fX = fX_;
        fY = fY_;
    }
    public void MoveAlien(){
        fX += 200* Gdx.graphics.getDeltaTime();
    }

}
