package com.example.platesnew;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "example.txt";


    private static class PlateInfo {
        String region;
        long count;
        float percent;
    }

    int i = 0;



    private EditText user_field;
    private TextView statText;
    private EditText User_Hint;
    private Button main_btn;
    private TextView result_info;
    private HashMap<String, String> regions;
    private Button infoButton;
    private ArrayList<Plate> plates;
    private Button exit_button;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private PieChart pieChart;





    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);


        setContentView(R.layout.activity_main);

        user_field = findViewById(R.id.user_field);
        Button main_btn = findViewById(R.id.main_btn);
        Button infoButton = findViewById(R.id.infoButton);
        TextView statText = findViewById(R.id.statText);
        TextView textView = findViewById(R.id.textView);
        TextView result_info = findViewById(R.id.result_info);
        Button wipeButton = findViewById(R.id.wipeButton);
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setVisibility(View.INVISIBLE);
        wipeButton.setVisibility(View.INVISIBLE);


        loadData();

        main_btn.setOnClickListener(view -> {
            String code = user_field.getText().toString().trim(); // вводимая юзером цифра

            user_field.setText("");
            user_field.setHint("");

            Plate plate = new Plate(code);
            plate.setRegion();

            if (plate.getRegion() != null) {
                result_info.setText(plate.getRegion());
                plates.add(plate);
            } else {
                result_info.setText("номера не существует");
            }


        });



        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i == 0) {


                    replaceFragment(new statFragment());
                    user_field.setVisibility(View.INVISIBLE);
                    main_btn.setVisibility(View.INVISIBLE);
                    result_info.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                    statText.setVisibility(View.VISIBLE);
                    pieChart.setVisibility(View.VISIBLE);
                    wipeButton.setVisibility(View.VISIBLE);
                    wipeButton.setAnimation(animation);


                    StringBuilder sb = new StringBuilder();
                    ArrayList <Float> divide = new ArrayList<>();
                    secondPage(sb);                           // эти 3 штуки классные

                    statText.setText(sb);

                    /*
                    StringBuilder sb = new StringBuilder();
                    plates.stream()
                            .map(Plate::getRegion)
                            .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                            .entrySet().stream()
                            .peek(stringLongEntry ->
                                    stringLongEntry.setValue(stringLongEntry.getValue() * 100 / plates.size()))
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            //.collect(Collectors.toList())
                            .forEach(e ->
                                    sb.append(String.format("\n%s - %d %1s", e.getKey(), e.getValue(), "%")));

                                    statText.setText(sb);*/

                    //это было для того чтоб списком выводить введенные плейты

                    //дальше пайчарт

                    plateArray(divide);
                    setupPieChart(divide);

                    i++;

                }

                else if (i == 1) {
                    pieChart.setVisibility(View.INVISIBLE);
                    user_field.setVisibility(View.VISIBLE);
                    main_btn.setVisibility(View.VISIBLE);
                    result_info.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    statText.setVisibility(View.INVISIBLE);
                    wipeButton.setVisibility(View.INVISIBLE);
                    i =0;
                }

            }
        } );

        wipeButton.setOnClickListener(view -> {

            plates.clear();
            pieChart.animateXY(1000, 1000);
            pieChart.setVisibility(View.INVISIBLE);
            statText.setText("");



        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }


    private void replaceFragment(Fragment statFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, statFragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void secondPage (StringBuilder sb){


        var histogram = plates.stream()
                .map(Plate::getRegion)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .map(e->{
                    var p = new PlateInfo();
                    p.region = e.getKey();
                    p.count = e.getValue();
                    p.percent = e.getValue() * 100.0f / plates.size();
                    return p;
                })
                .sorted((a,b)->Long.compare(b.count, a.count))
                .collect(Collectors.toList());

         /*plates.stream()
                .map(Plate::getRegion)
                .collect(Collectors.groupingBy(e->e, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * 100.0f / plates.size()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e-> divide.add(e.getValue()));*/

        var first = histogram
                .stream()
                .limit(5).collect(Collectors.toList());
        first.forEach(e ->
                sb.append(String.format("\n%s - %.2f%% (%d шт)", e.region, e.percent, e.count)));

        if(histogram.size()>5) {
            var second = histogram
                    .stream()
                    .skip(first.size())
                    .collect(Collectors.toList())
                    ;

            sb.append(String.format("\nдругие - %.2f%% (%d шт)",
                    second.stream().map(e-> e.percent)
                            .reduce(0.0f, Float::sum ),
                    second.stream().mapToLong(e -> e.count).sum()
                    ));

        }

                //.collect(Collectors.toList())
                /*.forEach(e ->
                        sb.append(String.format("\n%s - %d шт", e.getKey(), e.getValue())));*/




    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupPieChart(ArrayList<Float> divide){

        //populating a list

        List <PieEntry> pieEntries = new ArrayList<>();
        //ArrayList<Float> divide
        for (int c = 0; c < divide.size(); c++){
            pieEntries.add(new PieEntry(divide.get(c), ""));
        }

        var colors = new ArrayList<Integer>();
        for (int v : ColorTemplate.JOYFUL_COLORS
             ) {
            colors.add(v);
        }
        colors.add(Color.rgb(118, 84, 154));




        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);

        //get the chart

        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setData(data);
        data.setDrawValues(true);
        pieChart.setUsePercentValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextColor((Color.rgb(204,204,204)));
        data.setValueTextSize(13f);
        pieChart.animateXY(600,600);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.rgb(51,51,51));
        pieChart.setTransparentCircleColor((Color.rgb(51,51,51)));
        pieChart.setDescription(null);



        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        pieChart.invalidate();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void plateArray (ArrayList<Float> divide){


        var histogram = plates.stream()
                .map(Plate::getRegion)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * 100.0f / plates.size()
                )).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());



        var first = histogram
                .stream()
                .limit(5).collect(Collectors.toList());
        first.forEach(e-> divide.add(e.getValue()));



        if(histogram.size()>5) {

            var second = histogram
                    .stream()
                    .skip(first.size())
                    .map(Map.Entry::getValue)
                    .reduce(0.0f, Float::sum);
            divide.add(second);
        }

        /*plates.stream()
                .map(Plate::getRegion)
                .collect(Collectors.groupingBy(e->e, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * 100.0f / plates.size()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e-> divide.add(e.getValue()));*/
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(plates);
        editor.putString("plates", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("plates", null);
        Type type = new TypeToken<ArrayList<Plate>>() {}.getType();
        plates = gson.fromJson(json,type);

        if (plates == null) {
            plates = new ArrayList<>();
        }
    }

}