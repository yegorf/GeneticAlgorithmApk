package com.example.geneticalgorithm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.geneticalgorithm.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class GraphicActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    PointsGraphSeries<DataPoint> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        double x = 0;
        double y;

        GraphView graph = findViewById(R.id.graph);
        series = new LineGraphSeries<>();
        for (int i = 0; i < 20; i++) {
            x += 1;
            y = Math.pow(x, 4) - 250;
            series.appendData(new DataPoint(x, y), true, 500);
        }
        graph.addSeries(series);

        Intent intent = getIntent();
        double X = Double.parseDouble(intent.getStringExtra("x"));
        double Y = Double.parseDouble(intent.getStringExtra("y"));

        points = new PointsGraphSeries<>();
        points.appendData(new DataPoint(0, Y), true, 500);
        points.appendData(new DataPoint(X, 0), true, 500);
        points.appendData(new DataPoint(X, Y), true, 500);
        graph.addSeries(points);
    }
}
