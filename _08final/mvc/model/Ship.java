package _08final.mvc.model;

/**
 * Created by zhuoyuzhu on 11/26/16.
 */
import _08final.images.SpriteTexLoader;
import _08final.mvc.controller.Game;
import _08final.mvc.view.GamePanel;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by zhuoyu zhu on 11/20/16.
 */
public class Ship extends Sprite {

    private double speed = 250.0;


    private final static Dimension SHIP_DIM = new Dimension(25,39);

    private double velX = 0;
    private double velY = 0;


    public Ship(Point initPos) {
        super(initPos, SHIP_DIM, SpriteTexLoader.load(SpriteTexLoader.SpriteTex.SHIP));
    }

    /**
     * The key to solving this issue in keeping track of the time passed since the last frame.
     * I’ve been calling this the delta, meaning difference—(in this case, a difference in time).
     * Once we have the delta, we can figure out the distance our object should move this frame
     * by using the following formula:
     * @param deltaTime
     */
    public void tick(long deltaTime) {
        final double MILS_TO_SECS = 0.001f;
        double deltaTimeInSecs = deltaTime * MILS_TO_SECS;

        if (getX()+(int)(velX * deltaTimeInSecs) <= 20) {
            setX(20);
        }

        if (getX()+(int)(velX * deltaTimeInSecs) >= 660) {
            setX(660);
        }

        if (getY()+(int)(velY * deltaTimeInSecs) <= 20) {
            setY(20);
        }

        if (getY()+(int)(velY * deltaTimeInSecs) >= 660) {
            setY(640);
        }

        setX(getX()+(int)(velX * deltaTimeInSecs));
        setY(getY()+(int)(velY * deltaTimeInSecs));

        if (Physics.Collision(this, Game.er)) {
            System.out.println("Collision Detected!");
        }
    }


    public void setVelX(double direction) {
        this.velX = direction * speed;
    }

    public void setVelY(double direction) {
        this.velY = direction * speed;
    }

    public void moveLeft(int direction) {
        setVelX(-direction);
    }

    public void moveRight(int direction) {
        setVelX(direction);
    }

    public void moveDown(int direction) {
        setVelY(direction);
    }

    public void moveUp(int direction) {
        setVelY(-direction);
    }

}
