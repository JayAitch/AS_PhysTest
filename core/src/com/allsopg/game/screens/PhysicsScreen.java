package com.allsopg.game.screens;

import com.allsopg.game.PhysicsExample_2;
import com.allsopg.game.physics.WorldManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by gerard on 14/02/2018.
 */

public class PhysicsScreen implements Screen {
    //SpriteBatch batch;
    Sprite sprite;
    Body body;
    Texture img;
    private PhysicsExample_2 game;

    private float gameTime;
    private float accumulator = 0;

    private WorldManager pWorld;

    public PhysicsScreen(PhysicsExample_2 physicsExample){
        game = physicsExample;

        OrthographicCamera camera;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //batch = new SpriteBatch();
        // Using default LibGdx logo
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);
        // Sprite centered at top of screen
        sprite.setPosition(Gdx.graphics.getWidth() / 4 - sprite.getWidth() / 4,
                Gdx.graphics.getHeight() / 4);

        body = WorldManager.getInstance().createDynamicBody(sprite);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        // update sprite position to updated Physics body position
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        game.batch.begin();
        game.batch.draw(sprite, sprite.getX(), sprite.getY());
        game.batch.end();
        WorldManager.getInstance().debugRender();
        WorldManager.getInstance().doPhysicsStep(delta);
    }

    private void update(float stateTime) {
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}