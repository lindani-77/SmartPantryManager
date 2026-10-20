package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private RecyclerView rvSuggested;
    private TextView tvEmptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        db = new DatabaseHelper(this);
        rvSuggested = findViewById(R.id.rvSuggested);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        rvSuggested.setLayoutManager(new LinearLayoutManager(this));

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {
        // Fetch recipes matching strict-matching rules from DatabaseHelper
        List<Recipe> matchedRecipes = db.getSuggestedRecipes();

        if (matchedRecipes == null || matchedRecipes.isEmpty()) {
            // Zero-State UI: Hide list, show user-friendly feedback text
            rvSuggested.setVisibility(View.GONE);
            tvEmptyState.setVisibility(View.VISIBLE);
            tvEmptyState.setText("No recipes match your pantry yet - add more ingredients");
        } else {
            rvSuggested.setVisibility(View.VISIBLE);
            tvEmptyState.setVisibility(View.GONE);

            // Bind to your recipe adapter here
            // RecipeAdapter recipeAdapter = new RecipeAdapter(matchedRecipes, recipe -> {
            //     Intent intent = new Intent(this, RecipeDetailActivity.class);
            //     intent.putExtra("RECIPE_NAME", recipe.getName());
            //     intent.putExtra("RECIPE_INSTRUCTIONS", recipe.getInstructions());
            //     startActivity(intent);
            // });
            // rvSuggested.setAdapter(recipeAdapter);
        }
    }
}