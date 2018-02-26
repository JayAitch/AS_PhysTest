package com.allsopg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by gerard on 16/02/2018.
 */

public class PhysicsExample_1 extends ApplicationAdapter {
    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;
    float animationTime;
    @Override
    public void create() {

        batch = new SpriteBatch();
        // Using default LibGdx logo
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);

        // Sprite centered at top of screen
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);

        // physics world - vector for gravity
        world = new World(new Vector2(0, -98f), true);

        // BodyDefinition to define the physics objects with position
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // 1 in physics engine is 1 pixel
        // body set to same position as sprite
        bodyDef.position.set(sprite.getX(), sprite.getY());

        // add body to the world
        body = world.createBody(bodyDef);

        // define tdimensions of physics shape
        PolygonShape shape = new PolygonShape();
        // a physics polygon with the same dimensions as sprite
        shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);

        // Define physical properties such as density, restitution and etc.
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        // Get rid of shape
        shape.dispose();
    }

    @Override
    public void render() {

        // Advance world clock by time passed since last frame
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        // update sprite position to updated Physics body position
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        world.dispose();
    }
}
