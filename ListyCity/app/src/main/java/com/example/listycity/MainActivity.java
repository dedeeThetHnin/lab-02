package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    Button addCityBtn, deleteCityBtn, confirmBtn;
    EditText inputCity;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    int selectedPostion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        addCityBtn = findViewById(R.id.add_city);
        deleteCityBtn = findViewById(R.id.delete_city);
        confirmBtn = findViewById(R.id.confirm_city);
        inputCity = findViewById(R.id.input_city);

        //String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna",
        //"Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        //dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //to select city
        cityList.setOnItemClickListener((parent, view, position, id)->{
            selectedPostion=position;
        });

        //add city
        addCityBtn.setOnClickListener(v->{
            inputCity.requestFocus();
        });

        //confirm city
        confirmBtn.setOnClickListener(v->{
            String cityName = inputCity.getText().toString().trim();

            if (!cityName.isEmpty()){
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");
            }
        });

        //delete city
        deleteCityBtn.setOnClickListener(v->{
            if(selectedPostion != -1){
                dataList.remove(selectedPostion);
                cityAdapter.notifyDataSetChanged();
                selectedPostion = -1;
            }
        });
    }
}