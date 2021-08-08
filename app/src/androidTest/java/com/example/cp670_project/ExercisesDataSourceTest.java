package com.example.cp670_project;

import android.content.Context;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import  androidx.test.platform.app.InstrumentationRegistry;

import java.util.List;

// Data Access Layer tests
public class ExercisesDataSourceTest extends TestCase  {
    private Account account;
    private Context context;
    private ExercisesDataSource datasource;

    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        account = new Account();
        datasource = new ExercisesDataSource(context);
        datasource.open();
    }

    public void tearDown() throws Exception {
        datasource.close();
    }

    @Test
    public void testCreateExercise() {
        // Arrange
        // See setup

        // Act
        Exercise exercise = datasource.createExercise("New fangled exercise", account.getId(), "Crazy",
                50, 0, 0, 0, 8, 5, 0);

        // Assert
        assertNotNull(exercise);
        assertEquals("New fangled exercise", exercise.getName());
        //assertEquals("Crazy", exercise.getType());
    }

    @Test
    public void testCreateMeal() {
        // Arrange
        // See setup

        // Act
        Meal meal = datasource.createMeal("Midnight Snack", account.getId(), 600);

        // Assert
        assertNotNull(meal);
        assertEquals("Midnight Snack", meal.getName());
    }

    @Test
    public void testGetAllExercises() {
        // Arrange
        // See setup

        // Act
        List<Exercise> exerciseList = datasource.getAllExercises();

        // Assert
        assertNotNull(exerciseList);
        assertTrue(exerciseList.size() > 0);
    }

    @Test
    public void testGetAllMeals() {
        // Arrange
        // See setup

        // Act
        List<Meal> mealList = datasource.getAllMeals();

        // Assert
        assertNotNull(mealList);
        assertTrue(mealList.size() > 0);
    }
}