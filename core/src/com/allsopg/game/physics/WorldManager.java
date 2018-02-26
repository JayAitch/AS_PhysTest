package com.allsopg.game.physics;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by gerard on 01/03/2017.
 */

public class WorldManager {

    //physics
    private final Vector2 gravity;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body dynamicBody;
    private Body staticBody;
    private FitViewport viewport;

    private static WorldManager WORLDMANAGER;

    public static WorldManager getInstance(){
        if(WORLDMANAGER == null){WORLDMANAGER = new WorldManager();}
        return WORLDMANAGER;
    }


    private WorldManager(){
        gravity = new Vector2(0,-9.8f);
        Box2D.init();
        //2nd arg if we want objects to sleep or not
        world = new World(gravity, true);
        debugRenderer = new Box2DDebugRenderer();
    }

    public Body createDynamicBody(Sprite sprite){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(),sprite.getY());
        dynamicBody = world.createBody(bodyDef);

        //prepare for Fixture definition
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getBoundingRectangle().getX(),
                sprite.getBoundingRectangle().getY());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution=0.6f;

        dynamicBody.createFixture(fixtureDef);
        return dynamicBody;
    }


    public void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
    }


    public void debugRender(){
        debugRenderer.render(world, viewport.getCamera().combined);
    }

    public World getWorld(){
        return world;
    }
}
