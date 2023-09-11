package task8;

import org.education.Lab1.task8.Task8;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task8Test {
    int[] arrayExpected = {0, 4, 7, 9, 10, 11};
    double[] array1 = new double[]{1, 2, 3, 4, 5, 6};
    double[] array2 = new double[]{0, 4, 6, 8, 9, 10};

    @Test
    void findIndexes() {
        System.out.println("TEST EXECUTED");
        int[] res = Task8.pushSequence(array1, array2);
        Assertions.assertArrayEquals(res, arrayExpected);
    }
}
