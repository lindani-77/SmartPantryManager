package com.example.smartpantrymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class PantryListActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private RecyclerView rvPantry;
    private PantryAdapter adapter;
    private List<PantryItem> pantryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        db = new DatabaseHelper(this);
        rvPantry = findViewById(R.id.rvPantry);
        rvPantry.setLayoutManager(new LinearLayoutManager(this));

        // 1. FAB to open Add/Edit screen
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        // 2. Button to navigate to Suggested Recipes screen
        Button btnSuggested = findViewById(R.id.btnSuggested);
        btnSuggested.setOnClickListener(v -> {
            Intent intent = new Intent(PantryListActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });

        // 3. Button/Menu option to navigate to Settings screen
        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(PantryListActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryDataFromDatabase(); // Automatically refreshes list when returning from Add screen
    }

    private void loadPantryDataFromDatabase() {
        pantryList = new ArrayList<>();
        Cursor cursor = db.getAllPantryItemsCursor();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double qty = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
                String expiry = cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"));

                pantryList.add(new PantryItem(id, name, qty, unit, expiry));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Bind RecyclerView to PantryAdapter
        adapter = new PantryAdapter(pantryList);
        rvPantry.setAdapter(adapter);
    }
}