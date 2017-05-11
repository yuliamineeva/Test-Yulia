package View;

import Controller.Controller;
import Model.*;
import Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.ColorUIResource;

import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.CENTER;


public class GameWindow extends JFrame {
    GreetingWindow greetingWindow = new GreetingWindow();
    ChoiceWindow choiceWindow = new ChoiceWindow();
    JPanel jPanelField = new JPanel();
    JPanel jPanelPlayer = new JPanel();
    JPanel jPanelComp = new JPanel();
    JPanel jPanelHeader = new JPanel();
    JButton[][] buttonsComp = new JButton[Field.SIZE + 1][Field.SIZE + 1];
    JButton[][] buttonsPlayer = new JButton[Field.SIZE + 1][Field.SIZE + 1];
    JLabel statusLabel1 = new JLabel("Hello", LEFT);
    JLabel statusLabel2 = new JLabel("Морской бой", LEFT);
    JLabel statusLabel3 = new JLabel("***", LEFT);
    JLabel headerPlayer = new JLabel("Поле игрока", CENTER);
    JLabel jLabelPlayer = new JLabel("", CENTER);
    JLabel headerComp = new JLabel("Поле компьютера", CENTER);
    JLabel jLabelComp = new JLabel("", CENTER);
    Color invisibleColor = new ColorUIResource(238, 238,238);
    Boolean isVisible = false;

    public void init() {
        greetingWindow.init();
        choiceWindow.init(greetingWindow);
        initBattleWindow();
    }

