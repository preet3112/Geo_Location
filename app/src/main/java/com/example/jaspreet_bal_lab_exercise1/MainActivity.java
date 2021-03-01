package com.example.jaspreet_bal_lab_exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Geocoder geocoder;
    EditText address;
    Button coordinates;
    TextView latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        address = (EditText)findViewById(R.id.address);
        coordinates = (Button)findViewById(R.id.coordinates);
        latlng = (TextView)findViewById(R.id.latlng);
        geocoder = new Geocoder(this);
        coordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddressDetails();
            }
        });

    }

    private void loadAddressDetails() {
        if (address.getText().toString() == null){
            Toast.makeText(this, "Please Enter the address", Toast.LENGTH_SHORT).show();
        }else {
            try {
                List<Address> addresses = geocoder.getFromLocationName(address.getText().toString(), 1);
                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    System.out.println("Address" + address.toString());
                    latlng.setText("Latitude :"+address.getLatitude() +"\n Longitude :"+address.getLongitude());

                }else {
                    latlng.setText("No lat/long found for this address!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}