package Main;

import Education.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    @Test
    void checkAnswerTest() {
        Question question = new Question("correct", "correct");
        assertTrue(question.checkAnswer("correct"));

        Question question1 = new Question("correct", "correct");
        assertFalse(question1.checkAnswer("incorrect"));
    }

    @Test
    void checkAnswerTestFail() {
        Question question = new Question("correct", "correct");
        assertTrue(question.checkAnswer("incorrect"));

        Question question1 = new Question("correct", "correct");
        assertFalse(question1.checkAnswer("correct"));
    }
}