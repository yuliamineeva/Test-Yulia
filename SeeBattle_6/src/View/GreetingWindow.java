package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.SwingConstants.CENTER;

public class GreetingWindow extends JFrame {
    String playersName;
    boolean isClose;

    public boolean getIsClose() {
        return isClose;
    }

    public void setIsClose(boolean close) {
        isClose = close;
    }

    public String getPlayersName() {
        return playersName;
    }

    public void setPlayersName(String playersName) {
        this.playersName = playersName;
    }


    public void init() {
        setSize(300, 200);
        setTitle("SeaBattle");
        setLayout(new GridLayout(4, 1));
        JLabel jLabel1 = new JLabel("Добро пожаловать в игру \"Морской бой\".", CENTER);
        JLabel jLabel2 = new JLabel("Введите Ваше имя: ", CENTER);
        JTextField textPlayersName = new JTextField();
        textPlayersName.setHorizontalAlignment(CENTER); // выравнивание по центру
        textPlayersName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textPlayersName.getText();
                setPlayersName(name);
                Controller.setName(name);
                setIsClose(true);
                dispose();
            }
        });
        JButton jButton = new JButton("OK");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textPlayersName.getText();
                setPlayersName(name);
                setIsClose(true);
                Controller.setName(name);
                dispose();
            }
        });
        add(jLabel1);
        add(jLabel2);
        add(textPlayersName);
        add(jButton);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}