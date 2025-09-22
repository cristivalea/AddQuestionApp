package org.example.gui;

import org.example.core.ContentTextUP;
import org.example.core.GuiType;
import org.example.core.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SecondScreenFrame extends JFrame implements ActionListener {
    private JPanel panelType;
    private JPanel mainPanel;
    private Question question;

    private JPanel contentPanel;
    private JButton nextButton;

    private GuiType selectedGuiType;
    public SecondScreenFrame(Question question){
        this.question = question;
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelType = new JPanel();
        this.panelType.setLayout(new BoxLayout(this.panelType, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();

        for(GuiType gui : GuiType.values()){
            JRadioButton rb = new JRadioButton(gui.getDenumire());
            buttonGroup.add(rb);
            this.panelType.add(rb);

            rb.addActionListener(e -> {
                selectedGuiType = gui;
                showContentPanel(gui);
            });
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> printQuestionInfo());
        this.mainPanel.add(this.panelType);
        this.add(this.mainPanel);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> printQuestionInfo());
        this.mainPanel.add(this.nextButton);

        this.setVisible(true);
        this.pack();
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }

    public GuiType getSelectedGuiType() {
        return selectedGuiType;
    }

    private void showContentPanel(GuiType gui) {
        if (contentPanel != null) {
            mainPanel.remove(contentPanel);
        }

        contentPanel = new ContentTextUpPanel();


        mainPanel.add(contentPanel, mainPanel.getComponentCount() - 1);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void printQuestionInfo() {
        if (contentPanel instanceof ContentTextUpPanel) {
            String content = ((ContentTextUpPanel) contentPanel).getContent();
            question.setConteent(new ContentTextUP(content));
        }
        question.setGuiType(selectedGuiType);

// afișăm în consolă
        System.out.println("===== Question colectată =====");
        System.out.println(question);
    }

}

