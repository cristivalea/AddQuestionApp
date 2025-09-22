package org.example.gui;

import org.example.core.Dificulty;
import org.example.core.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThirdScreenFrame extends JFrame implements ActionListener {
    private Question question;
    private JPanel panelDificulty;
    private JComboBox<Dificulty> comboDificulty;
    private JLabel labelDificulty;
    private JPanel panelDuration;
    private JLabel labelDuration;
    private JComboBox<Short> comboDuration;
    private JPanel panelHint;
    private JLabel labelHint;
    private JTextField txtHint;
    private JPanel mainPanel;
    private ButtonAddQuestion buttonAddQuestion;
    public ThirdScreenFrame(Question question){
        this.question = question;
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelDificulty = new JPanel();
        this.labelDificulty = new JLabel("Dificulty");
        this.comboDificulty = new JComboBox<>(Dificulty.values());
        this.panelDificulty.add(this.labelDificulty);
        this.panelDificulty.add(this.comboDificulty);
        this.mainPanel.add(this.panelDificulty);

        this.panelDuration = new JPanel();
        this.labelDuration = new JLabel("Duration time");
        Short[] time = {60, 100, 150};
        this.comboDuration = new JComboBox<>(time);
        this.panelDuration.add(this.labelDuration);
        this.panelDuration.add(this.comboDuration);
        this.mainPanel.add(this.panelDuration);

        this.panelHint = new JPanel();
        this.labelHint = new JLabel("Hint");
        this.txtHint = new JTextField(100);
        this.panelHint.add(this.labelHint);
        this.panelHint.add(this.txtHint);
        this.mainPanel.add(this.panelHint);

        this.buttonAddQuestion = new ButtonAddQuestion(question, comboDificulty, comboDuration, txtHint);
        this.buttonAddQuestion.addActionListener(this);
        this.mainPanel.add(this.buttonAddQuestion);

        this.add(this.mainPanel);
        this.setVisible(true);
        this.pack();
        this.setSize(1500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }

}
