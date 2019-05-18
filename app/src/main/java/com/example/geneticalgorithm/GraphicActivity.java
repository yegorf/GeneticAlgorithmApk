package com.example.geneticalgorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
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

        double x;
        double y;

        x = 0;

        GraphView graph = findViewById(R.id.graph);
        series = new LineGraphSeries<>();
        for(int i=0; i<20; i++) {
            x += 1;
            y = Math.pow(x,4) - 250;
            series.appendData(new DataPoint(x,y), true, 500);
        }
        graph.addSeries(series);

        Intent intent  = getIntent();
        Double X = Double.valueOf(intent.getStringExtra("x"));
        Double Y = Double.valueOf(intent.getStringExtra("y"));

        points = new PointsGraphSeries<>();
        points.appendData(new DataPoint(0,Y), true, 500);
        points.appendData(new DataPoint(X,0), true, 500);
        points.appendData(new DataPoint(X,Y), true, 500);
        graph.addSeries(points);

    }
}
