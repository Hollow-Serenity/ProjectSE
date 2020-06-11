package Main;

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