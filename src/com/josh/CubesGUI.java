package com.josh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ec0662sr on 4/27/2016.
 */
public class CubesGUI extends JFrame implements KeyListener{

    private JPanel rootPanel;
    private JTable cubesTable;
    private JTextField newTimeField;
    private JTextField newNameField;
    private JButton addNewRecordButton;
    private JTextField updateTimeField;
    private JButton updateTimeButton;

    public CubesGUI(final CubesDataModel cubesDataModel) {

        addNewRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String name = newNameField.getText();
            String time = newTimeField.getText();

            }
        });
        updateTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int rowSelected = cubesTable.getSelectedRow();
            String time = updateTimeField.getText();
            }
        });
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
