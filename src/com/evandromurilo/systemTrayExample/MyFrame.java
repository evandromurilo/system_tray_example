package com.evandromurilo.systemTrayExample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evandro Murilo on 6/8/17.
 */

public class MyFrame extends JFrame {
    private static BufferedImage icon;

    public MyFrame() {
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/images/icon.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        };

        setTitle("Bonjour");
        this.setPreferredSize(new Dimension(350, 350));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel picLabel = new JLabel(new ImageIcon(icon));
        getContentPane().add(picLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                minimizeToTray();
            }
        });

        pack();
        setVisible(true);
    }

    private void minimizeToTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray não é suportado.");
            System.exit(1);
        }

        PopupMenu popup = new PopupMenu();
        TrayIcon trayIcon = new TrayIcon(icon);
        SystemTray tray = SystemTray.getSystemTray();

        MenuItem openItem = new MenuItem("Restaurar");
        MenuItem closeItem = new MenuItem("Sair");

        popup.add(openItem);
        popup.add(closeItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Tray icon não pôde ser adicionado.");
            System.exit(1);
        }

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand() != null && actionEvent.getActionCommand().equals("Sair")) {
                    System.exit(0);
                }

                setVisible(true);
                tray.remove(trayIcon);
            }
        };

        popup.addActionListener(listener);
        trayIcon.addActionListener(listener);
    }
}
