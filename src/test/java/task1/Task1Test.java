package task1;

import org.education.Lab1.task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Task1Test {

    static Task1 task1;

    @BeforeAll
    static public void init(){
        task1 = new Task1();
    }

    @Test
    public void testA(){
        double testX = 10;
        double testY = 20;
        double expectedResult = 10.16469;

        System.out.println("Calculation Test");
        Assertions.assertEquals(expectedResult, task1.calculate(testX, testY), 0.01);
    }
}
