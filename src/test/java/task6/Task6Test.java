package task6;

import org.education.Lab1.task6.Task6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Task6Test {

    @Test
    public void test6(){
        double[][] testArr = new double[][] {{1.0,2.0,3.0,4.0,5.0}, {2.0,3.0,4.0,5.0,1.0}, {3.0,4.0,5.0,1.0,2.0}, {4.0,5.0,1.0,2.0,3.0}, {5.0,1.0,2.0,3.0,4.0}};
        Assertions.assertTrue(Arrays.deepEquals(testArr, Task6.makeMatrix(new double[]{1.0, 2.0, 3.0, 4.0, 5.0})));
    }

}
