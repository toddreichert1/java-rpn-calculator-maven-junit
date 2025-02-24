package org.mrbriefcase;

import static org.junit.Assert.*;

import org.junit.*;

public class rpnTest {
    @Test
    public void noArgumentsTest() {
        System.out.println("+noArgumentsTest++{}+");
        rpn rpn = new rpn();
        String[] args = {};
        assertEquals("ERR01", rpn.main(args));
    }
    
    @Test
    public void additionTest() {
        System.out.println("+additionTest++{\"3\",\"4\",\"+\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","4","+"};
        assertEquals("7", rpn.main(args));
    }

    @Test
    public void additionAlphaTest() {
        System.out.println("+additionAlphaTest++{\"3\",\"4\",\"5\",\"add\",\"a\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","4","5","add","a"};
        assertEquals("12", rpn.main(args));
    }

    @Test
    public void subtractionTest() {
        System.out.println("+subtractionTest++{\"3\",\"4\",\"-\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","4","-"};
        assertEquals("-1", rpn.main(args));
    }

    @Test
    public void subtractionAlphaTest() {
        System.out.println("+subtractionAlphaTest++{\"5\",\"12\",\"3\",\"sub\",\"s\"}+");
        rpn rpn = new rpn();
        String[] args = {"5","12","3","sub","s"};
        assertEquals("-4", rpn.main(args));
    }

    @Test
    public void multiplicationTest() {
        System.out.println("+multiplicationTest++{\"3\",\"4\",\"*\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","4","*"};
        assertEquals("12", rpn.main(args));
    }

    @Test
    public void multiplicationAlphaTest() {
        System.out.println("+multiplicationAlphaTest++{\"3\",\"4\",\"2\",\"mul\",\"m\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","4","2","mul","m"};
        assertEquals("24", rpn.main(args));
    }

    @Test
    public void divisionTest() {
        System.out.println("+divisionTest++{\"12\",\"4\",\"/\"}+");
        rpn rpn = new rpn();
        String[] args = {"12","4","/"};
        assertEquals("3", rpn.main(args));
    }

    @Test
    public void divisionAlphaTest() {
        System.out.println("+divisionAlphaTest++{\"12\",\"8\",\"4\",\"div\",\"d\"}+");
        rpn rpn = new rpn();
        String[] args = {"12","8","4","div","d"};
        assertEquals("6", rpn.main(args));
    }

    @Test
    public void decimalPointTest() {
        System.out.println("+decimalPointTest++{\"3\",\"2\",\"d\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","2","d"};
        assertEquals("1.5", rpn.main(args));
    }

    @Test
    public void divisionByZeroTest() {
        System.out.println("+divisionByZeroTest++{\"4\",\"0\",\"d\"}+");
        rpn rpn = new rpn();
        String[] args = {"4","0","d"};
        assertEquals("ERR08", rpn.main(args));
    }

    @Test
    public void divisionByZeroTest2() {
        System.out.println("+divisionByZeroTest2++{\"4\",\"0.0\",\"d\"}+");
        rpn rpn = new rpn();
        String[] args = {"4","0.0","d"};
        assertEquals("ERR08", rpn.main(args));
    }

    @Test
    public void divisionByZeroTest3() {
        System.out.println("+divisionByZeroTest3++{\"4\",\"3\",\"3\",\"s\",\"d\"}+");
        rpn rpn = new rpn();
        String[] args = {"4","3","3","s","d"};
        assertEquals("ERR08", rpn.main(args));
    }
    
    @Test
    public void cosineTest() {
        System.out.println("+cosineTest++{\"0\",\"cos\"}+");
        rpn rpn = new rpn();
        String[] args = {"0","cos"};
        assertEquals("1", rpn.main(args));
    }
    
    @Test
    public void multipleOperationTest() {
        System.out.println("+multipleOperationTest++{\"3\",\"0\",\"cos\",\"+\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","0","cos","+"};
        assertEquals("4", rpn.main(args));
    }
    
    @Test
    public void noOperatorsPresentTest() {
        System.out.println("+noOperatorsPresentTest++{\"3\",\"0\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","0"};
        assertEquals("ERR02", rpn.main(args));
    }
    
    @Test
    public void invalidArgumentOrOperatorPresentTest() {
        System.out.println("+invalidArgumentOrOperatorPresentTest++{\"3\",\"0\",\"^\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","0","^"};
        assertEquals("ERR03", rpn.main(args));
    }
    
    @Test
    public void notEnoughOperandsToOperatorOnTest() {
        System.out.println("+notEnoughOperandsToOperatorOnTest++{\"3\",\"+\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","+"};
        assertEquals("ERR04", rpn.main(args));
    }
    
    @Test
    public void invalidArgumentOrderTest() {
        System.out.println("+invalidArgumentOrderTest++{\"3\",\"3\",\"+\",\"3\",\"+\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","3","+","3","+"};
        assertEquals("ERR05", rpn.main(args));
    }

    @Test
    public void notEnoughOperandsAndOrOperatorsTest() {
        System.out.println("+notEnoughOperandsAndOrOperatorsTest++{\"cos\"}+");
        rpn rpn = new rpn();
        String[] args = {"cos"};
        assertEquals("ERR06", rpn.main(args));
    }

    @Test
    public void tooManyOperandsTest() {
        System.out.println("+tooManyOperandsTest++{\"3\",\"3\",\"3\",\"+\"}+");
        rpn rpn = new rpn();
        String[] args = {"3","3","3","+"};
        assertEquals("ERR07", rpn.main(args));
    }
}
