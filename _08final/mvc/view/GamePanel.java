package _08final.mvc.view;

import _08final.images.SpriteTexLoader;
import _08final.mvc.controller.Game;
import _08final.mvc.model.Explosion;
import _08final.mvc.model.Sprite;
import _08final.mvc.model.Ship;
import _08final.sounds.Sound;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.*;
import java.util.Scanner;
import java.util.*;

import javax.swing.*;
import java.awt.*;

import javax.sound.sampled.Clip;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhuoyuzhu on 11/26/16.
 */
public class GamePanel extends JPanel {

    private Game game;
    private ArrayList<Integer> score;
    private MenuPanel menuPanel = new MenuPanel();
    private int i = 1;

    public GamePanel() {
        this.setPreferredSize(GameFrame.FRAME_DIM);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Call the super paintComponent of the panel
        super.paintComponent(g);
        g.drawImage(SpriteTexLoader.load(SpriteTexLoader.SpriteTex.BACKGROUND), 0, 0,
                    GameFrame.FRAME_DIM.width, GameFrame.FRAME_DIM.height, this);

        //Start redrawing all the objects of the game.
        ArrayList<Sprite> sprites = Game.getDrawableSprites();
        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);

        for(Sprite sprite : sprites) {
            if (Game.status == Game.STATUS.MENU) {
                menuPanel.mainMenu(g);
            } else if (Game.status == Game.STATUS.HELP) {
                menuPanel.helpMenu(g);
            } else if (Game.status == Game.STATUS.GAME_OVER) {
                Font fnt2 = new Font("arial", Font.BOLD, 50);
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawString("Game Over!", 215, 370);
                try {
                    int s = Game.score;
                    if (i == 1) {
                        FileWriter fw = new FileWriter("/Users/zhuoyuzhu/zhuoyuzhu/zhuoyuzhu/proJava/src/_08final/mvc/view/score.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        String st = Integer.toString(s);
                        bw.append("\n" + st);
                        bw.close();
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    score = new ArrayList<>();
                    File inputFile = new File("/Users/zhuoyuzhu/zhuoyuzhu/zhuoyuzhu/proJava/src/_08final/mvc/view/score.txt");
                    Scanner in = new Scanner(inputFile);
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        int num = Integer.parseInt(line);
                        score.add(num);
                    }
                    Collections.sort(score, Collections.reverseOrder());
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int gap = 135;
                for (int i = 0; i < score.size(); i++) {
                    g.drawString("" + score.get(i), 5, gap);
                    gap += 20;
                }

                g.drawString("Health: ", 5, 20);
                g.drawString("Score: " + game.getScore(), 5, 70);
                g.drawString("Ship Life: " + game.getLife(), 5, 90);
                g.drawString("Score List: ", 5, 110);
                g.setColor(Color.gray);
                g.fillRect(5, 25, 100, 25);
                g.setColor(Color.green);
                g.fillRect(5, 25, game.HEALTH, 25);
                g.setColor(Color.white);
                g.drawRect(5, 25, 100, 25);

                sprite.draw(g);
            }
        }
    }
}