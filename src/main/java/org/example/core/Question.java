package org.example.core;

import java.util.Arrays;
import java.util.List;

public class Question {

    protected String idQuestion;
    protected Dificulty dificulty;
    protected List<Objectiv> mainObjectives;
    protected  List<Objectiv> secondaryObjectiv;
    protected short timeLength;
    protected GuiType guiType;
    protected AbstractContent conteent;
    protected List<AbstractResponse> responses;
    protected boolean[] answers;
    protected String hint;
    protected boolean partialScore;

    public Question(String idQuestion, Dificulty dificulty, List<Objectiv> mainObjectives, List<Objectiv> secondaryObjectiv, short timeLength,  GuiType guiType, AbstractContent conteent, List<AbstractResponse> responses, boolean[] answers, String hint) {
        this.idQuestion = idQuestion;
        this.dificulty = dificulty;
        this.mainObjectives = mainObjectives;
        this.secondaryObjectiv = secondaryObjectiv;
        this.timeLength = timeLength;
        this.guiType = guiType;
        this.conteent = conteent;
        this.responses = responses;
        this.answers = answers;
        this.hint = hint;
        setPartialScore(false);
    }

    public Question() {

    }

    public void setPartialScore(boolean score){
        this.partialScore = score;
    }

    public boolean getPartialScore(){
        return this.partialScore;
    }

    public  String getIdQuestion() {
        return this.idQuestion;
    }

    public  void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Dificulty getDificulty() {
        return this.dificulty;
    }

    public void setDificulty(Dificulty dificulty) {
        this.dificulty = dificulty;
    }

    public List<Objectiv> getMainObjectives() {
        return this.mainObjectives;
    }

    public void setMainObjectives(List<Objectiv> mainObjectives) {
        this.mainObjectives = mainObjectives;
    }

    public List<Objectiv> getSecondaryObjectiv() {
        return this.secondaryObjectiv;
    }

    public void setSecondaryObjectiv(List<Objectiv> secondaryObjectiv) {
        this.secondaryObjectiv = secondaryObjectiv;
    }

    public short getTimeLength() {
        return this.timeLength;
    }

    public void setTimeLength(short timeLength) {
        this.timeLength = timeLength;
    }

    public GuiType getGuiType() {
        return this.guiType;
    }

    public void setGuiType(GuiType guiType) {
        this.guiType = guiType;
    }

    public AbstractContent getConteent() {
        return this.conteent;
    }

    public void setConteent(AbstractContent conteent) {
        this.conteent = conteent;
    }

    public List<AbstractResponse> getResponses() {
        return this.responses;
    }

    public void setResponses(List<AbstractResponse> responses) {
        this.responses = responses;
    }

    public boolean[ ]isAnswers() {
        return this.answers;
    }

    public  void setAnswers(boolean[] answers) {
        this.answers = answers;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "Question{" +
                "\nidQuestion='" + idQuestion + '\'' +
                "\ndificulty=" + dificulty +
                "\nmainObjectives=" + mainObjectives +
                "\nsecondaryObjectiv=" + secondaryObjectiv +
                "\ntimeLength=" + timeLength +
                "\nguiType=" + guiType +
                "\nconteent=" + conteent +
                "\nresponses=" + responses +
                "\nanswers=" + Arrays.toString(answers) +
                "\nhint='" + hint + '\'' +
                "\nPartial Score= " + partialScore +
                '}';
    }

    public void displayQuestion(){
        String result = null;
        if(this.idQuestion == null){
            System.err.println("Intrebare nu are id");
        }
        result = "Serial number: " + this.idQuestion + "\n";
        if(this.conteent == null){
            System.err.println("Intrebarea nu are content");
        }
        result += this.conteent + "\n";
        if(this.responses.size() == 0){
            System.err.println("Intrebarea nu are raspunsuri");
        }
        String aux_response = "";
        for(AbstractResponse absr : this.responses){
            if(absr != null) {
                aux_response += absr.toString() + " ";
            }
        }
        result += aux_response;
        System.out.println(result);

    }

    public int[] transformToInt(String string) {
        int[] array = new int[string.length()];
        for(int i = 0; i < string.length(); i++){
            array[i] = string.charAt(i) - 'a' + 1;
        }
        return array;
    }

    public boolean evaluate(String resp){
        boolean result = false;
        int nrCorecte = 0;
        for (boolean b : this.answers){
            if(b){
                nrCorecte++;
            }
        }
        if(resp.length() == 1){
            int index = resp.toLowerCase().charAt(0) - 97;
            if(index >= 0 && index < this.answers.length && this.answers[index]){
                result = true;
            }
        }
        else if(resp.length() > 1){
            boolean corect = true;
            int[] arr = transformToInt(resp);
            if(arr.length != nrCorecte){
                return false;
            }
            for(int j = 0; j < arr.length; j++){
                int index = arr[j] - 1;
                if(index < 0 || index >= this.answers.length || !this.answers[index]){
                    corect = false;
                    break;
                }
            }
            result = corect; 
        }
        return result;
    }

    public boolean[] getAnswers() {
        return answers;
    }
} // end class
