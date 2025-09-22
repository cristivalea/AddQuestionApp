package org.example.write_file;

import org.example.core.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseXMLQuestion {

    public Question readQuestion(Element e) throws ParserConfigurationException, SAXException, IOException {

        GuiType gui = null;
        Dificulty dif = null;
        String id = null;
        List<Objectiv> obj_pricipal = new ArrayList<>();
        List<Objectiv> obj_secundare = new ArrayList<>();
        short durata = 0;
        AbstractContent content_cod = null;
        List<AbstractResponse> list_abs_resp = new ArrayList<>();
        boolean[] ans_axu = null;
        String hint = null;

        Question question = new Question(id, dif, obj_pricipal, obj_secundare, durata ,gui, content_cod, list_abs_resp, ans_axu, hint);

        //extragere format
        String format = e.getElementsByTagName("format").item(0).getTextContent().trim();
        if(format == null){
            System.out.println("Nu exista valoare");
        }
        if(format.equalsIgnoreCase("text_up_cod_raspunsuri")) {
            gui = GuiType.TEXT_UP_COD;
            question.setGuiType(GuiType.TEXT_UP_COD);
        }
        else if(format.equalsIgnoreCase("text_up_raspunsuri")){
            gui = GuiType.TEXT_UP;
            question.setGuiType(GuiType.TEXT_UP);
        }

        //extragere tip
        String tip = e.getElementsByTagName("type").item(0).getTextContent().trim();
        if(tip == null){
            System.out.println("Nu exita valoare");
        }
        if(tip.equals("middlelevelquestion")){
            question.setDificulty(Dificulty.MIDLELEVELQUESTION);
            dif = Dificulty.MIDLELEVELQUESTION;
        }
        else if(tip.equals("softquestion")){
            question.setDificulty(Dificulty.SOFTQUESTION);
            dif = Dificulty.SOFTQUESTION;
        }
        else if(tip.equals("hardquestion")){
            question.setDificulty(Dificulty.HARDQUESTION);
            dif = Dificulty.HARDQUESTION;
        }

        //extragere id
        id = e.getElementsByTagName("id").item(0).getTextContent().trim();
        if(id == null){
            System.out.println("Nu exista valoare");
        }
        question.setIdQuestion(id);

        //extragere obiective pricipale
        String obiectiv_principal_str = e.getElementsByTagName("mainObjectiv").item(0).getTextContent().trim();
        if(obiectiv_principal_str.length() == 0){
            System.out.println("Nu exista valoarea extrasa");
        }
        List<Integer> obiectiv_principal = new ArrayList<>();
        String[] parts_p = obiectiv_principal_str.split("[,;\\s]+");
        for(String s : parts_p){
            int nr = Integer.parseInt(s);
            obiectiv_principal.add(nr);
        }
        if(obiectiv_principal.size() == 0){
            System.out.println("Nu exista elemente in lista obiectivelor pricipale");
        }

        List<Objectiv> obiective = Objectiv.readObjectiv("D:\\LICENTA\\objectives.xml");
        for(Objectiv obj : obiective){
            int id_obj = Integer.parseInt(obj.getId());
            if(obj.isMainObiectiv() && obiectiv_principal.contains(id_obj)){
                obj_pricipal.add(obj);
            }
        }
        question.setMainObjectives(obj_pricipal);

        //extragere subobiective
        String obiective_secundare_str = e.getElementsByTagName("secondarObjectiv").item(0).getTextContent().trim();
        if(obiective_secundare_str.length() == 0){
            System.err.println("Nu exista valoare extrasa");
        }
        List<Integer> obiective_secundare = new ArrayList<>();
        String[] parts = obiective_secundare_str.split("[.,;\\s]+");
        for(String s : parts){
            int nr = Integer.parseInt(s);
            obiective_secundare.add(nr);
        }
        if(obiective_secundare.size() == 0){
            System.out.println("Nu exista elemente in lista obiectivelor secundare");
        }
        for(Objectiv obj : obiective){
            if(obj.getListaSubobiective() != null) {
                for (Objectiv os : obj.getListaSubobiective()) {
                    int idOS = Integer.parseInt(os.getId());
                    if (obiective_secundare.contains(idOS)) {
                        //System.out.println(idOS);
                        obj_secundare.add(os);
                    }
                }
            }
        }
        question.setSecondaryObjectiv(obj_secundare);

        //extragere durata

        String raw = e.getElementsByTagName("estimatedSeconds").item(0).getTextContent();
        durata = Short.parseShort(raw);
        question.setTimeLength(durata);

        //extragere cerinta
        String cerinta = e.getElementsByTagName("textUP").item(0).getTextContent().trim();
        if(cerinta == null){
            System.out.println("Nu exista aceasta cerinta");
        }
        //System.out.println(cerinta);

        //extragere cod
        Element source_cod_el = null;
        NodeList sourceCode = e.getElementsByTagName("sourceCode");
        if(sourceCode.getLength() > 0){
            source_cod_el = (Element) sourceCode.item(0);
        }
        String source_cod_string = null;
        if(source_cod_el != null){
            source_cod_string = source_cod_el.getTextContent().trim();
        }
        else {
            source_cod_string = null;
        }
        if(source_cod_string != null && !source_cod_string.isEmpty()){
            content_cod = new ContentTextUPCod(cerinta, source_cod_string);
        }
        else {
            content_cod = new ContentTextUP(cerinta);
        }
        question.setConteent(content_cod);

        //extragere raspunsuri
        NodeList nl2 = e.getElementsByTagName("responses");
        Node n2 = nl2.item(0);
        //System.out.println(n2.getNodeName());
        if(n2.getNodeType() == Node.ELEMENT_NODE){
            Element e2 = (Element) n2;

            //extragere Lista Raspunsuri
            List<String> raspunsuri = new ArrayList<>();
            NodeList nl3 = e2.getElementsByTagName("response");
            int contor = 1;

            for(int i = 0; i < nl3.getLength(); i++){
                Node raspuns  = nl3.item(i);
                if(raspuns.getNodeType() == Node.ELEMENT_NODE){
                    Element e3 = (Element) raspuns;
                    //System.out.println(e3.getTextContent().trim());
                    raspunsuri.add(raspuns.getTextContent());
                    ResponseText rsp = new ResponseText(contor++, e3.getTextContent().trim());
                    list_abs_resp.add(rsp);
                }
            }
            question.setResponses(list_abs_resp);
        } // end if raspunsuri


        // extragere True / False

        NodeList nl4 = e.getElementsByTagName("answers");
        Node n_tf = nl4.item(0);
        //System.out.println(n_tf);
        if(n_tf.getNodeType() == Node.ELEMENT_NODE){
            Element e4 = (Element) n_tf;
            List<String> answers = new ArrayList<>();
            NodeList nl5 = e4.getElementsByTagName("answer");


            for(int i = 0; i < nl5.getLength(); i++){
                Node ans = nl5.item(i);
                if(ans.getNodeType() == Node.ELEMENT_NODE){
                    Element ans1 = (Element) ans;
                    //System.out.println(ans.getTextContent().trim());
                    answers.add(ans1.getTextContent().trim());
                }
            }
            ans_axu = new boolean[answers.size()];
            int cont_ans = 0;
            for(String s : answers){
                if(s.equalsIgnoreCase("TRUE")) {
                    ans_axu[cont_ans++] = true;
                }
                else if(s.equalsIgnoreCase("FALSE")){
                    ans_axu[cont_ans++] = false;
                }
            }
            question.setAnswers(ans_axu);
        }// end if answers

        //extragere hint
        hint = e.getElementsByTagName("hint").item(0).getTextContent().trim();
        if(hint == null){
            System.out.println("Nu exista hint");
        }
        question.setHint(hint);


        return question;
    } // end readQuestion

    public static Question parseFromFile(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(filePath);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            ParseXMLQuestion parser = new ParseXMLQuestion();
            return parser.readQuestion(root);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createElementWithText(Document doc, Element parent, String tag, String text) {
        Element elem = doc.createElement(tag);
        elem.appendChild(doc.createTextNode(text));
        parent.appendChild(elem);
    }

   public void writeToXML(Question question){
        try{

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("question");
            doc.appendChild(root);
            createElementWithText(doc, root, "format", question.getGuiType().getDenumire());
            createElementWithText(doc, root, "type", question.getDificulty().getDenumire());
            createElementWithText(doc, root, "id", question.getIdQuestion());
            String idMain = "";
            for(Objectiv op : question.getMainObjectives()){
                idMain += op.getId();
            }
            createElementWithText(doc, root, "mainObjectiv", idMain);
            String idSec = "";
            for(Objectiv os : question.getSecondaryObjectiv()){
                idSec += os.getId();
            }
            createElementWithText(doc, root, "secondarObjectiv", idSec);
            createElementWithText(doc, root, "estimatedSeconds", String.valueOf(question.getTimeLength()));
            AbstractContent content = question.getConteent();
            if(content instanceof ContentTextUP) {
                createElementWithText(doc, root, "textUP", content.getText());
                createElementWithText(doc, root, "sourceCode", "\n\t\t" +  null + "\n\t");
            }
            else if(content instanceof ContentTextUPCod ctc){
                createElementWithText(doc, root, "textUP", ctc.getText());
                createElementWithText(doc, root, "sourceCode", "\n\t\t" +  ctc.getCode() + "\n\t");
            }
            else if(content instanceof ContentTextUpImage cti){
                createElementWithText(doc, root, "textUP", cti.getText());
                createElementWithText(doc, root, "sourceCode", "\n\t\t" +  cti.getImagePath() + "\n\t");
            }
            else if(content instanceof ContentTextUpImageMidle ctim){
                createElementWithText(doc, root, "textUP", ctim.getText() + "\n" + ctim.getTextMidle());
                createElementWithText(doc, root, "sourceCode", "\n\t\t" +  ctim.getImagePath() + "\n\t");
            }
            else if(content instanceof ContentTextUpMidleCode ctmc){
                createElementWithText(doc, root, "textUP", ctmc.getText() + "\n" + ctmc.getTextMidle());
                createElementWithText(doc, root, "sourceCode", "\n\t\t" +  ctmc.getCode() + "\n\t");
            }

            Element responses = doc.createElement("responses");
            root.appendChild(responses);
            for (AbstractResponse r : question.getResponses()) {
                if(r instanceof ResponseText) {
                    createElementWithText(doc, responses, "response", r.getTxt());
                }
                else if(r instanceof ResponseCode rc){
                    createElementWithText(doc, responses, "response", rc.getTxt() + " " + rc.getCode());
                }
            }

            Element answers = doc.createElement("answers");
            root.appendChild(answers);

            for (boolean a : question.getAnswers()) {
                String value = a ? "TRUE" : "FALSE";
                createElementWithText(doc, answers, "answer", value);
            }

            createElementWithText(doc, root, "hint", question.getHint());

//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            DOMSource source = new DOMSource(doc);
//            StreamResult consoleResult = new StreamResult(System.out);
//            transformer.transform(source, consoleResult);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            String filePath = "D:\\LICENTA\\Intrebari_XML\\write_xml_java1.xml";
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("XML creat cu succes la: " + filePath);
        }catch (Exception e){
            System.err.println("Error write question in xml: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        Question question = parseFromFile("D:\\LICENTA\\Intrebari_XML\\mediu\\question_1.xml");
        System.out.println(question);
        ParseXMLQuestion parseXMLQuestion = new ParseXMLQuestion();
//        parseXMLQuestion.writeToXML(question);
    }
}
