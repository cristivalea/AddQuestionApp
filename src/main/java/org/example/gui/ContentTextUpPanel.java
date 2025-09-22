package org.example.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentTextUpPanel extends JPanel implements ActionListener {

    private JPanel panelText;
    private JLabel labelText;
    private JTextArea txtText;
    private JPanel mainPanel;

    public ContentTextUpPanel(){
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelText = new JPanel();
        this.labelText = new JLabel("Text");
        this.panelText.add(this.labelText);
        this.txtText = new JTextArea(5, 40);
        this.panelText.add(this.txtText);
        this.mainPanel.add(this.panelText);

        this.add(this.mainPanel);
    }

    public String getContent() {
        return txtText.getText();
    }


    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }
}
