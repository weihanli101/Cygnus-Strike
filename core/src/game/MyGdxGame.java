package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

// scratch program created to test out using buttons in libgdx and getting it to do something (in this case it displays an image)
//Matt Brock
//http://stackoverflow.com/questions/21488311/libgdx-how-to-create-a-button
//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx

public class MyGdxGame extends Game {

    Stage stage;
    BitmapFont font;

    private TextButton tbButton;
    private TextButton.TextButtonStyle tbsButton;
    private Skin skButton;
    private TextureAtlas taButton;

    boolean bCheck = false;
    Texture tImage;
    SpriteBatch batch;

    @Override
    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skButton = new Skin();
        //process for .pack (.pack -> textureAtlas -> Skin -> TextButtonStyle)
        taButton = new TextureAtlas(Gdx.files.internal("MenuButtons.pack"));//.pack is used to hold multiple images and can be referenced by TextButtonStyle
        skButton.addRegions(taButton);//Applying a texture atlas into a skin

        tImage = new Texture("badlogic.jpg");
        batch = new SpriteBatch();

        tbsButton = new TextButton.TextButtonStyle();//TextButtonStyle Holds all the images that will be applied to the TextButton
        tbsButton.font = font;
        tbsButton.up = skButton.getDrawable("btnPlayUp");//images from TextButtonStyle are applied to individual button states (up, down, etc.)
        tbsButton.down = skButton.getDrawable("btnPlayDown");
        tbsButton.checked = skButton.getDrawable("btnPlayUp");
        tbButton = new TextButton("", tbsButton);//Applying the TextButtonStyle to the TextButton giving it all of its positions and images
        tbButton.setSize(200, 100);
        tbButton.setPosition(100, 100);
        tbButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {//when the button is pressed do something
                bCheck = true;
                return true;

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {//when the button is not pressed do something
                bCheck = false;


            }
        });
        stage.addActor(tbButton);


    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);//setting background
        Gdx.gl.glClearColor(0, 0, 0, 0);
        super.render();
        stage.draw();

        if (bCheck){//checks to see if the button is pressed
            batch.begin();
            batch.draw(tImage, 0, 0, 80, 80);//display the image if the button is pressed
            batch.end();
        }

    }

}