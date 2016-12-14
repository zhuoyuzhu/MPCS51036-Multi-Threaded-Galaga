package _08final.mvc.controller;

/**
 * Created by zhuoyuzhu on 11/26/16.
 */
import _08final.mvc.model.*;
import _08final.mvc.view.GameFrame;
import _08final.mvc.view.MenuPanel;
import _08final.mvc.view.GamePanel;
import _08final.sounds.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by lamont on 11/20/16.
 *
 * 1. Runnable is a part of a thread
 */

public class Game implements Runnable, KeyListener {

    /** Represents the JFrame for the game */
    private GameFrame mGameFrame;

    /** Represents the Animation delay between frames */
    public final static int DRAW_DELAY = 45;

    /** The thread that handles the render loop for the game */
    private Thread mRenderThread;

    /** Represents the ship in the game */
    public Ship mShip;

    /** */
    private boolean isShooting = false;

    /** List of Sprites that need to be rendered  */
    public static ArrayList<Sprite> _sprites = new ArrayList<Sprite>();

    public static ArrayList<RedEnemy> er;
    public static ArrayList<YellowEnemy> ey;
    public static ArrayList<BlueEnemy> eb;

    private double deltaTimeInSecs;
    public static int score;
    public static int shipLife = 3;
    private EnemyController ec;
    public static Explosion e1;
    public static Explosion e2;
    public static Explosion e3;
    public static Explosion e4;
    public static Explosion e5;
    public static Explosion f1;
    public static Explosion f2;
    public static Explosion f3;
    public static Explosion f4;
    public static Explosion f5;
    public static int HEALTH = 100;


    public Game() {
        this.mGameFrame = new GameFrame(this);
        this.mShip = new Ship(new Point(330,600));
        Game._sprites.add(mShip);

        this.ec = new EnemyController(this);
        er = ec.getRed();
        ey = ec.getYellow();
        eb = ec.getBlue();

        if (Game.status == STATUS.MENU) {
            try{
                String s = "/_08final/sounds/themeSong.wav";
                Sound.playSound(s);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum STATUS{
        MENU,
        GAME,
        HELP,
        ENEMY_EXPLOSION,
        GAME_OVER
    };

    public static STATUS status = STATUS.MENU;

    public static int getScore() {
        return score;
    }

    public void setScore(int bonus) {
        score += bonus;
    }

    public static int getLife() {
        return shipLife;
    }

    public void setLife(int life) {
        shipLife += life;
    }

    /**
     * Starts the thread that will handle the render loop for the game
     */
    private void startRenderLoopThread() {
        //Check to make sure the render loop thread has not begun
        if (this.mRenderThread == null) {
            //All threads that are created in java need to be passed a Runnable object.
            //In this case we are making the "Runnable Object" the actual game instance.
            this.mRenderThread = new Thread(this);
            //Start the thread
            this.mRenderThread.start();
        }
    }

    /**
     * This represents the method that will be called for a Runnable object when a thread starts.
     * In this case, this run method represents the render loop.
     */
    public void run() {

        //Make this thread a low priority such that the main thread of the Event Dispatch is always is
        //running first.
        this.mRenderThread.setPriority(Thread.MIN_PRIORITY);

        //Get the current time of rendering this frame
        long elapsedTime = System.currentTimeMillis();

        long currentTime = 0;
        long lastTime = 0;
        long deltaTime = 0;

        // this thread animates the scene
        while (Thread.currentThread() == this.mRenderThread) {

            currentTime = System.currentTimeMillis();

            if(lastTime == 0){
                lastTime = currentTime;
                deltaTime = 0;
            }else {
                deltaTime = currentTime - lastTime;
                lastTime = currentTime;
                tick(deltaTime);
            }

            /************* Update game HERE
             * - Move the game models
             * - Check for collisions between the bullet, or fighters and the ship
             * - Check whether we should move to a new level potentially.
             * Do the update about enemy and ship status
             */

            //Redraw the game frame with to visually show the updated game state.
            this.mGameFrame.draw();

            try {
                /** We want to ensure that the drawing time is at least the DRAW_DELAY we specified. */
                elapsedTime += DRAW_DELAY;
                Thread.sleep(Math.max(0, elapsedTime - currentTime));
            } catch (InterruptedException e) {
                //If an interrupt occurs then you can just skip this current frame.
                continue;
            }
        }
    }


    private void tick(long deltaTime) {
        if (status == STATUS.GAME) {
            mShip.tick(deltaTime);
            ec.tick(deltaTime);
        }
    }


    /***
     * Generates all the drawable sprites for the game currently
     * @return an arraylist of all the drawable sprites in the game
     */
    public static ArrayList<Sprite> getDrawableSprites() {
        return new ArrayList<Sprite>(_sprites);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int nKey = e.getKeyCode();
        if (status == STATUS.GAME) {
            switch (nKey) {
                case KeyEvent.VK_LEFT:
                    this.mShip.moveLeft(1);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.mShip.moveRight(1);
                    break;
                case KeyEvent.VK_DOWN:
                    this.mShip.moveDown(1);
                    break;
                case KeyEvent.VK_UP:
                    this.mShip.moveUp(1);
                    break;
                case KeyEvent.VK_SPACE:
                    // Force the user to release the space bar before shooting again
                    if (!isShooting) {
                        isShooting = true;
                        ec.addBullet(new Bullet(new Point(mShip.getX(), mShip.getY()), this));
                        String s = "/_08final/sounds/firing.wav";
                        Sound.playSound(s);
                    }
                    break;
                default:
                    System.out.println("Pressing the key: " + KeyEvent.getKeyText(nKey));
                    break;
            }
        } else {
            switch (nKey) {
                case KeyEvent.VK_P:
                    Game.status = STATUS.GAME;
                    break;
                case KeyEvent.VK_H:
                    Game.status = STATUS.HELP;
                    break;
                case KeyEvent.VK_Q:
                    System.exit(1);
                    break;
                case KeyEvent.VK_ESCAPE:
                    Game.status = STATUS.MENU;
                    break;
                default:
                    System.out.println("Pressing the key: " + KeyEvent.getKeyText(nKey));
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int nKey = e.getKeyCode();

        switch (nKey) {
            case KeyEvent.VK_LEFT:
                this.mShip.moveLeft(0);
                break;
            case KeyEvent.VK_RIGHT:
                this.mShip.moveRight(0);
                break;
            case KeyEvent.VK_DOWN:
                this.mShip.moveDown(0);
                break;
            case KeyEvent.VK_UP:
                this.mShip.moveUp(0);
                break;
            case KeyEvent.VK_SPACE:
                isShooting = false;
                break;
            default:
                System.out.println("Pressing the key: " + KeyEvent.getKeyText(nKey));
                break;
        }
    }
    public void keyTyped(KeyEvent e) {}

    public static void main(String args[]) {
        // Create a thread that will not affect main thread
        EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
            public void run() {
                try {
                    //Construct the game controller
                    Game game = new Game();
                    //Start the render loop for the game
                    game.startRenderLoopThread();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

