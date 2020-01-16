/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.IngredientDTO;
import dto.RecipeDTO;
import dto.WeekPlanDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import entities.WeekPlan;
import errorhandling.NotFoundException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author simon
 */
public class MenuFacade {

    private static MenuFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MenuFacade() {
    }

    public static MenuFacade getMenuFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<RecipeDTO> getAllRecipes() {
        EntityManager em = emf.createEntityManager();
        List<RecipeDTO> listDTO = new ArrayList();
        try {
            List<Recipe> list = em.createQuery("SELECT r FROM Recipe r").getResultList();
            for (Recipe recipe : list) {
                listDTO.add(new RecipeDTO(recipe));
            }
            return listDTO;
        } finally {
            em.close();
        }
    }

    public WeekPlanDTO getWeekPlanDTO(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            WeekPlan weekPlan = em.find(WeekPlan.class, id);
            return new WeekPlanDTO(weekPlan);
        } finally {
            em.close();
        }
    }

    public List<WeekPlanDTO> getAllWeekPlans() {
        EntityManager em = emf.createEntityManager();
        List<WeekPlanDTO> weekPlanDTO = new ArrayList<>();
        try {
            List<WeekPlan> list = em.createQuery("SELECT w FROM WeekPlan w").getResultList();
            for (WeekPlan weekPlan : list) {
                weekPlanDTO.add(new WeekPlanDTO(weekPlan));
            }
            return weekPlanDTO;
        } finally {
            em.close();
        }
    }

    public WeekPlan createWeekPlan(List<Recipe> recipe) {
        EntityManager em = emf.createEntityManager();
        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = date.get(woy);
        WeekPlan weekplan = new WeekPlan((weekNumber + 1), date.getYear());
        for (Recipe recipe1 : recipe) {
            weekplan.setRecipes(recipe1);
        }
        try {
            em.getTransaction().begin();
            em.persist(weekplan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WeekPlan();
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        EntityManager em = emf.createEntityManager();

        Recipe recipe = new Recipe(recipeDTO.getName(), recipeDTO.getPreptime(), recipeDTO.getDirections());
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient(ingredientDTO.getAmount(), new Item(
                    ingredientDTO.getItemDTO().getName(),
                    ingredientDTO.getItemDTO().getPrice(),
                    ingredientDTO.getItemDTO().getQty()));
            recipe.setIngredients(ingredient);
        }
        try {
            em.getTransaction().begin();
            em.persist(recipe);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RecipeDTO(recipe);
    }

    public RecipeDTO editRecipe(RecipeDTO recipeDTO, int id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        recipe.setName(recipeDTO.getName());
        recipe.setPreptime(recipeDTO.getPreptime());
        recipe.setDirections(recipeDTO.getDirections());
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {

            for (int i = 0; i < recipe.getIngredients().size() - 1; i++) {
                Ingredient ingredient = em.find(Ingredient.class, recipe.getIngredients().get(i).getId());
                if (!(ingredient.equals(recipe.getIngredients().get(i)))) {
                    ingredient = new Ingredient(ingredientDTO.getAmount(), new Item(
                            ingredientDTO.getItemDTO().getName(),
                            ingredientDTO.getItemDTO().getPrice(),
                            ingredientDTO.getItemDTO().getQty()));
                    recipe.setIngredients(ingredient);
                } else {
                    try {
                        em.getTransaction().begin();
                        em.merge(recipe);
                        em.getTransaction().commit();
                    } finally {
                        em.close();
                    }
                }
            }
        }
        return new RecipeDTO(recipe);
    }

    public RecipeDTO getRecipe(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Recipe recipe = em.find(Recipe.class, id);
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public String deleteWeekPlan(int id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        WeekPlan weekplan = em.find(WeekPlan.class, id);

        if (weekplan == null) {
            throw new NotFoundException("Unfortunately that weekplan doesn't exist, it may already have been deleted.");
        }

        em.getTransaction().begin();
        em.remove(weekplan);
        em.getTransaction().commit();

        return "success";
    }

    public void setup() {
        EntityManager em = emf.createEntityManager();
        Item item = new Item("Spaghetti", 7.5);
        Item item1 = new Item("Tomatoes", 13);
        Item item2 = new Item("Meatballs", 53);
        Ingredient ingredient = new Ingredient(300);
        Ingredient ingredient1 = new Ingredient(250);
        Ingredient ingredient2 = new Ingredient(500);
        ingredient.setItem(item);
        ingredient1.setItem(item1);
        ingredient2.setItem(item2);
        Recipe recipe = new Recipe("Spaghetti and meatballs", 50, "First you fill a pot with water, "
                + "bring it to a simmer and then add your pasta to it. Take your tomatoes and add them to a pot, heat and reduce it."
                + " Bring out a large skillet and add your meatballs and cook them until golden brown. Serve immediately");
        recipe.setIngredients(ingredient);
        recipe.setIngredients(ingredient1);
        recipe.setIngredients(ingredient2);
        Item item3 = new Item("Spaghetti", 7.5);
        Item item4 = new Item("Garlic", 20);
        Item item5 = new Item("Parsley", 15);
        Ingredient ingredient3 = new Ingredient(750);
        Ingredient ingredient4 = new Ingredient(240);
        Ingredient ingredient5 = new Ingredient(350);
        ingredient3.setItem(item3);
        ingredient4.setItem(item4);
        ingredient5.setItem(item5);
        Recipe recipe1 = new Recipe("Pasta olio e aglio", 10, "First you fill a pot with water, "
                + "bring it to a simmer and then add your pasta to it. Take your garlic, mince it finely");
//                + " and add to a skillet with a good amount of olive oil. Keep going until the garlic has taken on some colour."
//                + " Bring out a large skillet and add your pasta, garlic and parsley. If the sauce is too thin, add some pasta water. Serve immediately");
        recipe1.setIngredients(ingredient3);
        recipe1.setIngredients(ingredient4);
        recipe1.setIngredients(ingredient5);
        Item item6 = new Item("Egg", 5);
        Item item7 = new Item("Bacon", 12);
        Item item8 = new Item("Toast", 10);
        Ingredient ingredient6 = new Ingredient(350);
        Ingredient ingredient7 = new Ingredient(275);
        Ingredient ingredient8 = new Ingredient(675);
        ingredient6.setItem(item6);
        ingredient7.setItem(item7);
        ingredient8.setItem(item8);
        Recipe recipe2 = new Recipe("Fried bacon and eggs with a side of toast", 20, "Take out a pan. Add some butter to the pan and toast your toast to your liking and then set aside. "
                + " Cook your bacon on one side of the pan. If you want to achieve an even fry, add a bit of oil to the pan, make room for the eggs.");
//                + " If you're not experienced with cracking eggs, you can crack them in a seperate container before you add them to the pan, to avoid any eggs shells making their way to the pan."
//                + " add your eggs to the pan and cook them to your liking. The same trick with oil can be done here, it'll crisp up the egg. Serve immediately");
        recipe2.setIngredients(ingredient6);
        recipe2.setIngredients(ingredient7);
        recipe2.setIngredients(ingredient8);
        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = date.get(woy);
            WeekPlan plan = new WeekPlan((weekNumber + 1), date.getYear());
            plan.setRecipes(recipe);
            plan.setRecipes(recipe1);
            plan.setRecipes(recipe2);
        try {
            em.getTransaction().begin();
            em.persist(plan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
