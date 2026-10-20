package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity {
    private EditText etName, etQuantity, etUnit, etExpiry;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        db = new DatabaseHelper(this);
        etName = findViewById(R.id.etIngredientName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiry = findViewById(R.id.etExpiryDate);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> validateAndSaveIngredient());
    }

    private void validateAndSaveIngredient() {
        String nameStr = etName.getText().toString().trim();
        String qtyStr = etQuantity.getText().toString().trim();
        String unitStr = etUnit.getText().toString().trim();
        String expiryStr = etExpiry.getText().toString().trim();

        // Input Validation: Check for empty fields
        if (nameStr.isEmpty() || qtyStr.isEmpty() || unitStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Input Validation: Parse quantity safely
        double quantity;
        try {
            quantity = Double.parseDouble(qtyStr);
            if (quantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantity must be a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to SQLite database via DatabaseHelper
        boolean isInserted = db.insertPantryItem(nameStr, quantity, unitStr, expiryStr);

        if (isInserted) {
            Toast.makeText(this, "Ingredient added successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Closes activity and returns to PantryListActivity
        } else {
            Toast.makeText(this, "Error saving ingredient to database", Toast.LENGTH_SHORT).show();
        }
    }
}