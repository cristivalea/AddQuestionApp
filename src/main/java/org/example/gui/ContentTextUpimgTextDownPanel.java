package org.example.gui;

import org.example.core.AbstractResponse;
import org.example.core.ResponseText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContentTextUpimgTextDownPanel extends JPanel implements ActionListener {
    private JPanel panelText;
    private JLabel labelText;
    private JTextArea txtText;
    private JPanel panelResponses;

    private JPanel panelImage;
    private JLabel labelImage;
    private JLabel imagePreview;
    private JButton btnLoadImage;
    private File selectedImageFile;
    private JPanel mainPanel;
    private JPanel answersContainer;
    private JPanel textDownPanel;
    private JLabel labeltextDown;
    private JTextArea textDownArea;

    public ContentTextUpimgTextDownPanel(){
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        this.panelText = new JPanel();
        this.labelText = new JLabel("Text");
        this.panelText.add(this.labelText);
        this.txtText = new JTextArea(5, 40);
        this.panelText.add(this.txtText);
        this.mainPanel.add(this.panelText);

        this.panelImage = new JPanel();
        this.labelImage = new JLabel("Imagine");
        this.panelImage.add(this.labelImage);

        this.imagePreview = new JLabel();
        this.imagePreview.setPreferredSize(new Dimension(400, 300));
        this.imagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        this.btnLoadImage = new JButton("Alege imagine...");
        this.btnLoadImage.addActionListener(this::onChooseImage);

        this.panelImage.add(this.imagePreview);
        this.panelImage.add(this.btnLoadImage);

        this.mainPanel.add(this.panelImage);

        this.textDownPanel = new JPanel();
        this.labeltextDown = new JLabel("Text");
        this.textDownPanel.add(this.labeltextDown);
        this.textDownArea = new JTextArea(3, 50);
        this.textDownPanel.add(this.textDownArea);
        this.mainPanel.add(this.textDownPanel);

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
    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }

    private void onChooseImage(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imagini", "jpg", "png", "gif"
        ));

        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedImageFile.getAbsolutePath());

            // redimensionăm imaginea ca să încapă în preview
            Image scaled = icon.getImage().getScaledInstance(
                    imagePreview.getWidth(), imagePreview.getHeight(), Image.SCALE_SMOOTH
            );
            imagePreview.setIcon(new ImageIcon(scaled));
        }
    }

    public String getContent() {
        return txtText.getText();
    }

    public String getSelectedImageFile() {
        return selectedImageFile.getPath();
    }

    public String getTextDown(){
        return this.textDownArea.getText();
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
