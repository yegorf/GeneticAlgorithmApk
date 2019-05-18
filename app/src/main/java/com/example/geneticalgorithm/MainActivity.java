package com.example.geneticalgorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.geneticalgorithm.logic.Genetic;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    Button button;
    EditText editSize;
    EditText editCount;
    EditText edicKras;
    EditText editMutation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editSize = findViewById(R.id.editText);
        editCount = findViewById(R.id.editText2);
        edicKras = findViewById(R.id.editText3);
        editMutation = findViewById(R.id.editText4);

        button.setOnClickListener(e -> {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("size", editSize.getText());
            intent.putExtra("count", editCount.getText());
            intent.putExtra("kras", edicKras.getText());
            intent.putExtra("mutation", editMutation.getText());
            startActivity(intent);
        });
    }
}
