package com.example.lzy.review;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void abc(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.print(calendar);
    }
}