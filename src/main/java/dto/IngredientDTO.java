/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import java.util.Objects;

/**
 *
 * @author simon
 */
public class IngredientDTO {

    private Integer id;
    private int amount;
    private ItemDTO itemDTO;

    public IngredientDTO() {
    }

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.amount = ingredient.getAmount();
        this.itemDTO = new ItemDTO(ingredient.getItem());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
