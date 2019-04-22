package com.github.moraesofia.pjns.ui;

import javax.swing.*;
import java.io.File;

public class FileDialog {

    private JFrame frame;

    public FileDialog() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);
    }

    public File showOpenFile() {
        JFileChooser chooser = new JFileChooser();
        int chosenOption = chooser.showOpenDialog(null);
        if (chosenOption == JFileChooser.APPROVE_OPTION) {
            frame.setVisible(false);
            return chooser.getSelectedFile();
        } else {
            frame.setVisible(false);
            return null;
        }
    }

    public File showSaveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("dados.json"));
        int chosenOption = chooser.showSaveDialog(null);
        if (chosenOption == JFileChooser.APPROVE_OPTION) {
            frame.setVisible(false);
            return chooser.getSelectedFile();
        } else {
            frame.setVisible(false);
            return null;
        }
    }
}
