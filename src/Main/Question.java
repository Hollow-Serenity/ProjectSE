package Main;

import java.util.ArrayList;

public class Question {
    private String text;
    private String answer;

    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }
    public Question() {
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public Boolean checkAnswer(String response) {
        return response.equals(answer);
    }

    @Override
    public String toString() {
        return text;
    }
}

class ReadingAssignment {
    private String story;
    private ArrayList<Question> questions = new  ArrayList<>();

    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
}