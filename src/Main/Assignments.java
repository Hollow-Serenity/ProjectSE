package Main;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

interface Story {
    public HBox display();
}


public class Assignments {
    private ArrayList<Story> stories = new ArrayList<>();

    public static class PurpleMouseStory implements Story {
        private Reading reading = new Reading();

        private ChoiceQuestion goodWeatherActivities() {
            RadioButton walk = new RadioButton("Go on walks");
            RadioButton drinkTea = new RadioButton("Drink tea");
            RadioButton playOutside = new RadioButton("Play outside");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("What does Poppy like to do when the weather is good?");
            choiceQuestion.addChoice(walk, true);
            choiceQuestion.addChoice(drinkTea, false);
            choiceQuestion.addChoice(playOutside, false);
            return choiceQuestion;
        }

        private ChoiceQuestion badWeatherActivities() {
            RadioButton readABook = new RadioButton("Stay at home and read a book");
            RadioButton drinkTea = new RadioButton("Stay at home and drink some tea");
            RadioButton listenToMusic = new RadioButton("Stay at home and listen to music");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("What does Poppy like to do when the weather is bad?");
            choiceQuestion.addChoice(readABook, false);
            choiceQuestion.addChoice(drinkTea, true);
            choiceQuestion.addChoice(listenToMusic, false);
            return choiceQuestion;
        }

        public void assignment() {
            reading.setStory("Poppy is a purple mouse, who lives in a big blue house. \n" +
                    "Poppy likes to go on walks if the weather is good, \n" +
                    "if not he stays at home and drinks some tea.");

            Question question = new Question();
            question.setText("What is Poppy?");
            question.setAnswer("A mouse");

            Question question1 = new Question();
            question1.setText("Where does Poppy live?");
            question1.setAnswer("In a big blue house");

            Question question4 = new Question();
            question4.setText("What colour is the mouse?");
            question4.setAnswer("Purple");

            reading.addQuestion(question);
            reading.addQuestion(question1);
            reading.addQuestion(goodWeatherActivities());
            reading.addQuestion(badWeatherActivities());
            reading.addQuestion(question4);
        }

        public HBox display() {
            assignment();
            return DisplayingStories.display(reading);
        }
    }

    public static class TwoMiceStory implements Story {
        private Reading reading = new Reading();

        private ChoiceQuestion problem() {
            RadioButton shared = new RadioButton("They shared");
            RadioButton fought = new RadioButton("They fought");
            RadioButton throwAway = new RadioButton("The threw the cheese away");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("How did the two mice solve their problem?");
            choiceQuestion.addChoice(shared, true);
            choiceQuestion.addChoice(fought, false);
            choiceQuestion.addChoice(throwAway, false);
            return choiceQuestion;
        }

        private ChoiceQuestion location() {
            RadioButton blue = new RadioButton("They lived in a little blue house");
            RadioButton big = new RadioButton("They lived in a big green house");
            RadioButton littleGreen = new RadioButton("They lived in a little green house");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("Where did the two mice live?");
            choiceQuestion.addChoice(blue, false);
            choiceQuestion.addChoice(big, false);
            choiceQuestion.addChoice(littleGreen, true);
            return choiceQuestion;
        }

        public void assignment() {
            reading.setStory("Mike and Moe were two mice. They lived in a little green house \n" +
                    "Mike and Moe saw some cheese. 'I want the cheese!' said Mike \n" +
                    "'It's mine!' said Moe. Mike and Moe yelled and yelled. \n" +
                    "'How about we split it?' said Moe. 'Yes!' said Mike. \n" +
                    "They both had some cheese.");

            Question question1 = new Question();
            question1.setText("What did the two mice both want?");
            question1.setAnswer("Cheese");

            Question question3 = new Question();
            question3.setText("What were the two mice called?");
            question3.setAnswer("Mike and Moe");

            reading.addQuestion(location());
            reading.addQuestion(question1);
            reading.addQuestion(problem());
            reading.addQuestion(question3);
        }

        public HBox display() {
            assignment();
            return DisplayingStories.display(reading);
        }
    }

    public static class LittleFoxStory implements Story {
        private Reading reading = new Reading();

        private ChoiceQuestion isTheFoxFun() {
            RadioButton yes = new RadioButton("yes");
            RadioButton no = new RadioButton("no");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("Is the fox fun?");
            choiceQuestion.addChoice(yes, true);
            choiceQuestion.addChoice(no, false);
            return choiceQuestion;
        }

        public void assignment() {
            reading.setStory("A fox has been in my yard. He wants to play. \n" +
                    "The fox gets on the log. Then he hides under the deck \n" +
                    "He is fun.");

            Question question = new Question();
            question.setText("Where does the fox hide?");
            question.setAnswer("Under the deck");

            Question question1 = new Question();
            question1.setText("What does the fox want to do?");
            question1.setAnswer("Play");

            Question question3 = new Question();
            question3.setText("What did the fox get on?");
            question3.setAnswer("The log");

            reading.addQuestion(question);
            reading.addQuestion(question1);
            reading.addQuestion(isTheFoxFun());
            reading.addQuestion(question3);
        }

        public HBox display() {
            assignment();
            return DisplayingStories.display(reading);
        }
    }

    public static class TheSnailStory implements Story {
        private Reading reading = new Reading();

        private ChoiceQuestion whyWasGailSad() {
            RadioButton broken = new RadioButton("Her shell broke");
            RadioButton plain = new RadioButton("She felt like she was too plain");
            RadioButton bullied = new RadioButton("She was being bullied by the other snails");
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setText("Why was Gail sad?");
            choiceQuestion.addChoice(broken, false);
            choiceQuestion.addChoice(plain, true);
            choiceQuestion.addChoice(bullied, false);
            return choiceQuestion;
        }

        public void assignment() {
            reading.setStory("Gail the snail was very sad. She felt like she was too plain. \n" +
                    "'I know how to fix this!' she said in her brain. \n" +
                    "'I will stain my shell with paint! Gail did not want to wait. \n" +
                    "She got a pall and started to paint. Oh no! It started to rain. \n" +
                    "The paint washed off. Gail the snail was plain again!");

            Question question = new Question();
            question.setText("What did Gail do to her shell?");
            question.setAnswer("She stained her shell with paint");

            Question question2 = new Question();
            question2.setText("Did Gail want to wait?");
            question2.setAnswer("No");

            Question question3 = new Question();
            question3.setText("Why did the paint wash off at the end?");
            question3.setAnswer("It started to rain");

            reading.addQuestion(question);
            reading.addQuestion(whyWasGailSad());
            reading.addQuestion(question2);
            reading.addQuestion(question3);
        }

        public HBox display() {
            assignment();
            return DisplayingStories.display(reading);
        }
    }

    public ArrayList<Story> allStories() {
        Story purpleMouseStory = new PurpleMouseStory();
        Story twoMiceStory = new TwoMiceStory();
        Story littleFoxStory = new LittleFoxStory();
        Story theSnailStory = new TheSnailStory();

        stories.add(purpleMouseStory);
        stories.add(twoMiceStory);
        stories.add(littleFoxStory);
        stories.add(theSnailStory);

        return stories;
    }
}