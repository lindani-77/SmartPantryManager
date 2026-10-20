package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView tvTitle = findViewById(R.id.tvRecipeTitle);
        TextView tvInstructions = findViewById(R.id.tvInstructions);

        // Retrieve data passed through Intent extras
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        String instructions = getIntent().getStringExtra("RECIPE_INSTRUCTIONS");

        if (recipeName != null) tvTitle.setText(recipeName);
        if (instructions != null) tvInstructions.setText(instructions);
    }
}