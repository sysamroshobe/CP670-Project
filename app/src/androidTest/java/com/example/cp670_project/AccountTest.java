package com.example.cp670_project;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

public class AccountTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testAddMeal() {
        // Arrange
        Account account = new Account();
        Meal meal = new Meal();
        meal.setName("breakfast");
        meal.setCaloriesIn(200);
        meal.setDate(new Date());

        // Act
        boolean returnCode = account.addMeal(meal);

        // Assert
        assertNotNull(account.getId());
        assertTrue(returnCode);
    }

    @Test
    public void testAddExercise() {
        // Arrange
        Account account = new Account();
        Exercise exercise = new Exercise();
        exercise.setName("Clean and Press");
        exercise.setDistance(100);
        exercise.setRepetitions(100);
        exercise.setWeight(100);
        exercise.setLengthOfTime(100);
        exercise.setCaloriesOut(200);
        exercise.setDate(new Date());

        // Act
        boolean returnCode = account.addExercise(exercise);

        // Assert
        assertNotNull(account.getId());
        assertTrue(returnCode);
        assertEquals(exercise.getDistance(), 100.0);
        assertEquals(exercise.getRepetitions(), 100);
        assertEquals(exercise.getWeight(), 100.0);
        assertEquals(exercise.getLengthOfTime(), 100.0);
        assertEquals(exercise.getCaloriesOut(), 200);
    }

    @Test
    public void testHasIdInitialized() {
        Account account = new Account();
        assertNotNull(account.getId());
    }

}