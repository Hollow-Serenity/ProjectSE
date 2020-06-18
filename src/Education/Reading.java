package Education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Reading {
    private ArrayList<ReadingAssignment> readingAssignments = new ArrayList<>();
    private ReadingAssignment currentReadingAssignment = new ReadingAssignment();

    public Reading() {
        ReadingAssignment littleFoxStory = new LittleFoxStory();
        ReadingAssignment theSnailStory = new TheSnailStory();
        ReadingAssignment twoMiceStory = new TwoMiceStory();
        ReadingAssignment purpleMouseStory = new PurpleMouseStory();
        List<ReadingAssignment> readingAssignmentList = Arrays.asList(littleFoxStory, theSnailStory, twoMiceStory, purpleMouseStory);
        readingAssignments.addAll(readingAssignmentList);
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
