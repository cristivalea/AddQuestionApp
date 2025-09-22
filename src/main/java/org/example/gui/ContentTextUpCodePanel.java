package org.example.gui;

import org.example.core.AbstractResponse;
import org.example.core.ResponseText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ContentTextUpCodePanel extends JPanel implements ActionListener {
    private JPanel panelText;
    private JLabel labelText;
    private JTextArea txtText;
    private JPanel panelResponses;

    private JPanel panelCode;
    private JLabel labelCode;
    private JTextArea textCode;
    private JPanel mainPanel;
    private JPanel answersContainer;

    public ContentTextUpCodePanel(){
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelText = new JPanel();
        this.labelText = new JLabel("Text");
        this.panelText.add(this.labelText);
        this.txtText = new JTextArea(5, 40);
        this.panelText.add(this.txtText);

        this.panelCode = new JPanel();
        this.labelCode = new JLabel("Code");
        this.textCode = new JTextArea(35, 50);
        this.panelText.add(this.labelCode);
        this.panelText.add(this.textCode);
        this.mainPanel.add(this.panelText);

        this.panelResponses = new JPanel();
        JLabel labelResponses = new JLabel("Responses:");
        panelResponses.add(labelResponses);

        answersContainer = new JPanel();
        answersContainer.setLayout(new BoxLayout(answersContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollAnswers = new JScrollPane(answersContainer);
        scrollAnswers.setPreferredSize(new Dimension(800, 50));
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

    public java.util.List<AbstractResponse> getResponses() {
        List<AbstractResponse> responses = new ArrayList<>();
        int id = 1;

        for (Component c : answersContainer.getComponents()) {
            if (c instanceof JPanel) {
                JPanel row = (JPanel) c;
                for (Component sub : row.getComponents()) {
                    if (sub instanceof JTextField tf) {
                        String text = tf.getText().trim();
                        if (!text.isEmpty()) {
                            responses.add(new ResponseText(id, text));
                        }
                    }
                }
                id++;
            }
        }

        return responses;
    }

    public boolean[] getAnswers() {
        int size = answersContainer.getComponentCount();
        boolean[] answers = new boolean[size];
        int idx = 0;

        for (Component c : answersContainer.getComponents()) {
            if (c instanceof JPanel) {
                JPanel row = (JPanel) c;
                for (Component sub : row.getComponents()) {
                    if (sub instanceof JCheckBox) {
                        answers[idx] = ((JCheckBox) sub).isSelected();
                    }
                }
                idx++;
            }
        }
        return answers;
    }
}
