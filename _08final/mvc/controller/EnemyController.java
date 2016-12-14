package _08final.mvc.controller;

import _08final.mvc.model.*;
import _08final.mvc.view.*;
import _08final.sounds.Sound;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhuoyuzhu on 11/28/16.
 */
public class EnemyController extends GamePanel {

    /**
     * Red enemy score = 20
     * Yellow enemy score = 50
     * Blue enemy score = 100
     */
    private ArrayList<YellowEnemy> Y = new ArrayList<YellowEnemy>();
    private ArrayList<RedEnemy> R = new ArrayList<RedEnemy>();
    private ArrayList<BlueEnemy> B = new ArrayList<BlueEnemy>();
    private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
    private ArrayList<Explosion> ex = new ArrayList<Explosion>();
    private ArrayList<Ship> sh = new ArrayList<Ship>();

    private final int RED_SCORE = 20;
    private final int YELLOW_SCORE = 50;
    private final int BLUE_SCORE = 100;

    YellowEnemy YEnemy;
    RedEnemy REnemy;
    BlueEnemy BEnemy;
    Bullet TempBullet;
    Game game;
    Explosion e;
    Ship s;

    public EnemyController(Game game) {
        this.game = game;
        int x = 125;
        for (int i = 0; i < 7; i++) {
            REnemy = new RedEnemy(new Point(x,100), game, this);
            addRedEnemy(REnemy);
            Game._sprites.add(REnemy);
            YEnemy = new YellowEnemy(new Point(x,50), game, this);
            addYellowEnemy(YEnemy);
            Game._sprites.add(YEnemy);
            BEnemy = new BlueEnemy(new Point(x,0), game, this);
            addBlueEnemy(BEnemy);
            Game._sprites.add(BEnemy);
            x += 70;
        }
    }

    public void tick(long deltaTime) {
        for (int i = 0; i < R.size(); i++) {
            REnemy = R.get(i);
            REnemy.tick(deltaTime, REnemy);
        }
        for (int i = 0; i < Y.size(); i++) {
            YEnemy = Y.get(i);
            YEnemy.tick(deltaTime, YEnemy);
        }
        for (int i = 0; i < B.size(); i++) {
            BEnemy = B.get(i);
            BEnemy.tick(deltaTime, BEnemy);
        }
        for (int i = 0; i < bullet.size(); i++) {
            TempBullet = bullet.get(i);

            if (TempBullet.getY() < 0) {
                removeBullet(TempBullet);
            }
            TempBullet.tick();
        }
        for (int i = 0; i < ex.size(); i++) {
            e = ex.get(i);
            removeEx(e);
        }
        if (Game.shipLife > 0 && !Game._sprites.contains(game.mShip)) {
            game.HEALTH = 100;
            game.mShip = new Ship(new Point(600,600));
            Game._sprites.add(game.mShip);
        }
        if (Game.shipLife == 0) {
            Game.status = Game.STATUS.GAME_OVER;
        }
    }

    public void addYellowEnemy(YellowEnemy newEnemy) {
        Y.add(newEnemy);
    }

    public void removeYellowEnemy(YellowEnemy finishedEnemy) {
        Y.remove(finishedEnemy);
        Game._sprites.remove(finishedEnemy);

        String s = "/_08final/sounds/enemyKilled.wav";
        Sound.playSound(s);
        game.setScore(YELLOW_SCORE);
    }

    public void addRedEnemy(RedEnemy newEnemy) {
        R.add(newEnemy);
    }

    public void removeRedEnemy(RedEnemy finishedEnemy) {
        R.remove(finishedEnemy);
        Game._sprites.remove(finishedEnemy);

        String s = "/_08final/sounds/enemyKilled.wav";
        Sound.playSound(s);
        game.setScore(RED_SCORE);
    }

    public void addBlueEnemy(BlueEnemy newEnemy) {
        B.add(newEnemy);
    }

    public void removeBlueEnemy(BlueEnemy finishedEnemy) {
        B.remove(finishedEnemy);
        Game._sprites.remove(finishedEnemy);

        String s = "/_08final/sounds/enemyKilled.wav";
        Sound.playSound(s);
        game.setScore(BLUE_SCORE);
    }

    public ArrayList<RedEnemy> getRed() {
        return R;
    }

    public ArrayList<YellowEnemy> getYellow() {
        return Y;
    }
    public ArrayList<BlueEnemy> getBlue() {
        return B;
    }

    public void addBullet(Bullet newBullet) {
        bullet.add(newBullet);
        Game._sprites.add(newBullet);
    }

    public void removeBullet(Bullet finishedBullet) {
        bullet.remove(finishedBullet);
        Game._sprites.remove(finishedBullet);
    }

    public ArrayList<Bullet> getBullet() {
        return bullet;
    }


    public void addEx(Explosion e) {
        ex.add(e);
        Game._sprites.add(e);
    }

    public void removeEx(Explosion e) {
        ex.remove(e);
        Game._sprites.remove(e);
    }

    public void addEx(Ship s) {
        sh.add(s);
        Game._sprites.add(s);
    }

    public void removeEx(Ship s) {
        sh.remove(s);
        Game._sprites.remove(s);
    }

}