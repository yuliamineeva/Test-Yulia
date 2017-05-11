package View;

import Controller.Controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.SwingConstants.CENTER;

public class ChoiceWindow extends JFrame {
    String title = "Выбор расстановки кораблей";
    boolean isClose;

    public boolean getIsClose() {
        return isClose;
    }

    public void setIsClose(boolean close) {
        isClose = close;
    }


    public void init(GreetingWindow greetingWindow) {
        setSize(400, 250);
        setTitle(title);
        JLabel jLabel = new JLabel("Выберете способ установки корабля: ", CENTER);
        jLabel.setFont(new Font(null, Font.PLAIN, 14));
        jLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        JRadioButton autoButton = new JRadioButton("1. Автоматическая расстановка");
        autoButton.setFont(new Font(null, Font.PLAIN, 14));
        autoButton.setActionCommand("auto");
        autoButton.setSelected(true);
        JRadioButton manualButton = new JRadioButton("2. Ручная расстановка");
        manualButton.setFont(new Font(null, Font.PLAIN, 14));
        manualButton.setActionCommand("manual");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 5));
        ButtonGroup group = new ButtonGroup();
        group.add(autoButton);
        group.add(manualButton);
        buttonPanel.add(autoButton);
        buttonPanel.add(manualButton);
        JButton jButton = new JButton("OK");
        getRootPane().setDefaultButton(jButton);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();
                Controller.chooseAlignment(command.equals("manual") ? 2 : 1);
                setIsClose(true);
                dispose();
            }
        });
        add(jLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(jButton, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        while (!greetingWindow.getIsClose()) {
            setVisible(false);
        }
        if (greetingWindow.getIsClose()) {
            jLabel.setText(greetingWindow.getPlayersName() + ", выберете способ установки корабля: ");
            setVisible(true);
        }
    }
}

