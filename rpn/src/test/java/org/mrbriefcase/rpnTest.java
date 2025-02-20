package org.mrbriefcase;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class rpnTest {
    @Test
    public void additionTest() {
        rpn rpn = new rpn();
        String[] args = {"3","4","+"};
        assertEquals("7", rpn.main(args));
    }
    @Test
    public void subtractionTest() {
        rpn rpn = new rpn();
        String[] args = {"3","4","-"};
        assertEquals("-1", rpn.main(args));
    }
    @Test
    public void multiplicationTest() {
        rpn rpn = new rpn();
        String[] args = {"3","4","*"};
        assertEquals("12", rpn.main(args));
    }

    @Test
    public void divisionTest() {
        rpn rpn = new rpn();
        String[] args = {"12","4","/"};
        assertEquals("3", rpn.main(args));
    }
}