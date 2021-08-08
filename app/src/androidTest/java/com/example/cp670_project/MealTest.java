package com.example.cp670_project;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class MealTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testHasIdInitialized() {
        Meal meal = new Meal();
        assertNotNull(meal.getId());
    }
}