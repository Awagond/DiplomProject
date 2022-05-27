package com.comptech.login.khuranas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView gornorudnie, dlyasignalizaciiiblokirovki, nestacionarnie, kabeliiprovodamontazhnie;
    private ImageView dlyapogruzhnihneftyanihelektronasosov, kabelisudovieimorskiegruzonesuschie, kabeliupravleniya, kontrolniekabeli;
    private ImageView provodadlyatermopar, silovie, siloviedlyaelektroustanovok, provodasvyazi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        gornorudnie = (ImageView) findViewById(R.id.gornorudnie);
        dlyasignalizaciiiblokirovki = (ImageView) findViewById(R.id.dlyasignalizaciiiblokirovki);
        nestacionarnie = (ImageView) findViewById(R.id.nestacionarnie);
        kabeliiprovodamontazhnie = (ImageView) findViewById(R.id.kabeliiprovodamontazhnie);

        dlyapogruzhnihneftyanihelektronasosov = (ImageView) findViewById(R.id.dlyapogruzhnihneftyanihelektronasosov);
        kabelisudovieimorskiegruzonesuschie = (ImageView) findViewById(R.id.kabelisudovieimorskiegruzonesuschie);
        kabeliupravleniya = (ImageView) findViewById(R.id.kabeliupravleniya);
        kontrolniekabeli = (ImageView) findViewById(R.id.kontrolniekabeli);

        provodadlyatermopar = (ImageView) findViewById(R.id.provodadlyatermopar);
        silovie = (ImageView) findViewById(R.id.silovie);
        siloviedlyaelektroustanovok = (ImageView) findViewById(R.id.siloviedlyaelektroustanovok);
        provodasvyazi = (ImageView) findViewById(R.id.provodasvyazi);


        gornorudnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "Gornorudnie");
                startActivity(intent);
            }
        });


        dlyasignalizaciiiblokirovki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "DlyaSignalizaciiIBlokirovki");
                startActivity(intent);
            }
        });


        nestacionarnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "Nestacionarnie");
                startActivity(intent);
            }
        });

        kabeliiprovodamontazhnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,HomeActivity .class);
                intent.putExtra("category", "KabeliiProvodaMontazhnie");
                startActivity(intent);
            }
        });

        dlyapogruzhnihneftyanihelektronasosov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "DlyaPogruzhnihNeftyanihElektronasosov");
                startActivity(intent);
            }
        });


        kabelisudovieimorskiegruzonesuschie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "KabeliSudovieiMorskieGruzoNesuschie");
                startActivity(intent);
            }
        });


        kabeliupravleniya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "KabeliUpravleniya");
                startActivity(intent);
            }
        });

        kontrolniekabeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,HomeActivity .class);
                intent.putExtra("category", "KontrolnieKabeli");
                startActivity(intent);
            }
        });

        provodadlyatermopar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "ProvodaDlyaTermopar");
                startActivity(intent);
            }
        });


        silovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "Silovie");
                startActivity(intent);
            }
        });


        siloviedlyaelektroustanovok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddnewProductActivity.class);
                intent.putExtra("category", "SilovieDlyaElektroUstanovok");
                startActivity(intent);
            }
        });

        provodasvyazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,HomeActivity .class);
                intent.putExtra("category", "ProvodaSvyazi");
                startActivity(intent);
            }
        });
    }
}
