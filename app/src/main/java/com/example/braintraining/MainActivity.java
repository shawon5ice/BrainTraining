package com.example.braintraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int cposition,correctans = 0,totalquestion =0;
    private Button go,zero,one,two,three,playagain;
    private TextView countdown,pass,status,equation;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown = findViewById(R.id.countdown);
        pass = findViewById(R.id.pass);
        equation = findViewById(R.id.equation);
        status = findViewById(R.id.status);
        zero = findViewById(R.id.zero);
        one= findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        constraintLayout = findViewById(R.id.constraintLayout);
        playagain = findViewById(R.id.playAgain);
        getSupportActionBar().hide();
        constraintLayout.setVisibility(View.INVISIBLE);
    }

    public void go(View view){
        findViewById(R.id.go).setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        playAgain(view);
    }
    public void game(){
        ArrayList<Integer> answers = new ArrayList<Integer>();
        Random random = new Random();
        int val1 = random.nextInt(49);
        int val2 = random.nextInt(49);
        cposition = random.nextInt(4);
        int total = val1+val2;
        equation.setText(String.valueOf(val1)+" + "+String.valueOf(val2));
        for(int i=0;i<4;i++){
            int x = random.nextInt(99);
            if(i==cposition){
                answers.add(total);
            }else {
                while (x==total){
                    x = random.nextInt(99);
                }
                answers.add(x);
            }
        }

        zero.setText(String.valueOf(answers.get(0)));
        one.setText(String.valueOf(answers.get(1)));
        two.setText(String.valueOf(answers.get(2)));
        three.setText(String.valueOf(answers.get(3)));
    }
    public void checkGame(View view){
        String ans = "";
        if(view.getTag().toString().equals(String.valueOf(cposition))){
            ans = "Correct :)";
            correctans++;
        }else{
            ans = "Wrong :(";
        }
        totalquestion++;
        status.setText(ans);
        status.setVisibility(View.VISIBLE);
        pass.setText(String.valueOf(correctans)+"/"+String.valueOf(totalquestion));
        game();
    }

    public void countDown(){
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                countdown.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }
            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                status.setText("Done!");
            }
        }.start();
    }
    public void playAgain(View view){
        totalquestion = 0;
        correctans = 0;
        pass.setText(String.valueOf(correctans)+"/"+String.valueOf(totalquestion));
        playagain.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);
        countDown();
        game();
    }
}