package com.example.smartpantrymanager;

public class RecipeIngredient {
    private String ingredientName;
    private double requiredQuantity;
    private String unit;

    public RecipeIngredient(String ingredientName, double requiredQuantity, String unit) {
        this.ingredientName = ingredientName;
        this.requiredQuantity = requiredQuantity;
        this.unit = unit;
    }

    public String getIngredientName() { return ingredientName; }
    public double getRequiredQuantity() { return requiredQuantity; }
    public String getUnit() { return unit; }
}