package org.example.gui;

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
    private ButtonNextScreen2 nextButton;
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
        this.mainPanel.add(this.panelType);

        this.nextButton = new ButtonNextScreen2(this, this.question);
        this.nextButton.addActionListener(this);
        this.mainPanel.add(this.nextButton);

        this.add(this.mainPanel);

        this.setVisible(true);
        this.pack();
        this.setSize(1300, 800);
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

        switch (gui.getId()) {
            case 1:
            {
                contentPanel = new ContentTextUpCodePanel();
                break;
            }
            case 2:
            {
                contentPanel = new ContentTextUpPanel();
                break;
            }
            case 3:
            {
                contentPanel = new ContentTextUpCodetextDownPanel();
                break;
            }
            case 4:
            {
                contentPanel = new ContentTextUpImgPanel();
                break;
            }
            case 5:
            {
                contentPanel = new ContentTextUpimgTextDownPanel();
                break;
            }
        }

        mainPanel.add(contentPanel, mainPanel.getComponentCount() - 1);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

//    private void printQuestionInfo() {
//        if (contentPanel instanceof ContentTextUpCodePanel) {
//            String content = ((ContentTextUpCodePanel) contentPanel).getContent();
//            String code = ((ContentTextUpCodePanel) contentPanel).getCode();
//            question.setConteent(new ContentTextUPCod(content, code));
//        }
//        question.setGuiType(selectedGuiType);
//
//        System.out.println("===== Question colectată =====");
//        System.out.println(question);
//    }
//
    public JPanel getContentPanel() {
        return contentPanel;
    }


}

