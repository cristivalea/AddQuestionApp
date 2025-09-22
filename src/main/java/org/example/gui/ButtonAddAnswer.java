package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonAddAnswer extends JButton implements Command {
    private JPanel answersContainer;

    public ButtonAddAnswer(JPanel answersContainer) {
        super("Add Answer");
        this.answersContainer = answersContainer;
        this.addActionListener(e -> execute());
    }

    @Override
    public void execute() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtAnswer = new JTextField(30);
        JCheckBox chkCorrect = new JCheckBox("True");

        row.add(txtAnswer);
        row.add(chkCorrect);

        answersContainer.add(row);
        answersContainer.revalidate();
        answersContainer.repaint();
    }
}