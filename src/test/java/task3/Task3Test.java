package task3;

import org.education.Lab1.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Task3Test {

    List<double[]> expected = List.of(
            new double[]{35.0, 0.473814720414451},
            new double[]{35.7, 2.190178409401024},
            new double[]{36.400000000000006, -3.5897299081920964},
            new double[]{37.10000000000001, -0.6828337470600633}
    );
    double inputA = 35.0;
    double inputB = 37.7;
    double inputH = 0.7;

    @Test
    void test() {
        System.out.println("CALCULATION EXECUTED");
        int ind = 0;
        for(double[] arr : Task3.calculate(inputA, inputB, inputH)){
            if(ind < expected.size()){
                Assertions.assertArrayEquals(expected.get(ind), arr);
                ind++;
            }
            else{
                Assertions.fail();
            }
        }
    }
}
