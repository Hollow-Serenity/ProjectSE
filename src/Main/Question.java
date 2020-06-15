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


class ReadingAssignment extends Question {
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

    public Boolean checkAnswers(String response1, String response2, String response3) {
        boolean isCorrect = false;
        if(questions.get(0).checkAnswer(response1) && questions.get(1).checkAnswer(response2) && questions.get(2).checkAnswer(response3)) {
            isCorrect = true;
        }
        return isCorrect;
    }
}


class LittleFoxStory extends ReadingAssignment {
    public LittleFoxStory() {
        setStory("A fox has been in my yard. He wants to play. \n" +
                "The fox gets on the log. Then he hides under the deck \n" +
                "He is fun.");
        addQuestion(new Question("Where did the fox hide?", "Under the deck"));
        addQuestion(new Question("What does the fox get on?", "The log"));
        addQuestion(new Question("Is the fox fun?", "Yes"));
    }
}

class TheSnailStory extends ReadingAssignment {
    public TheSnailStory() {
        setStory("Gail the snail was very sad. She felt like she was too plain. \n" +
                "'I know how to fix this!' she said in her brain. \n" +
                "'I will stain my shell with paint! Gail did not want to wait. \n" +
                "She got a pall and started to paint. Oh no! It started to rain. \n" +
                "The paint washed off. Gail the snail was plain again!");
        addQuestion(new Question("Why was Gail sad?", "She felt like she was too plain"));
        addQuestion(new Question("Why did the paint wash of?", "It started to rain"));
        addQuestion(new Question("How did Gail fix her problem?", "She stained her shell with paint"));
    }
}

class TwoMiceStory extends ReadingAssignment {
    public TwoMiceStory() {
        setStory("Mike and Moe were two mice. They lived in a little green house \n" +
                "Mike and Moe saw some cheese. 'I want the cheese!' said Mike \n" +
                "'It's mine!' said Moe. Mike and Moe yelled and yelled. \n" +
                "'How about we split it?' said Moe. 'Yes!' said Mike. \n" +
                "They both had some cheese.");
        addQuestion(new Question("What were the two mice called?", "Mike and Moe"));
        addQuestion(new Question("What did the two mice both want?", "Cheese"));
        addQuestion(new Question("Where did the two mice live?", "In a little green house"));
    }
}

class PurpleMouseStory extends ReadingAssignment {
    public PurpleMouseStory() {
        setStory("Poppy is a purple mouse, who lives in a big blue house. \n" +
                "Poppy likes to go on walks if the weather is good, \n" +
                "if not he stays at home and drinks some tea.");
        addQuestion(new Question("What animal is Poppy?", "A mouse"));
        addQuestion(new Question("Where does Poppy live?", "In a big blue house"));
        addQuestion(new Question("What colour is Poppy?", "Purple"));
    }
}