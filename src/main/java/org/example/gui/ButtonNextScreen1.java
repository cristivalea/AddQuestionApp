package org.example.gui;

import org.example.core.Objectiv;
import org.example.core.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonNextScreen1 extends JButton implements Command{
    private JList listMainObjectives;
    private JList listSecondaryObjectives;
    private JTextField textId;

    public ButtonNextScreen1(JList listMainObjectives, JList listSecondaryObjectives, JTextField textId) {
        super("Next");
        this.listMainObjectives = listMainObjectives;
        this.listSecondaryObjectives = listSecondaryObjectives;
        this.textId = textId;
    }

    @Override
    public void execute() {
        // Preluăm obiectivele selectate
        List<Objectiv> mainObjectives = getSelectedMainObjectives();
        List<Objectiv> secondaryObjectives = getSelectedSecondaryObjectives();

        // Creăm obiectul Question cu ce avem până acum
        Question question = new Question(
                textId.getText(),
                null,
                mainObjectives,
                secondaryObjectives,
                (short)0,
                null,
                null,
                new ArrayList<>(),
                null,
                null
        );


        new SecondScreenFrame(question).setVisible(true);

        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private List<Objectiv> getSelectedMainObjectives() {
        DefaultListModel<JCheckBox> model = (DefaultListModel<JCheckBox>) listMainObjectives.getModel();
        List<Objectiv> selected = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            JCheckBox cb = model.getElementAt(i);
            if(cb.isSelected()) {
                String[] parts = cb.getText().split("\\.", 2);
                selected.add(new Objectiv(parts[1].trim(), parts[0].trim(), true, null));
            }
        }
        return selected;
    }

    private List<Objectiv> getSelectedSecondaryObjectives() {
        DefaultListModel<JCheckBox> model = (DefaultListModel<JCheckBox>) listSecondaryObjectives.getModel();
        List<Objectiv> selected = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            JCheckBox cb = model.getElementAt(i);
            if(cb.isSelected()) {
                String[] parts = cb.getText().split("\\.", 2);
                selected.add(new Objectiv(parts[1].trim(), parts[0].trim(), false, null));
            }
        }
        return selected;
    }
}
