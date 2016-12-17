package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        LineChart chart = (LineChart) findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();
        Intent intent=getIntent();
        String history = intent.getStringExtra("history");
        String symbol=intent.getStringExtra("symbol");

        chart.animateXY(1000, 1000);
        chart.invalidate();


        List<History> historyArrayList = parsing(history);

        ArrayList<String> date = new ArrayList<>();
        for (int i = 0; i < historyArrayList.size(); i++) {
            float price = Float.parseFloat(String.valueOf(historyArrayList.get(i).getPrice()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(historyArrayList.get(i).getDate());

            SimpleDateFormat format1 = new SimpleDateFormat("yy-MM");
            date.add(format1.format(calendar.getTime()));


            entries.add(new Entry(price, i));


        }

        LineDataSet dataSet = new LineDataSet(entries, symbol);
        LineData lineData = new LineData(date, dataSet);
        chart.setData(lineData);

    }

    public List<History> parsing(String history) {
        List<History> historyArrayList = new ArrayList<>();
        String[] historyArray = history.split("\n");
        String[] temporaryArray;
        for (int i = historyArray.length - 1; i >= 0; i--) {
            temporaryArray = historyArray[i].split(",");
            historyArrayList.add(new History(Long.parseLong(temporaryArray[0]), Double.parseDouble(temporaryArray[1])));


        }

        return historyArrayList;
    }

}


