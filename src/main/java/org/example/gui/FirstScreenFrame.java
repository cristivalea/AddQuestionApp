package org.example.gui;

import org.example.core.Configuration;
import org.example.core.Objectiv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FirstScreenFrame extends JFrame implements ActionListener {
    //main objectives
    private JPanel panelMainObjectives;
    private JList listMainObjectives;
    private JLabel labelMainObjective;

    //secondary objective;
    private JPanel panelSecondaryObjectives;
    private JList listSecondaryObjective;
    private JLabel labelSecondaryObjective;

    //id
    private JPanel panelId;
    private JTextField txtId;
    private JLabel labelId;

    ButtonGenerateQuestionId buttongenerateQuestionId;
    ButtonNextScreen1 buttonNextScreen1;

    private JPanel mainPanel;

    public FirstScreenFrame() throws Exception{
        this.mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(boxLayout);

        // main objectives
        this.panelMainObjectives = new JPanel();
        this.labelMainObjective = new JLabel("Main Objectives");
        List<Objectiv> obiective = Objectiv.readObjectiv(Configuration.objectivesJavaXML);

        DefaultListModel<JCheckBox> modelMainObjectives = new DefaultListModel<>();
        for (Objectiv o : obiective) {
            modelMainObjectives.addElement(new JCheckBox(o.getId() + ". " + o.getNume()));
        }

        this.listMainObjectives = new JList<>(modelMainObjectives);
        this.listMainObjectives.setCellRenderer(new ListCellRenderer<JCheckBox>() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<? extends JCheckBox> list,
                    JCheckBox value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                return value;
            }
        });

        DefaultListModel<JCheckBox> modelSubObjectives = new DefaultListModel<>();
        this.listSecondaryObjective = new JList<>(modelSubObjectives);
        this.listSecondaryObjective.setCellRenderer(new ListCellRenderer<JCheckBox>() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<? extends JCheckBox> list,
                    JCheckBox value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                return value;
            }
        });

        this.listMainObjectives.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = listMainObjectives.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    JCheckBox checkbox = modelMainObjectives.getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    listMainObjectives.repaint(listMainObjectives.getCellBounds(index, index));

                    modelSubObjectives.clear();
                    for (int i = 0; i < modelMainObjectives.getSize(); i++) {
                        JCheckBox cb = modelMainObjectives.getElementAt(i);
                        if (cb.isSelected()) {
                            Objectiv mainObj = obiective.get(i);
                            if (mainObj.getListaSubobiective() != null) {
                                for (Objectiv sub : mainObj.getListaSubobiective()) {
                                    modelSubObjectives.addElement(new JCheckBox(sub.getId() + ". " + sub.getNume()));
                                }
                            }
                        }
                    }
                }
            }
        });

        this.listSecondaryObjective.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = listSecondaryObjective.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    JCheckBox checkbox = modelSubObjectives.getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    listSecondaryObjective.repaint(listSecondaryObjective.getCellBounds(index, index));
                }
            }
        });

        this.panelMainObjectives.add(this.labelMainObjective);
        JScrollPane scrollMain = new JScrollPane(this.listMainObjectives);
        this.panelMainObjectives.add(scrollMain);
        scrollMain.setPreferredSize(new java.awt.Dimension(600, 250)); // dimensiune mai mare
        this.panelMainObjectives.add(this.labelMainObjective, BorderLayout.NORTH);
        this.panelMainObjectives.add(scrollMain, BorderLayout.CENTER);
        this.mainPanel.add(this.panelMainObjectives);


        this.panelSecondaryObjectives = new JPanel();
        this.labelSecondaryObjective = new JLabel("Secondary Objectives");
        this.panelSecondaryObjectives.add(this.labelSecondaryObjective);
        JScrollPane scrollSecondary = new JScrollPane(this.listSecondaryObjective);
        this.panelSecondaryObjectives.add(scrollSecondary);
        scrollSecondary.setPreferredSize(new java.awt.Dimension(900, 250));
        this.panelSecondaryObjectives.add(scrollSecondary, BorderLayout.CENTER);
        this.mainPanel.add(this.panelSecondaryObjectives);

        this.panelId = new JPanel();
        this.labelId = new JLabel("Generated id question");
        this.panelId.add(this.labelId);
        this.txtId = new JTextField(50);
        this.txtId.setEditable(false);
        this.panelId.add(this.txtId);
        this.buttongenerateQuestionId = new ButtonGenerateQuestionId(this.listMainObjectives, this.listSecondaryObjective, this.txtId);
        this.buttongenerateQuestionId.addActionListener(this);
        this.panelId.add(this.buttongenerateQuestionId);
        this.mainPanel.add(this.panelId);

        this.buttonNextScreen1 = new ButtonNextScreen1(this.listMainObjectives, this.listSecondaryObjective, this.txtId);
        this.buttonNextScreen1.addActionListener(this);
        this.mainPanel.add(this.buttonNextScreen1);

        this.add(this.mainPanel);
        this.setVisible(true);
        this.pack();
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        ((Command)actionEvent.getSource()).execute();
    }

    public static void main(String[] args) throws Exception{
        FirstScreenFrame firstScreenFrame = new FirstScreenFrame();
    }
}
