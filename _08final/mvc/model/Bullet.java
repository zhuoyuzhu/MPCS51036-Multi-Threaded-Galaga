package _08final.mvc.model;

import _08final.images.SpriteTexLoader;
import _08final.mvc.controller.Game;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhuoyuzhu on 11/27/16.
 */
public class Bullet extends Sprite {

    private ArrayList<Bullet> b;
    private Game game;

    private final static Dimension BULLET_DIM = new Dimension(30,50);

    public Bullet(Point initPos, Game game) {
        super(initPos, BULLET_DIM, SpriteTexLoader.load(SpriteTexLoader.SpriteTex.FIGHTER_MISSILE));
        this.game = game;
    }

    public void tick() {
        setY(getY()-13);

    }
}
