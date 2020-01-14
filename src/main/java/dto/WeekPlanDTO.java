/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Recipe;
import entities.WeekPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author simon
 */
public class WeekPlanDTO {

    private Integer weekPlanId;
    private int weekNo;
    private int year;

    private List<RecipeDTO> recipes = new ArrayList();

    public WeekPlanDTO() {
    }

    public WeekPlanDTO(WeekPlan weekplan) {
        this.weekPlanId = weekplan.getWeekPlanId();
        this.weekNo = weekplan.getWeekNo();
        this.year = weekplan.getYear();
        for (Recipe recipe : weekplan.getRecipes()) {
            this.recipes.add(new RecipeDTO(recipe));
        }
    }

    public Integer getWeekPlanId() {
        return weekPlanId;
    }

    public void setWeekPlanId(Integer weekPlanId) {
        this.weekPlanId = weekPlanId;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.weekPlanId);
        hash = 17 * hash + this.weekNo;
        hash = 17 * hash + this.year;
        hash = 17 * hash + Objects.hashCode(this.recipes);
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
        final WeekPlanDTO other = (WeekPlanDTO) obj;
        if (this.weekNo != other.weekNo) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.weekPlanId, other.weekPlanId)) {
            return false;
        }
        if (!Objects.equals(this.recipes, other.recipes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WeekPlanDTO{" + "weekPlanId=" + weekPlanId + ", weekNo=" + weekNo + ", year=" + year + ", recipes=" + recipes + '}';
    }

}
