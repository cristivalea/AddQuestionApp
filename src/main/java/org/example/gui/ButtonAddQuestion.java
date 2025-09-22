package org.example.gui;

import org.example.core.Dificulty;
import org.example.core.Question;

import javax.swing.*;

public class ButtonAddQuestion extends JButton implements Command {
    private Question question;
    private JComboBox<Dificulty> comboDificulty;
    private JComboBox<Short> comboDuration;
    private JTextField txtHint;

    public ButtonAddQuestion(Question question, JComboBox<Dificulty> comboDificulty, JComboBox<Short> comboDuration, JTextField txtHint) {
        super("Add Question");
        this.question = question;
        this.comboDificulty = comboDificulty;
        this.comboDuration = comboDuration;
        this.txtHint = txtHint;
    }

    public void execute(){
        Dificulty dificulty = null;
        short timeLength = 0;
        String hint = null;

        String dificultyString = this.comboDificulty.getSelectedItem().toString().trim();
        if (dificultyString.equals(Dificulty.SOFTQUESTION.toString())) {
            question.setDificulty(Dificulty.SOFTQUESTION);
        } else if (dificultyString.equals(Dificulty.MIDLELEVELQUESTION.toString())) {
            question.setDificulty(Dificulty.MIDLELEVELQUESTION);
        } else if (dificultyString.equals(Dificulty.HARDQUESTION.toString())) {
            question.setDificulty(Dificulty.HARDQUESTION);
        }

        String time = this.comboDuration.getSelectedItem().toString();
        Short shortTime = Short.parseShort(time);
        if (time == null) {
            System.err.println("Time lenght is null");
        }
        question.setTimeLength(shortTime);

        String textHint = this.txtHint.getText().trim();
        if(textHint == null){
            System.err.println("Hint is null");
        }
        question.setHint(textHint);

        System.out.println(question);
    }
}
