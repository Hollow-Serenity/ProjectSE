package Main;

import Education.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculations {

    @Test
    public void TestPlus(){
        assertEquals(5, Question.calculateMathExpression(4,1,'+'));
        assertEquals(1728,Question.calculateMathExpression(657,1071,'+'));
    }

    @Test
    public void WrongTestPlus(){
        assertEquals(14, Question.calculateMathExpression(12,6,'+'));
        assertEquals(12228,Question.calculateMathExpression(657,1071,'+'));
    }
    @Test
    public void TestMinus(){
        assertEquals(2,Question.calculateMathExpression(9,7,'-'));
        assertEquals(9,Question.calculateMathExpression(100,91,'-'));
    }
    @Test
    public void WrongTestMinus(){
        assertEquals(700,Question.calculateMathExpression(856,5,'-'));
        assertEquals(35124,Question.calculateMathExpression(154,1,'-'));
    }

    @Test
    public void TestDivision(){
        assertEquals(2,Question.calculateMathExpression(8,4,'/'));
        assertEquals(18,Question.calculateMathExpression(90,5,'/'));
    }

    @Test
    public void WrongTestDivision(){
        assertEquals(9,Question.calculateMathExpression(180,45,'/'));
        assertEquals(6,Question.calculateMathExpression(270,5,'/'));
    }

    @Test
    public void TestMultiplication(){
        assertEquals(80,Question.calculateMathExpression(10,8,'x'));
        assertEquals(36,Question.calculateMathExpression(12,3,'x'));
    }

    @Test
    public void WrongTestMultiplication(){
        assertEquals(64,Question.calculateMathExpression(9,8,'x'));
        assertEquals(21,Question.calculateMathExpression(1322,56,'x'));
    }
}
