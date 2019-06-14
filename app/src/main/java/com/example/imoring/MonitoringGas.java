package com.example.imoring;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

public class MonitoringGas extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private Float GAS_VALUE;
    private Float AIR_VALUE;
    private Float CO_VALUE;
    private Float CH4_VALUE;
    private Float LPG_VALUE;
    private Float GASVOLT_VALUE;
    private Float POLUTION_VALUE;


    TextView gasValue;
    TextView airValue;
    TextView gasVolt;
    TextView gasCO;
    TextView gasCH4;
    TextView gasLPG;
    TextView polution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_gas);

        gasValue = (TextView) findViewById(R.id.GasValue);
        airValue = (TextView) findViewById(R.id.airValue);
        gasVolt = (TextView) findViewById(R.id.gasvoltValue);
        gasCO = (TextView) findViewById(R.id.coValue);
        gasLPG = (TextView) findViewById(R.id.lpgValue);
        gasCH4 = (TextView) findViewById(R.id.ch4Value);
        polution = (TextView) findViewById(R.id.polutionValue);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GAS_VALUE = dataSnapshot.child("GAS_VALUE").getValue(Float.class);
                AIR_VALUE = dataSnapshot.child("air_value").getValue(Float.class);
                CO_VALUE = dataSnapshot.child("gas_co").getValue(Float.class);
                LPG_VALUE = dataSnapshot.child("gas_lpg").getValue(Float.class);
                CH4_VALUE = dataSnapshot.child("gas_ch4").getValue(Float.class);
                GASVOLT_VALUE = dataSnapshot.child("gas_volt").getValue(Float.class);
                POLUTION_VALUE = dataSnapshot.child("polution").getValue(Float.class);

                gasValue.setText(GAS_VALUE.toString());
                airValue.setText(AIR_VALUE.toString());
                gasCO.setText(CO_VALUE.toString());
                gasCH4.setText(CH4_VALUE.toString());
                gasLPG.setText(LPG_VALUE.toString());
                gasVolt.setText(GASVOLT_VALUE.toString());
                polution.setText(POLUTION_VALUE.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
