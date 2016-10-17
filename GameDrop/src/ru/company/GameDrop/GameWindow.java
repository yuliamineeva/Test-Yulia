package ru.company.GameDrop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame {
    private static GameWindow gameWindow;
    private static Image background;
    private static Image gameOver;
    private static Image drop;
    private static long lastFrameTime;
    private static float dropV = 200; //скорость капли
    private static float dropLeft = 200; // верхняя левая координата капли по оси Х
    private static float dropTop = -100; // верхняя левая координата капли по оси Y
    private static int score; // счетчик очков

    public static void main(String[] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(906, 478);
        gameWindow.setResizable(false);
        lastFrameTime = System.nanoTime(); //время запуска программы
        GameField gameField = new GameField();
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float dropRight = dropLeft + drop.getWidth(null); // правая координата капли
                float dropBottom = dropTop + drop.getHeight(null); // нижняя граница капли
                boolean isDrop = x >= dropLeft && x <= dropRight && y >= dropTop && y <= dropBottom;
                if(isDrop){
                    dropTop = -100;
                    dropLeft = (int) (Math.random() * (gameField.getWidth() - drop.getWidth(null)));
                    dropV = dropV + 20;
                    score++;
                    gameWindow.setTitle("Счет: " + score);
                }
            }
        });
        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }

    private static void onRepaint(Graphics g) {
        long currentTime = System.nanoTime(); // текущее время
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f; //перевод в наносекунды -время между кадрами
        lastFrameTime = currentTime;
        dropTop = dropTop + dropV * deltaTime;
//        dropLeft = dropLeft + dropV * deltaTime;
        g.drawImage(background, 0, 0, null);
        g.drawImage(drop, (int) dropLeft, (int) dropTop, null);
        if (dropTop > gameWindow.getHeight()) {
            g.drawImage(gameOver, 280, 120, null);
        }
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
