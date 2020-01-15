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
    
    public List<WeekPlanDTO> getAllWeekPlans(){
        EntityManager em = emf.createEntityManager();
        List<WeekPlanDTO> weekPlanDTO = new ArrayList<>();
        try{
            List<WeekPlan> list = em.createQuery("SELECT w FROM WeekPlan w").getResultList();
            for (WeekPlan weekPlan : list) {
                weekPlanDTO.add(new WeekPlanDTO(weekPlan));
            }
            return weekPlanDTO;
        }finally{
            em.close();
        }
    }
    
    public WeekPlan createWeekPlan(List<Recipe> recipe) {
        EntityManager em = emf.createEntityManager();

        WeekPlan weekplan = new WeekPlan();
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
        Recipe recipe = new Recipe("Spaggoot", 50, "First you fill the bowl");
        recipe.setIngredients(ingredient);
        recipe.setIngredients(ingredient1);
        recipe.setIngredients(ingredient2);
        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = date.get(woy);
        try {
            WeekPlan plan = new WeekPlan((weekNumber + 1), date.getYear());
            plan.setRecipes(recipe);
            em.getTransaction().begin();
            em.persist(plan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
