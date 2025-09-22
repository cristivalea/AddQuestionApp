package org.example.gui;

import org.example.core.ContentTextUP;
import org.example.core.ContentTextUPCod;
import org.example.core.GuiType;
import org.example.core.Question;

import javax.swing.*;

public class ButtonNextScreen2 extends JButton implements Command {
    private SecondScreenFrame frame;
    private Question question;

    public ButtonNextScreen2(SecondScreenFrame frame, Question question) {
        super("Next");
        this.frame = frame;
        this.question = question;
    }

    public void execute(){
        JPanel contentPanel = frame.getContentPanel();
        GuiType selectedGuiType = frame.getSelectedGuiType();

        if(contentPanel instanceof ContentTextUpPanel panel){
            String content = ((ContentTextUpPanel) contentPanel).getContent();
            question.setConteent(new ContentTextUP(content));
            question.setResponses(panel.getResponses());
            question.setAnswers(panel.getAnswers());
        }
        if (contentPanel instanceof ContentTextUpCodePanel) {
            String content = ((ContentTextUpCodePanel) contentPanel).getContent();
            String code = ((ContentTextUpCodePanel) contentPanel).getCode();
            question.setConteent(new ContentTextUPCod(content, code));
        }

        question.setGuiType(selectedGuiType);

        System.out.println("===== Question colectată =====");
        System.out.println(question);
    }
}