    public void initBattleWindow() {
        setSize(1100, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SeaBattle");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        makeLabelSettings(statusLabel3);
        makeLabelSettings(statusLabel2);
        makeLabelSettings(statusLabel1);
        makeHeaderSettings(headerPlayer);
        makeHeaderSettings(headerComp);
        makeHeaderSettings(jLabelPlayer);
        makeHeaderSettings(jLabelComp);
        jPanelComp.setLayout(new GridLayout(Field.SIZE + 1, Field.SIZE + 1));
        for (int i = 0; i < buttonsComp.length; i++) {
            for (int j = 0; j < buttonsComp[i].length; j++) {
                JButton jButton = new JButton();
                if (i == 0 && j == 0) {
                    jButton.setText("***");
                    jButton.setBackground(Color.GREEN);
                } else if (j == 0 && i > 0) {
                    jButton.setText("" + i);
                    jButton.setBackground(Color.GREEN);
                } else if (i == 0 && j > 0) {
                    char upSymbol = (char) ('A' + j - 1);
                    jButton.setText(String.valueOf(upSymbol));
                    jButton.setBackground(Color.GREEN);
                } else {
                    int finalJ = j;
                    int finalI = i;
                    invisibleColor = jButton.getBackground();
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String buttonText = jButton.getText();
                            Controller.doShoot(new Point(finalJ - 1, finalI - 1), buttonText);
                        }
                    });
                }
                jButton.setPreferredSize(new Dimension(35, 35));
                buttonsComp[i][j] = jButton;
                jPanelComp.add(jButton);
            }
        }
        jPanelPlayer.setLayout(new GridLayout(Field.SIZE + 1, Field.SIZE + 1));
        for (int i = 0; i < buttonsPlayer.length; i++) {
            for (int j = 0; j < buttonsPlayer[i].length; j++) {
                JButton jButton = new JButton();
                if (i == 0 && j == 0) {
                    jButton.setText("***");
                    jButton.setBackground(Color.GREEN);
                } else if (j == 0 && i > 0) {
                    jButton.setText("" + i);
                    jButton.setBackground(Color.GREEN);
                } else if (i == 0 && j > 0) {
                    char upSymbol = (char) ('A' + j - 1);
                    jButton.setText(String.valueOf(upSymbol));
                    jButton.setBackground(Color.GREEN);
                }
                jButton.setPreferredSize(new Dimension(35, 35));
                buttonsPlayer[i][j] = jButton;
                jPanelPlayer.add(jButton);
            }
        }

        jPanelHeader.setLayout(new GridLayout(2, 2));
        jPanelHeader.add(jLabelPlayer);
        jPanelHeader.add(jLabelComp);
        jPanelHeader.add(headerComp);
        jPanelHeader.add(headerPlayer);

        jPanelField.setLayout(new GridLayout(1, 2, 0, 5));
        jPanelField.add(jPanelComp);
        jPanelField.add(jPanelPlayer);

        add(statusLabel3);
        add(statusLabel2);
        add(statusLabel1);
        add(jPanelHeader);
        add(jPanelField);
        setLocationRelativeTo(null);  // установить окно по центру

        while (!choiceWindow.getIsClose()) {
            setVisible(false);
        }
        if (choiceWindow.getIsClose()) {
            updateLabel(greetingWindow.getPlayersName() + ", игра начинается!");
            setVisible(true);
        }

    }

    private void makeLabelSettings(JLabel jLabel) {
        jLabel.setFont(new Font(null, Font.PLAIN, 20));
        jLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        jLabel.setAlignmentX(this.getContentPane().CENTER_ALIGNMENT);
        if (jLabel == statusLabel1) {
            jLabel.setForeground(Color.BLUE);
        } else if (jLabel == statusLabel2) {
            jLabel.setForeground(Color.GREEN);
        } else {
            jLabel.setForeground(Color.MAGENTA);
        }
    }

    private void makeHeaderSettings(JLabel jLabel) {
        if (jLabel == jLabelPlayer || jLabel == jLabelComp) {
            jLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 5));
            jLabel.setFont(new Font(null, Font.PLAIN, 20));
        } else {
            jLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
            jLabel.setFont(new Font(null, Font.PLAIN, 14));
            jLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

    }

    public void update(Field.Symbol[][] symComp, Field.Symbol[][] symPlayer) {
        for (int i = 1; i < buttonsComp.length; i++) {
            for (int j = 1; j < buttonsComp[i].length; j++) {
                switch (symComp[i - 1][j - 1]) {
                    case BLANK:
                        buttonsComp[i][j].setText(" ");
                        break;
                    case MISS:
                        buttonsComp[i][j].setText("*");
                        buttonsComp[i][j].setBackground(Color.GRAY);
                        break;
                    case HIT:
                        buttonsComp[i][j].setText("D");
                        buttonsComp[i][j].setBackground(Color.blue);
                        break;
                    case SHIP:
                        if(isVisible){
                            buttonsComp[i][j].setText("S");
                            buttonsComp[i][j].setBackground(Color.CYAN);
                        } else {
                            buttonsComp[i][j].setBackground(invisibleColor);
                        }
                        break;
                }
            }
        }
        for (int i = 1; i < buttonsPlayer.length; i++) {
            for (int j = 1; j < buttonsPlayer[i].length; j++) {
                switch (symPlayer[i - 1][j - 1]) {
                    case BLANK:
                        buttonsPlayer[i][j].setText(" ");
                        break;
                    case MISS:
                        buttonsPlayer[i][j].setText("*");
                        buttonsPlayer[i][j].setBackground(Color.GRAY);
                        break;
                    case HIT:
                        buttonsPlayer[i][j].setText("D");
                        buttonsPlayer[i][j].setBackground(Color.BLUE);
                        break;
                    case SHIP:
                        buttonsPlayer[i][j].setText("S");
                        buttonsPlayer[i][j].setBackground(Color.CYAN);
                        break;
                }
            }
        }
    }

    public void updateLabel(String newLabel) {
        statusLabel3.setText(statusLabel2.getText());
        statusLabel2.setText(statusLabel1.getText());
        statusLabel1.setText(newLabel);
        Color color = statusLabel3.getForeground();
        statusLabel3.setForeground(statusLabel2.getForeground());
        statusLabel2.setForeground(statusLabel1.getForeground());
        statusLabel1.setForeground(color);
    }

    public void updateHeader(Boolean isPlayer) {
        if (isPlayer) {
            jLabelPlayer.setText("Ваш ход, " + greetingWindow.getPlayersName());
            jLabelPlayer.setBackground(Color.GREEN);
            jLabelPlayer.setOpaque(true); //чтобы фон стал непрозрачным
            jLabelPlayer.setForeground(Color.RED);
            jLabelComp.setText("");
            jLabelComp.setOpaque(false);
        } else {
            jLabelComp.setText("Ход компьютера");
            jLabelComp.setBackground(Color.GREEN);
            jLabelComp.setOpaque(true);
            jLabelComp.setForeground(Color.RED);
            jLabelPlayer.setOpaque(false);
            jLabelPlayer.setText("");
        }
    }
}
