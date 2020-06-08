package Main;

import java.util.ArrayList;

public class Reading {
    private String story;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<>();

    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addChoiceQuestion(ChoiceQuestion choiceQuestion) {
        choiceQuestions.add(choiceQuestion);
    }
    public ArrayList<ChoiceQuestion> getChoiceQuestions() {
        return choiceQuestions;
    }
}
