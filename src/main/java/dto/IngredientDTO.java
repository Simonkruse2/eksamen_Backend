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

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + this.amount;
        hash = 89 * hash + Objects.hashCode(this.itemDTO);
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
        final IngredientDTO other = (IngredientDTO) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.itemDTO, other.itemDTO)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" + "id=" + id + ", amount=" + amount + ", itemDTO=" + itemDTO + '}';
    }

}
