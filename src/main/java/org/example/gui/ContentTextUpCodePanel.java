package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentTextUpCodePanel extends JPanel implements ActionListener {
    private JPanel panelText;
    private JLabel labelText;
    private JTextArea txtText;
    private JPanel panelResponses;

    private JPanel panelCode;
    private JLabel labelCode;
    private JTextArea textCode;
    private JPanel mainPanel;

    public ContentTextUpCodePanel(){
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelText = new JPanel();
        this.labelText = new JLabel("Text");
        this.panelText.add(this.labelText);
        this.txtText = new JTextArea(5, 40);
        this.panelText.add(this.txtText);
        this.mainPanel.add(this.panelText);

        this.panelCode = new JPanel();
        this.labelCode = new JLabel("Code");
        this.panelCode.add(this.labelCode);
        this.textCode = new JTextArea(50, 50);
        this.panelCode.add(this.textCode);
        this.mainPanel.add(this.panelCode);

        this.panelResponses = new JPanel();

        JLabel labelResponses = new JLabel("Responses:");
        panelResponses.add(labelResponses);

        JPanel answersContainer = new JPanel();
        answersContainer.setLayout(new BoxLayout(answersContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollAnswers = new JScrollPane(answersContainer);
        scrollAnswers.setPreferredSize(new Dimension(800, 100));
        panelResponses.add(scrollAnswers);


        ButtonAddAnswer btnAddAnswer = new ButtonAddAnswer(answersContainer);
        panelResponses.add(btnAddAnswer);
        this.mainPanel.add(panelResponses);

        this.add(this.mainPanel);
    }

    public String getContent() {
        return txtText.getText();
    }

    public String getCode(){
        return textCode.getText();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }
}
