package org.example.gui;

import org.example.Main;
import org.example.core.Objectiv;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ButtonGenerateQuestionId extends JButton implements Command {
    private JList listMainObjectives;
    private JList listSecondaryObjective;
    private JTextField textId;

    public ButtonGenerateQuestionId(JList listMainObjectives, JList listSecondaryObjective, JTextField textId) {
        super("Generate Id Question");
        this.listMainObjectives = listMainObjectives;
        this.listSecondaryObjective = listSecondaryObjective;
        this.textId = textId;
    }

    public void execute(){

        DefaultListModel<JCheckBox> model = (DefaultListModel<JCheckBox>) listMainObjectives.getModel();
        List<String> selectedTexts = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            JCheckBox cb = model.getElementAt(i);
            if (cb.isSelected()) {
                selectedTexts.add(cb.getText());
            }
        }

        List<Objectiv> mainObjectivesList = new ArrayList<>();
        for (String obP : selectedTexts) {
            String[] parts = obP.split("\\.", 2);
            String number = parts[0].trim();
            String objectiveText = parts[1].trim();
            Objectiv ob = new Objectiv(objectiveText, number, true, null);
            mainObjectivesList.add(ob);
        }


        DefaultListModel<JCheckBox> modelSub = (DefaultListModel<JCheckBox>) listSecondaryObjective.getModel();
        List<String> selectedSubOb = new ArrayList<>();
        for (int i = 0; i < modelSub.getSize(); i++) {
            JCheckBox cbs = modelSub.getElementAt(i);
            if (cbs.isSelected()) {
                selectedSubOb.add(cbs.getText());
            }
        }

        List<Objectiv> secondaryObjectivesList = new ArrayList<>();
        for (String obS : selectedSubOb) {
            String[] parts = obS.split("\\.", 2);
            String number = parts[0].trim();
            String objectiveText = parts[1].trim();
            Objectiv os = new Objectiv(objectiveText, number, false, null);
            secondaryObjectivesList.add(os);
        }

        String txtId = generateIdQuest(mainObjectivesList);
        textId.setText(txtId);
    }

    private String generateIdQuest(List<Objectiv> maibObjList){
        String id = Main.getInitiala();
        id += "Q";
        for(int i = 0; i < 3; i++){
            if(i < maibObjList.size()){
                id += maibObjList.get(i).getId();
            } else{
                id += "0";
            }
        }
        Random random = new Random();
        int uniqCode = random.nextInt(900) + 100;
        id += uniqCode;
        return id;
    }
}
