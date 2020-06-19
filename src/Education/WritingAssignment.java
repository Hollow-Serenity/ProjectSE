package Education;
import java.util.ArrayList;
import java.util.Random;

public class WritingAssignment {
    private ArrayList<Question> questions = new ArrayList<>();
    private Question currentQuestion;

    public WritingAssignment() {
        questions.add(new Question("What is the opposite of up?", "down"));
        questions.add(new Question("What is the opposite of down?", "up"));
        questions.add(new Question("What is the opposite of empty?", "full"));
        questions.add(new Question("What is the opposite of full?", "empty"));
        questions.add(new Question("What is the opposite of against?", "for"));
        questions.add(new Question("What is the opposite of alike?", "different"));
        questions.add(new Question("What is the opposite of antonym?", "synonym"));
        questions.add(new Question("acceptable or acceptible", "acceptable"));
        questions.add(new Question("aquit or acquit", "acquit"));
        questions.add(new Question("aggression or agression", "aggression"));
        questions.add(new Question("almost or allmost", "almost"));
        questions.add(new Question("anually or annually", "annually"));
        questions.add(new Question("arctic or artic", "arctic"));
        questions.add(new Question("arguement or argument", "argument"));
        questions.add(new Question("awfull or awful", "awful"));
        questions.add(new Question("chief or cheif", "chief"));
    }

    private void setCurrentQuestion(Question question) {
        currentQuestion = question;
    }
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        int randomNumber = random.nextInt(questions.size());

        Question question = questions.get(randomNumber);
        setCurrentQuestion(question);

        return question;
    }
}
