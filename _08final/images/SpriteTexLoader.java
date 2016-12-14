package _08final.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class easily loads the textures for the sprites in the game
 *
 * @version  1.0
 * @author Lamont Samuels
 * @since  11-13-16
 */

public class SpriteTexLoader {

    private static SpriteTexLoader sInstance = new SpriteTexLoader();

    public enum SpriteTex {
        SHIP,
        BACKGROUND,
        FIGHTER_MISSILE,
        ENEMY_MISSILE,
        RED_FIGHTER,
        YELLOW_FIGHTER,
        BLUE_FIGHTER,
        GREEN_BOSS,
        PINK_BOSS,
        GALAGA,
        EN_EX1,
        EN_EX2,
        EN_EX3,
        EN_EX4,
        EN_EX5,
        FI_EX1,
        FI_EX2,
        FI_EX3,
        FI_EX4,
        FI_EX5
    }

    private SpriteTexLoader() {}
    /**
     * Retrieves the file path for the a Sprite texture in the images file
     * @param sprite - the sprite file path to retrieve
     */
    private static String getSpriteFile(SpriteTex sprite) {

        String file = "";
        switch (sprite) {
            case GALAGA:
                file = "Galaga.png";
                break;
            case BACKGROUND:
                file = "background.png";
                break;
            case SHIP:
                file = "ship.png";
                break;
            case YELLOW_FIGHTER:
                file = "yellow_fighter.png";
                break;
            case RED_FIGHTER:
                file = "red_fighter.png";
                break;
            case FIGHTER_MISSILE:
                file = "fighter_missile.png";
                break;
            case ENEMY_MISSILE:
                file = "enemy_missile.png";
                break;
            case BLUE_FIGHTER:
                file = "blue_fighter.png";
                break;
            case EN_EX1:
                file = "enemy_explosion_1.png";
                break;
            case EN_EX2:
                file = "enemy_explosion_2.png";
                break;
            case EN_EX3:
                file = "enemy_explosion_3.png";
                break;
            case EN_EX4:
                file = "enemy_explosion_4.png";
                break;
            case EN_EX5:
                file = "enemy_explosion_5.png";
                break;
            case FI_EX1:
                file = "fighter_explosion_1.png";
                break;
            case FI_EX2:
                file = "fighter_explosion_2.png";
                break;
            case FI_EX3:
                file = "fighter_explosion_3.png";
                break;
            case FI_EX4:
                file = "fighter_explosion_4.png";
                break;
            case FI_EX5:
                file = "fighter_explosion_5.png";
                break;
        }
        return file;
    }

    /**
     * Returns a buffered image from the images directory of a particular sprite
     * @param sprite - the sprite texture to load
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static BufferedImage load(SpriteTex sprite)  throws IllegalArgumentException {

        if (sprite == null){
            throw new IllegalArgumentException("Sprite texture parameter must not be null");
        }
        BufferedImage img = null;
        try {
            String file = getSpriteFile(sprite);
            img = ImageIO.read(sInstance.getClass().getResource(file));
        }catch (IOException e){
            System.out.print("Could not open texture :" + sprite.toString());
            System.exit(1);
        }
        return img;
    }

}
