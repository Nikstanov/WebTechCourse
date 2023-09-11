package task7;

import org.education.Lab1.task7.Task7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task7Test {

    @Test
    public void test7(){
        double[] temp = new double[]{3.2,9.1,11.9,5.3,7.5,13.1};
        Task7.sort(temp);
        Assertions.assertArrayEquals(new double[]{3.2,5.3,7.5,9.1,11.9,13.1}, temp);
    }

}
