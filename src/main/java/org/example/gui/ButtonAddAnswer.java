package org.example.gui;

import javax.swing.*;
import java.awt.*;

//public class ButtonAddAnswer extends JButton implements Command {
//    private JPanel answersContainer;
//
//    public ButtonAddAnswer(JPanel answersContainer) {
//        super("Add Answer");
//        this.answersContainer = answersContainer;
//        this.addActionListener(e -> execute());
//    }
//
//    @Override
//    public void execute() {
//        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JTextField txtAnswer = new JTextField(30);
//        JCheckBox chkCorrect = new JCheckBox("True");
//
//        row.add(txtAnswer);
//        row.add(chkCorrect);
//
//        answersContainer.add(row);
//        answersContainer.revalidate();
//        answersContainer.repaint();
//    }
//}

    public class ButtonAddAnswer extends JButton implements Command {
        private JPanel answersContainer;

        public ButtonAddAnswer(JPanel answersContainer) {
            super("Add Answer");
            this.answersContainer = answersContainer;
            this.addActionListener(e -> execute());
        }

        @Override
        public void execute() {
            // creez un JPanel pentru un rând
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));

            // radio buttons pentru tipul răspunsului
            JRadioButton textOption = new JRadioButton("Text");
            JRadioButton codeOption = new JRadioButton("Code");
            ButtonGroup group = new ButtonGroup();
            group.add(textOption);
            group.add(codeOption);
            textOption.setSelected(true);

            // câmp text
            JTextField txtAnswer = new JTextField(20);

            // câmp cod (ascuns la început)
            JTextArea txtCode = new JTextArea(3, 20);
            txtCode.setVisible(false);
            JScrollPane scrollCode = new JScrollPane(txtCode);

            // checkbox pentru corectitudine
            JCheckBox chkCorrect = new JCheckBox("True");

            // adaug componentele în rând
            row.add(textOption);
            row.add(codeOption);
            row.add(txtAnswer);
            row.add(scrollCode);
            row.add(chkCorrect);

            // listener pentru radio buttons
            textOption.addActionListener(e -> txtCode.setVisible(false));
            codeOption.addActionListener(e -> txtCode.setVisible(true));

            // adaug rândul în container
            answersContainer.add(row);
            answersContainer.revalidate();
            answersContainer.repaint();
        }
    }