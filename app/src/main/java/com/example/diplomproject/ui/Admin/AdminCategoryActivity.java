package com.example.diplomproject.ui.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.diplomproject.R;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView gornorudnie, nestacionarnie, dlyasignalizaciiiblokirovki, kabeliiprovodamontazhnie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        init();

        gornorudnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "gornorudnie");
                startActivity(intent);
            }
        });
        nestacionarnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "nestacionarnie");
                startActivity(intent);
            }
        });
        dlyasignalizaciiiblokirovki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "dlyasignalizaciiiblokirovki");
                startActivity(intent);
            }
        });
        kabeliiprovodamontazhnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "kabeliiprovodamontazhnie");
                startActivity(intent);
            }
        });
    }
    private void init() {
        gornorudnie = findViewById(R.id.gornorudnie);
        nestacionarnie = findViewById(R.id.nestacionarnie);
        dlyasignalizaciiiblokirovki = findViewById(R.id.dlyasignalizaciiiblokirovki);
        kabeliiprovodamontazhnie = findViewById(R.id.kabeliiprovodamontazhnie);
    }
}