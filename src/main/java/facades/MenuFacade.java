/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.RecipeDTO;
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

    public List<RecipeDTO> getAllRecipes(){
        EntityManager em = emf.createEntityManager();
        List<RecipeDTO> listDTO = new ArrayList();
        try{
            List<Recipe> list = em.createQuery("SELECT r FROM Recipe r").getResultList();
            for(Recipe recipe : list) {
                listDTO.add(new RecipeDTO(recipe));
            }
            return listDTO;
        }finally{
            em.close();
        }
    }    
    
    public RecipeDTO getRecipe(int id){
        EntityManager em = emf.createEntityManager();
        try{
            Recipe recipe = em.find(Recipe.class, id);
            return new RecipeDTO(recipe);
        }finally{
            em.close();
        }
    }    
//
//    public void 
//    
    public void setup() {
        EntityManager em = emf.createEntityManager();
        Item item = new Item("Spaghetti", 200);
        item.equals(15);
        Ingredient ingredient = new Ingredient(20);
        Recipe recipe = new Recipe(50, "First you fill the bowl");
        recipe.setIngredients(ingredient);
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
