package com.mps.pocs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void verifyValidCase1() {
        assertTrue(Validator.isValid("[]{}"));
    }
    @Test
    public void verifyValidCase2() {
        assertTrue(Validator.isValid("{[()]}"));
    }
    @Test
    public void verifyValidCase3() {
        assertTrue(Validator.isValid("[{}()]"));
    }
    @Test
    public void verifyValidCase4() {
        assertTrue(Validator.isValid("({[]{}})"));
    }
    @Test
    public void verifyInvalidCase1() {
        assertFalse(Validator.isValid("[)"));
    }
    @Test
    public void verifyInvalidCase2() {
        assertFalse(Validator.isValid("({[(]})"));
    }
    @Test
    public void verifyInvalidCase3() {
        assertFalse(Validator.isValid("((((([]))))"));
    }
    @Test
    public void verifyInvalidCase4() {
        assertFalse(Validator.isValid("{()}}"));
    }
}
