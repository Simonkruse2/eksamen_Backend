/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author simon
 */
@Entity
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int preptime;
    private String directions;

    @ManyToMany
    private List<WeekPlan> weekPlans = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(int preptime, String directions) {
        this.preptime = preptime;
        this.directions = directions;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<WeekPlan> getWeekPlans() {
        return weekPlans;
    }

    public void setWeekPlan(WeekPlan weekPlan) {
        this.weekPlans.add(weekPlan);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + this.preptime;
        hash = 79 * hash + Objects.hashCode(this.directions);
        hash = 79 * hash + Objects.hashCode(this.weekPlans);
        hash = 79 * hash + Objects.hashCode(this.ingredients);
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
        final Recipe other = (Recipe) obj;
        if (this.preptime != other.preptime) {
            return false;
        }
        if (!Objects.equals(this.directions, other.directions)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.weekPlans, other.weekPlans)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" + "id=" + id + ", preptime=" + preptime + ", directions=" + directions + ", weekPlans=" + weekPlans + ", ingredients=" + ingredients + '}';
    }

}
