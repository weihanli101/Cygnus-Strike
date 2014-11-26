package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Main extends Game{
    Spaceship spaceship = new Spaceship();
    Alien alien = new Alien();

    @Override
    public void create() {
        alien.create();
        spaceship.create();


    }

    @Override
    public void render(){
        spaceship.render();
        alien.render();

    }
}
