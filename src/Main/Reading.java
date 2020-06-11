package Main;

import java.util.ArrayList;
import java.util.Random;

public class Reading {
    private ArrayList<ReadingAssignment> readingAssignments = new ArrayList<>();
    private ReadingAssignment currentReadingAssignment = new ReadingAssignment();

    public Reading() {
        littleFoxStory();
        theSnailStory();
        twoMiceStory();
        purpleMouseStory();
    }

    private void littleFoxStory() {
        ReadingAssignment readingAssignment = new ReadingAssignment();
        readingAssignment.setStory("A fox has been in my yard. He wants to play. \n" +
                "The fox gets on the log. Then he hides under the deck \n" +
                "He is fun.");
        readingAssignment.addQuestion(new Question("Where did the fox hide?", "Under the deck"));
        readingAssignment.addQuestion(new Question("What does the fox get on?", "The log"));
        readingAssignment.addQuestion(new Question("Is the fox fun?", "Yes"));
        readingAssignments.add(readingAssignment);
    }

    private void theSnailStory() {
        ReadingAssignment readingAssignment = new ReadingAssignment();
        readingAssignment.setStory("Gail the snail was very sad. She felt like she was too plain. \n" +
                "'I know how to fix this!' she said in her brain. \n" +
                "'I will stain my shell with paint! Gail did not want to wait. \n" +
                "She got a pall and started to paint. Oh no! It started to rain. \n" +
                "The paint washed off. Gail the snail was plain again!");
        readingAssignment.addQuestion(new Question("Why was Gail sad?", "She felt like she was too plain"));
        readingAssignment.addQuestion(new Question("Why did the paint wash of?", "It started to rain"));
        readingAssignment.addQuestion(new Question("How did Gail fix her problem?", "She stained her shell with paint"));
        readingAssignments.add(readingAssignment);
    }

    private void twoMiceStory() {
        ReadingAssignment readingAssignment = new ReadingAssignment();
        readingAssignment.setStory("Mike and Moe were two mice. They lived in a little green house \n" +
                "Mike and Moe saw some cheese. 'I want the cheese!' said Mike \n" +
                "'It's mine!' said Moe. Mike and Moe yelled and yelled. \n" +
                "'How about we split it?' said Moe. 'Yes!' said Mike. \n" +
                "They both had some cheese.");
        readingAssignment.addQuestion(new Question("What were the two mice called?", "Mike and Moe"));
        readingAssignment.addQuestion(new Question("What did the two mice both want?", "Cheese"));
        readingAssignment.addQuestion(new Question("Where did the two mice live?", "In a little green house"));
        readingAssignments.add(readingAssignment);
    }

    private void purpleMouseStory() {
        ReadingAssignment readingAssignment = new ReadingAssignment();
        readingAssignment.setStory("Poppy is a purple mouse, who lives in a big blue house. \n" +
                "Poppy likes to go on walks if the weather is good, \n" +
                "if not he stays at home and drinks some tea.");
        readingAssignment.addQuestion(new Question("What animal is Poppy?", "A mouse"));
        readingAssignment.addQuestion(new Question("Where does Poppy live?", "In a big blue house"));
        readingAssignment.addQuestion(new Question("What colour is Poppy?", "Purple"));
        readingAssignments.add(readingAssignment);
    }

    public ReadingAssignment getRandomReadingAssignment() {
        Random random = new Random();
        int randomNumber = random.nextInt(readingAssignments.size());
        currentReadingAssignment = readingAssignments.get(randomNumber);
        return currentReadingAssignment;
    }

    public ReadingAssignment getCurrentReadingAssignment() {
        return currentReadingAssignment;
    }
}
