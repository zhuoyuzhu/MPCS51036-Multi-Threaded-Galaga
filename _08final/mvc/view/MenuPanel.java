package _08final.mvc.view;

import _08final.images.SpriteTexLoader;
import _08final.mvc.model.Sprite;
import _08final.mvc.controller.Game;
import _08final.sounds.Sound;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by zhuoyuzhu on 11/27/16.
 */
public class MenuPanel extends JPanel {

    BufferedImage image;

    public void mainMenu(Graphics g) {
        try {
            image = ImageIO.read(new File("/Users/zhuoyuzhu/zhuoyuzhu/proJava/src/_08final/images/Galaga.png"));
            g.setColor(Color.white);
            g.drawImage(image, 185, 60, 340, 180, null);
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);
            g.drawString("Play: Hit P", 280, 270);
            g.drawString("Help: Hit H", 280, 370);
            g.drawString("Quit: Hit Q", 280, 470);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void helpMenu(Graphics g) {
        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawString("Instruction", 295, 50);
        g.drawString("Space Bar: Launch Missile", 220, 130);
        g.drawString("Up arrow: Move up", 220, 200);
        g.drawString("Right arrow: Move right", 220, 270);
        g.drawString("Down arrow: Move down", 220, 340);
        g.drawString("Left arrow: Move up", 220, 410);
        g.drawString("Esc: Return to Main", 220, 480);
    }

}
