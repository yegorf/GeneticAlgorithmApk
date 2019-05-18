package com.example.geneticalgorithm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.geneticalgorithm.logic.Genetic;
import com.example.geneticalgorithm.tools.Chromosome;

public class ResultActivity extends AppCompatActivity {

    TextView best;
    TextView value;
    TextView function;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        best = findViewById(R.id.best);
        value = findViewById(R.id.value);
        function = findViewById(R.id.function);
        button = findViewById(R.id.graphicButton);

//        Intent intent  = getIntent();
//        Integer size = Integer.valueOf(intent.getStringExtra("size"));
//        Integer count = Integer.valueOf(intent.getStringExtra("count"));
//        Double kras = Double.valueOf(intent.getStringExtra("kras"));
//        Double mutation = Double.valueOf(intent.getStringExtra("mutation"));

        Genetic genetic = new Genetic();
        Chromosome bestChromosome = genetic.evolution();

        best.setText(bestChromosome.getBinary());
        value.setText(bestChromosome.getDecimal().toString());
        function.setText(bestChromosome.getFunctionValue().toString());

        button.setOnClickListener(e -> {
            Intent intent = new Intent(this, GraphicActivity.class);
            intent.putExtra("x", bestChromosome.getDecimal().toString());
            intent.putExtra("y", bestChromosome.getFunctionValue().toString());
            startActivity(intent);
        });
    }
}