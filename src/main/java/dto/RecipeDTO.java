/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import entities.Recipe;
import entities.WeekPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author simon
 */
public class RecipeDTO {

    private Integer id;
    private int preptime;
    private String directions;
    private List<IngredientDTO> ingredients = new ArrayList<>();

    public RecipeDTO() {
    }

    public RecipeDTO(Recipe recipe) {

        this.id = recipe.getId();
        this.preptime = recipe.getPreptime();
        this.directions = recipe.getDirections();
        for (Ingredient ingredient : recipe.getIngredients()) {
            this.ingredients.add(new IngredientDTO(ingredient));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPreptime() {
        return preptime;
    }

    public void setPreptime(int preptime) {
        this.preptime = preptime;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + this.preptime;
        hash = 23 * hash + Objects.hashCode(this.directions);
        hash = 23 * hash + Objects.hashCode(this.ingredients);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeDTO other = (RecipeDTO) obj;
        if (this.preptime != other.preptime) {
            return false;
        }
        if (!Objects.equals(this.directions, other.directions)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" + "id=" + id + ", preptime=" + preptime + ", directions=" + directions + ", ingredients=" + ingredients + '}';
    }

}
