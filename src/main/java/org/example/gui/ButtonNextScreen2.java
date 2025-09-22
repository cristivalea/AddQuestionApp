package org.example.gui;

import org.example.core.*;

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
        else if (contentPanel instanceof ContentTextUpCodePanel panel) {
            String content = ((ContentTextUpCodePanel) contentPanel).getContent();
            String code = ((ContentTextUpCodePanel) contentPanel).getCode();
            question.setConteent(new ContentTextUPCod(content, code));
            question.setResponses(panel.getResponses());
            question.setAnswers(panel.getAnswers());
        }
        else if (contentPanel instanceof ContentTextUpImgPanel panel) {
            String content = ((ContentTextUpImgPanel) contentPanel).getContent();
            String img = ((ContentTextUpImgPanel) contentPanel).getSelectedImageFile();
            question.setConteent(new ContentTextUpImage(content, img));
            question.setResponses(panel.getResponses());
            question.setAnswers(panel.getAnswers());
        }
        else if(contentPanel instanceof ContentTextUpimgTextDownPanel panel){
            String content = ((ContentTextUpimgTextDownPanel) contentPanel).getContent();
            String img = ((ContentTextUpimgTextDownPanel) contentPanel).getSelectedImageFile();
            String textDown = ((ContentTextUpimgTextDownPanel) contentPanel).getTextDown();
            question.setConteent(new ContentTextUpImageMidle(content, img, textDown));
            question.setResponses(panel.getResponses());
            question.setAnswers(panel.getAnswers());
        }
        else if(contentPanel instanceof ContentTextUpCodetextDownPanel panel){
            String content = ((ContentTextUpCodetextDownPanel) contentPanel).getContent();
            String code = ((ContentTextUpCodetextDownPanel) contentPanel).getCode();
            String textDown = ((ContentTextUpCodetextDownPanel) contentPanel).getTextDown();
            question.setConteent(new ContentTextUpMidleCode(content, code, textDown));
            question.setResponses(panel.getResponses());
            question.setAnswers(panel.getAnswers());
        }

        question.setGuiType(selectedGuiType);

        new ThirdScreenFrame(question);
        frame.dispose();
    }
}
