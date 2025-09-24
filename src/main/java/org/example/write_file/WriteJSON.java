package org.example.write_file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.core.*;
import org.example.gui.ContentTextUpCodeImgTextDownPanel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WriteJSON {
    ParseXMLQuestion parseXMLQuestion = new ParseXMLQuestion();
//    protected  Question question = parseXMLQuestion.parseFromFile("D:\\LICENTA\\Intrebari_XML\\write_xml_java.xml");

    public  String writeJSON(Question question){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("format", question.getGuiType());
            data.put("type", question.getDificulty());
            data.put("id", question.getIdQuestion());

            List<Map<String, String>> mainObjectivesList = new ArrayList<>();
            if (question.getMainObjectives() != null) {
                for (Objectiv obj : question.getMainObjectives()) {
                    Map<String, String> objMap = new LinkedHashMap<>();
                    objMap.put("id", obj.getId());
                    objMap.put("name", obj.getNume());
                    mainObjectivesList.add(objMap);
                }
            }
            data.put("mainObjectives", mainObjectivesList);

            List<Map<String, String>> secondaryObjective = new ArrayList<>();
            if(question.getSecondaryObjectiv() != null){
                for(Objectiv os : question.getSecondaryObjectiv()){
                    Map<String, String> obS = new LinkedHashMap<>();
                    obS.put("id", os.getId());
                    obS.put("name", os.getNume());
                    secondaryObjective.add(obS);
                }
            }
            data.put("secondaryObjectives", secondaryObjective);

            data.put("estimateSeconds", question.getTimeLength());

            Map<String, Object> dataContent = new LinkedHashMap<>();
            AbstractContent content = question.getConteent();

            if (content != null) {
                Map<String, Object> contentMap = new LinkedHashMap<>();
                if(content instanceof ContentTextUpCodeImgTextDown ctcit){
                    contentMap.put("textUP", ctcit.getText());
                    contentMap.put("sourceCode", ctcit.getCode());
                    contentMap.put("img", ctcit.getImgPath());
                    contentMap.put("textDown", ctcit.getTextDown());
                } else if(content instanceof ContentTextUpImageMidle ctid){
                    contentMap.put("textUP", ctid.getText());
                    contentMap.put("img", ctid.getImagePath());
                    contentMap.put("textDown", ctid.getTextMidle());
                } else if(content instanceof ContentTextUpMidleCode ctcd){
                    contentMap.put("textUP", ctcd.getText());
                    contentMap.put("sourceCode", ctcd.getCode());
                    contentMap.put("textDown", ctcd.getTextMidle());
                } else if(content instanceof ContentTextUPCod ctc){
                    contentMap.put("textUP", ctc.getText());
                    contentMap.put("sourceCode", ctc.getCode());
                } else if(content instanceof ContentTextUpImage cti){
                    contentMap.put("textUP", cti.getText());
                    contentMap.put("img", cti.getImagePath());
                } else if(content instanceof ContentTextUP){
                    contentMap.put("textUP", content.getText());
                }
                data.put("content", contentMap);
            }



            List<Map<String, String>> responses = new ArrayList<>();
            if(question.getResponses() != null) {
                for (AbstractResponse ar : question.getResponses()) {
                    Map<String, String> mapResp = new LinkedHashMap<>();
                    mapResp.put("respoonse", ar.getTxt());
                    responses.add(mapResp);
                }
            }
            data.put("responses", responses);

            List<Map<String, Boolean>> answers = new ArrayList<>();
            if(question.getAnswers() != null){
                for(boolean ans : question.getAnswers()){
                    Map<String, Boolean> ansMap = new LinkedHashMap<>();
                    ansMap.put("answer", ans);
                    answers.add(ansMap);
                }
            }
            data.put("answers", answers);

            data.put("hint", question.getHint());
            String jsonString = mapper.writeValueAsString(data);

//            System.out.println(jsonString);
            return String.format(jsonString);
        }catch (Exception e){
            System.err.println("Error write JSON");
            return null;
        }
    }

    public static void main(String[] args){
        ParseXMLQuestion parseXMLQuestion = new ParseXMLQuestion();
        Question question1 = parseXMLQuestion.parseFromFile("D:\\LICENTA\\Intrebari_XML\\write_xml_java1.xml");
        WriteJSON writeJSON = new WriteJSON();
        String string = writeJSON.writeJSON(question1);
        System.out.println(string);
    }
}
