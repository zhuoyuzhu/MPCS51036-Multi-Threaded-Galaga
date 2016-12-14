package _08final.mvc.model;

/**
 * Created by zhuoyuzhu on 11/26/16.
 */
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Sprite {

    /** The dimensions of the sprite */
    private Dimension mDim;

    /** The position of the sprite */
    protected Point mPos;

    /** The texture for the sprite */
    private BufferedImage mTex;

    public Sprite(Point initPos, Dimension dim, BufferedImage texture) {
        this.mPos = initPos;
        this.mDim = dim;
        this.mTex = texture;
    }
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(this.mTex, (int) this.mPos.getX(), (int) this.mPos.getY(), (int) this.mDim.getWidth(),
                (int) this.mDim.getHeight(), null);
    }


    public int getX() {
        return this.mPos.x;
    }

    public int getY() {
        return this.mPos.y;
    }

    public void setX(int x) {
        this.mPos.x = x;
    }

    public void setY(int y) {
        this.mPos.y = y;
    }

    public BufferedImage getTexture() {
        return this.mTex;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 20, 20);
    }

}
