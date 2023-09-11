package task2;

import org.education.Lab1.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    float expectedXTrue = 2;
    float expectedXFalse = 20;
    float expectedYTrue = -0.11f;
    float expectedYFalse = -1111;

    @Test
    public void test() {
        System.out.println("Test executed");
        Assertions.assertTrue(Task2.scopeCheck(expectedXTrue, expectedYTrue));
        Assertions.assertFalse(Task2.scopeCheck(expectedXFalse, expectedYFalse));

    }
}
