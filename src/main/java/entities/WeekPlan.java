/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author simon
 */
@Entity
public class WeekPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer weekPlanId;
    private int weekNo;
    private int year;

    @ManyToMany(mappedBy = "weekPlans", cascade = CascadeType.PERSIST)
    private List<Recipe> recipes = new ArrayList<Recipe>();

    public WeekPlan() {
    }

    public WeekPlan(int weekNo, int year) {
        this.weekNo = weekNo;
        this.year = year;
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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRecipes(Recipe recipe) {
        this.recipes.add(recipe);
        recipe.setWeekPlan(this);
    }

    public Integer getId() {
        return weekPlanId;
    }

    public void setId(Integer id) {
        this.weekPlanId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (weekPlanId != null ? weekPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the weekPlanId fields are not set
        if (!(object instanceof WeekPlan)) {
            return false;
        }
        WeekPlan other = (WeekPlan) object;
        if ((this.weekPlanId == null && other.weekPlanId != null) || (this.weekPlanId != null && !this.weekPlanId.equals(other.weekPlanId))) {
            return false;
        }
        return true;
    }

}
