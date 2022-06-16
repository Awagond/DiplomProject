package com.example.cabelpc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cabelpc.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText etxtFullName, etxtPhoneNumber, etxtHomeAddress, etxtCityName;
    private Button shippmentBackbtn, shippmentConfirmBtn;
    private String totalAmount = "";
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Общая цена :" + totalAmount, Toast.LENGTH_SHORT).show();

        etxtFullName = (EditText) findViewById(R.id.shippment_name);
        etxtPhoneNumber = (EditText) findViewById(R.id.shippment_phone);
        etxtHomeAddress = (EditText) findViewById(R.id.shippment_home_address);
        etxtCityName = (EditText) findViewById(R.id.shippment_city_name);
        shippmentBackbtn = (Button) findViewById(R.id.shippment_back_btn);
        shippmentConfirmBtn = (Button) findViewById(R.id.shippment_confirm_btn);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        shippmentConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmFinalOrderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setAutoCancel(false)
                                .setSmallIcon(R.drawable.app_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pendingIntent)
                                .setContentTitle("Заказ")
                                .setContentText("Заказ принят!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                createChanel(notificationManager);
                notificationManager.notify(NOTIFY_ID, notificationBuilder.build());

                check();
            }
        });
    }

    private void createChanel(NotificationManager manager) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private void check() {
        if (TextUtils.isEmpty(etxtFullName.getText().toString())) {
            Toast.makeText(this, "Укажите логин", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etxtPhoneNumber.getText().toString())) {
            Toast.makeText(this, "Укажите номер телефона", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etxtHomeAddress.getText().toString())) {
            Toast.makeText(this, "Укажите адрес", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etxtCityName.getText().toString())) {
            Toast.makeText(this, "Укажите город", Toast.LENGTH_SHORT).show();
        } else {
            confirmOrder();
        }

    }

    private void confirmOrder() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        String saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        String saveCurrentTime = currentTime.format(calendar.getTime());

        final DatabaseReference OrdersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());

        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount", totalAmount);
        orderMap.put("name", etxtFullName.getText().toString());
        orderMap.put("phone", etxtPhoneNumber.getText().toString());
        orderMap.put("address", etxtHomeAddress.getText().toString());
        orderMap.put("city", etxtCityName.getText().toString());
        orderMap.put("date", saveCurrentDate);
        orderMap.put("time", saveCurrentTime);
        orderMap.put("State", "not shipped");

        OrdersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User View")
                            .child(Prevalent.currentOnlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ConfirmFinalOrderActivity.this, "Succes", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                }

            }
        });
    }
}
