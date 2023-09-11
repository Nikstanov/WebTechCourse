package task4;

import org.education.Lab1.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4Test {
    static ArrayList<Integer> primeNumbers; // Список для хранения простых чисел из массива

    @BeforeAll
    public static void setUp() {
        primeNumbers = new ArrayList<>();
    }

    @Test
    public void testPrintIndexOfPrimeNumbersWithPrimeNumbers() {
        int[] inputArray = new int[]{1111, 221312, 34312, 41233};
        List<Integer> res = Task4.findPrimeNums(inputArray);
        List<Integer> expected = Collections.singletonList(41233);
        Assertions.assertFalse(expected.size() != res.size());
        int ind = 0;
        for(Integer temp : res){
            if(ind < expected.size()){
                Assertions.assertEquals(expected.get(ind), temp);
                ind++;
            }
            else{
                Assertions.fail();
            }
        }
    }

    @Test
    public void testPrintIndexOfPrimeNumbersWithoutPrimeNumbers() {
        // Создаем массив без простых чисел
        int[] inputArray = new int[]{1110, 221310, 34310, 41230};
        List<Integer> res = Task4.findPrimeNums(inputArray);
        Assertions.assertEquals(0, res.size());
    }
}
