package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Recipe;
import entities.User;
import entities.WeekPlan;
import errorhandling.NotFoundException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MenuFacadeTest {

    private static EntityManagerFactory emf;
    private static MenuFacade facade;
    LocalDate date = LocalDate.now();
    TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

    private static final Gson GSON = new GsonBuilder().create();

    public MenuFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/eksamen_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = MenuFacade.getMenuFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MenuFacade.getMenuFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from WeekPlan").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from WeekPlan").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            User user = new User("user", "user");
            User admin = new User("admin", "admin");

            em.persist(user);
            em.persist(admin);
            WeekPlan weekPlan = new WeekPlan();
            List<Recipe> recipeList = new ArrayList<>();
            Recipe recipe = new Recipe("Spaghetti and meatballs", 60, "Brown the meatballs, "
                    + "heat and season the tomatosauce, cook the pasta and serve immediatly when the different components are done.");
            recipeList.add(recipe);
            weekPlan.setRecipes((Recipe) recipeList);

            em.persist(weekPlan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void deleteWeekPlan() {
        try {
            assertThat(facade.deleteWeekPlan(1), is(equalTo("success")));
        } catch (NotFoundException e) {
            fail("There's no such weekplan");
        }
    }

    @Test
    public void deleteNonExisitingWeekPlan() {
        try {
            facade.deleteWeekPlan(2);
            fail("No exception was thrown");
        } catch (NotFoundException e) {
            assertThat(true, is(true));
        }
    }

    @Test
    public void checkCorrectLocalDate() {
        EntityManager em = emf.createEntityManager();
        WeekPlan weekplan = em.find(WeekPlan.class, 1);
        weekplan.getWeekNo();
        int weekNumber = date.get(woy);
        try {
            assertThat(weekNumber + 1, is(equalTo(weekplan.getWeekNo())));
        } catch (Exception e) {
            fail("The weeknumber doesnt match up, indicating a difference in localdate");
        }

    }
}
