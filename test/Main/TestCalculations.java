package Main;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculations {

    @Test
    public void TestPlus(){
        assertEquals(5,Question.calculateMathExpression(4,1,'+'));
        assertEquals(1728,Question.calculateMathExpression(657,1071,'+'));
    }

    @Test
    public void TestMinus(){
        assertEquals(2,Question.calculateMathExpression(9,7,'-'));
        assertEquals(9,Question.calculateMathExpression(100,91,'-'));
    }

    @Test
    public void TestDivision(){
        assertEquals(2,Question.calculateMathExpression(8,4,'/'));
        assertEquals(18,Question.calculateMathExpression(90,5,'/'));
    }

    @Test
    public void TestMultiplication(){
        assertEquals(80,Question.calculateMathExpression(10,8,'x'));
        assertEquals(36,Question.calculateMathExpression(12,3,'x'));
    }
}
