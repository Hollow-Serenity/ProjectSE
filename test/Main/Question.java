package Main;

import Main.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest {
    @Test
    void checkAnswerTest() {
        Question question = new Question("correct", "correct");
        assertTrue(question.checkAnswer("correct"));

        Question question1 = new Question("correct", "correct");
        assertFalse(question1.checkAnswer("incorrect"));
    }
}