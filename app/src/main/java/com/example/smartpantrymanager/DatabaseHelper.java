package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pantry_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_PANTRY = "pantry";
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";

    // Common columns
    public static final String COLUMN_ID = "id";

    // Pantry Table Columns
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QTY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";
    public static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    // Recipes Table Columns
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    // Recipe Ingredients Table Columns
    public static final String COLUMN_RI_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RI_ING_NAME = "ingredient_name";
    public static final String COLUMN_RI_QTY = "required_quantity";
    public static final String COLUMN_RI_UNIT = "unit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Create Pantry Table
        String createPantryTable = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PANTRY_NAME + " TEXT NOT NULL, " +
                COLUMN_PANTRY_QTY + " REAL NOT NULL, " +
                COLUMN_PANTRY_UNIT + " TEXT NOT NULL, " +
                COLUMN_PANTRY_EXPIRY + " TEXT)";
        db.execSQL(createPantryTable);

        // 2. Create Recipes Table
        String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INSTRUCTIONS + " TEXT NOT NULL)";
        db.execSQL(createRecipesTable);

        // 3. Create Recipe Ingredients Table
        String createRecipeIngredientsTable = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RI_RECIPE_ID + " INTEGER, " +
                COLUMN_RI_ING_NAME + " TEXT NOT NULL, " +
                COLUMN_RI_QTY + " REAL NOT NULL, " +
                COLUMN_RI_UNIT + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_RI_RECIPE_ID + ") REFERENCES " + TABLE_RECIPES + "(" + COLUMN_ID + "))";
        db.execSQL(createRecipeIngredientsTable);

        // Pre-load / Seed 15 Recipes and their ingredients
        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    // ==================== SEED DATA (15 Recipes) ====================
    private void seedRecipes(SQLiteDatabase db) {
        // Recipe 1: Scrambled Eggs
        long r1 = insertRecipe(db, "Scrambled Eggs", "Whisk eggs with milk, cook on medium heat in a pan with butter until fluffy.");
        insertRecipeIngredient(db, r1, "Eggs", 2.0, "units");
        insertRecipeIngredient(db, r1, "Milk", 50.0, "ml");
        insertRecipeIngredient(db, r1, "Butter", 10.0, "g");

        // Recipe 2: Grilled Cheese Sandwich
        long r2 = insertRecipe(db, "Grilled Cheese Sandwich", "Butter bread slices, place cheese in between, and grill in a pan until golden brown.");
        insertRecipeIngredient(db, r2, "Bread", 2.0, "slices");
        insertRecipeIngredient(db, r2, "Cheese", 50.0, "g");
        insertRecipeIngredient(db, r2, "Butter", 10.0, "g");

        // Recipe 3: Pasta Aglio e Olio
        long r3 = insertRecipe(db, "Pasta Aglio e Olio", "Boil pasta. Sauté garlic and chili flakes in olive oil, toss pasta in the oil.");
        insertRecipeIngredient(db, r3, "Pasta", 100.0, "g");
        insertRecipeIngredient(db, r3, "Garlic", 3.0, "cloves");
        insertRecipeIngredient(db, r3, "Olive Oil", 20.0, "ml");

        // Recipe 4: Simple Omelette
        long r4 = insertRecipe(db, "Simple Omelette", "Beat eggs, pour into hot oiled pan, fold over when set.");
        insertRecipeIngredient(db, r4, "Eggs", 3.0, "units");
        insertRecipeIngredient(db, r4, "Salt", 2.0, "g");

        // Recipe 5: Tomato Soup
        long r5 = insertRecipe(db, "Tomato Soup", "Simmer chopped tomatoes with garlic and broth, then blend until smooth.");
        insertRecipeIngredient(db, r5, "Tomatoes", 4.0, "units");
        insertRecipeIngredient(db, r5, "Garlic", 2.0, "cloves");
        insertRecipeIngredient(db, r5, "Broth", 200.0, "ml");

        // Recipe 6: Garlic Butter Rice
        long r6 = insertRecipe(db, "Garlic Butter Rice", "Cook rice, then fry in a pan with melted butter and minced garlic.");
        insertRecipeIngredient(db, r6, "Rice", 150.0, "g");
        insertRecipeIngredient(db, r6, "Butter", 20.0, "g");
        insertRecipeIngredient(db, r6, "Garlic", 2.0, "cloves");

        // Recipe 7: Sugar Pancakes
        long r7 = insertRecipe(db, "Sugar Pancakes", "Mix flour, milk, and egg into batter. Fry ladlefuls on a pan and sprinkle sugar.");
        insertRecipeIngredient(db, r7, "Flour", 100.0, "g");
        insertRecipeIngredient(db, r7, "Milk", 100.0, "ml");
        insertRecipeIngredient(db, r7, "Eggs", 1.0, "units");
        insertRecipeIngredient(db, r7, "Sugar", 15.0, "g");

        // Recipe 8: Boiled Rice
        long r8 = insertRecipe(db, "Boiled Rice", "Simmer rice in boiling salted water until tender.");
        insertRecipeIngredient(db, r8, "Rice", 200.0, "g");
        insertRecipeIngredient(db, r8, "Salt", 5.0, "g");

        // Recipe 9: Fried Egg
        long r9 = insertRecipe(db, "Fried Egg", "Crack egg into hot oiled pan and fry until whites are set.");
        insertRecipeIngredient(db, r9, "Eggs", 1.0, "units");
        insertRecipeIngredient(db, r9, "Oil", 10.0, "ml");

        // Recipe 10: Buttered Toast
        long r10 = insertRecipe(db, "Buttered Toast", "Toast bread slices in a toaster and spread butter on top.");
        insertRecipeIngredient(db, r10, "Bread", 2.0, "slices");
        insertRecipeIngredient(db, r10, "Butter", 15.0, "g");

        // Recipe 11: Garlic Bread
        long r11 = insertRecipe(db, "Garlic Bread", "Spread garlic butter over bread slices and bake until crispy.");
        insertRecipeIngredient(db, r11, "Bread", 2.0, "slices");
        insertRecipeIngredient(db, r11, "Butter", 20.0, "g");
        insertRecipeIngredient(db, r11, "Garlic", 2.0, "cloves");

        // Recipe 12: Sweet Milk
        long r12 = insertRecipe(db, "Sweet Milk", "Warm milk in a pot and stir in sugar until dissolved.");
        insertRecipeIngredient(db, r12, "Milk", 250.0, "ml");
        insertRecipeIngredient(db, r12, "Sugar", 20.0, "g");

        // Recipe 13: Pasta with Butter
        long r13 = insertRecipe(db, "Pasta with Butter", "Boil pasta and toss with melted butter and salt.");
        insertRecipeIngredient(db, r13, "Pasta", 100.0, "g");
        insertRecipeIngredient(db, r13, "Butter", 20.0, "g");
        insertRecipeIngredient(db, r13, "Salt", 3.0, "g");

        // Recipe 14: Boiled Eggs
        long r14 = insertRecipe(db, "Boiled Eggs", "Place eggs in boiling water for 8 minutes, then cool in water.");
        insertRecipeIngredient(db, r14, "Eggs", 2.0, "units");

        // Recipe 15: Garlic Tomato Pasta
        long r15 = insertRecipe(db, "Garlic Tomato Pasta", "Boil pasta. Sauté garlic and chopped tomatoes, mix together.");
        insertRecipeIngredient(db, r15, "Pasta", 100.0, "g");
        insertRecipeIngredient(db, r15, "Garlic", 2.0, "cloves");
        insertRecipeIngredient(db, r15, "Tomatoes", 2.0, "units");
    }

    private long insertRecipe(SQLiteDatabase db, String name, String instructions) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, name);
        values.put(COLUMN_RECIPE_INSTRUCTIONS, instructions);
        return db.insert(TABLE_RECIPES, null, values);
    }

    private void insertRecipeIngredient(SQLiteDatabase db, long recipeId, String ingName, double qty, String unit) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RI_RECIPE_ID, recipeId);
        values.put(COLUMN_RI_ING_NAME, ingName);
        values.put(COLUMN_RI_QTY, qty);
        values.put(COLUMN_RI_UNIT, unit);
        db.insert(TABLE_RECIPE_INGREDIENTS, null, values);
    }

    // ==================== PANTRY CRUD METHODS ====================

    // 1. CREATE
    public boolean insertPantryItem(String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, name);
        values.put(COLUMN_PANTRY_QTY, quantity);
        values.put(COLUMN_PANTRY_UNIT, unit);
        values.put(COLUMN_PANTRY_EXPIRY, expiryDate);

        long result = db.insert(TABLE_PANTRY, null, values);
        return result != -1;
    }

    // 2. READ (Get all items)
    public Cursor getAllPantryItemsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PANTRY, null);
    }

    // 3. UPDATE
    public boolean updatePantryItem(int id, String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, name);
        values.put(COLUMN_PANTRY_QTY, quantity);
        values.put(COLUMN_PANTRY_UNIT, unit);
        values.put(COLUMN_PANTRY_EXPIRY, expiryDate);

        int rowsAffected = db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }

    // 4. DELETE
    public boolean deletePantryItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_PANTRY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }
}