package _08final.mvc.model;

import java.util.ArrayList;

/**
 * Created by zhuoyuzhu on 12/7/16.
 */
public class Physics {

    public static boolean Collision(Ship s, ArrayList<RedEnemy> r) {
        for (int i = 0; i < r.size(); i++) {
            if (s.getBounds().intersects(r.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(RedEnemy r, ArrayList<Bullet> b) {
        for (int i = 0; i < b.size(); i++) {
            if (r.getBounds().intersects(b.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(RedEnemy r, Ship s) {
        if (r.getBounds().intersects(s.getBounds())) {
            return true;
        }
        return false;
    }

    public static boolean Collision(YellowEnemy y, ArrayList<Bullet> b) {
        for (int i = 0; i < b.size(); i++) {
            if (y.getBounds().intersects(b.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(YellowEnemy y, Ship s) {
        if (y.getBounds().intersects(s.getBounds())) {
            return true;
        }
        return false;
    }

    public static boolean Collision(BlueEnemy bl, ArrayList<Bullet> b) {
        for (int i = 0; i < b.size(); i++) {
            if (bl.getBounds().intersects(b.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(BlueEnemy bl, Ship s) {
        if (bl.getBounds().intersects(s.getBounds())) {
            return true;
        }
        return false;
    }

}
