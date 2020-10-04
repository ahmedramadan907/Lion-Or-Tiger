package com.example.lionortiger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private HashMap<Integer, String> choice = new HashMap<>();
    private ArrayList<Integer> arrayTapped = new ArrayList<>();
    private ArrayList<Integer> antiConflict = new ArrayList<>();


    enum player {ONE, TWO}

    private player CurrentPlayer = player.ONE;
    private boolean counter = true;
    private int tag_num , scoreLion = 0, scoretiger = 0;

    private TextView txt , txtScore;
    private Button btn , btnReset;
    private ImageView tappedimage, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView2);
        txtScore = findViewById(R.id.textView3);
        btn = findViewById(R.id.button);
        btnReset = findViewById(R.id.button2);

        choice.clear();
        antiConflict.clear();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Again();
            }
        });
        txtScore.setText("Lion : 0 \n Tiger : 0");

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void imageClicked(final View ImageView) {

        counter = true;
        tappedimage = (ImageView) ImageView;


        for (int i = 0; i <= choice.size(); i++) {

            if (choice.get(Integer.parseInt(tappedimage.getTag().toString())) != null) {
                if (Objects.equals(choice.get(Integer.parseInt(tappedimage.getTag().toString())), "tiger") || Objects.equals(choice.get(Integer.parseInt(tappedimage.getTag().toString())), "lion")) {
                    for (int key : choice.keySet()) {

                        if (Integer.parseInt(tappedimage.getTag().toString()) == key) {
                            Toast.makeText(MainActivity.this, "You cannot", Toast.LENGTH_SHORT).show();
                            counter = false;
                            break;
                        }
                    }

                }
            }

        }


        if (counter) {

            if (CurrentPlayer == player.ONE) {
                tappedimage.setTranslationX(3000);
                tappedimage.animate().alpha(1).translationXBy(-3000).rotation(1800).setDuration(500);
                tappedimage.setImageResource(R.drawable.tiger);
                CurrentPlayer = player.TWO;
                int tag = Integer.parseInt(tappedimage.getTag().toString());
                choice.put(tag, "tiger");

            } else if (CurrentPlayer == player.TWO) {
                tappedimage.setTranslationX(3000);
                tappedimage.animate().alpha(1).translationXBy(-3000).rotation(1800).setDuration(500);
                tappedimage.setImageResource(R.drawable.lion);
                CurrentPlayer = player.ONE;
                int tag = Integer.parseInt(tappedimage.getTag().toString());
                choice.put(tag, "lion");

            }
        }


        CheckWinner();

        tag_num = tappedimage.getId();
        arrayTapped.add(tag_num);
        antiConflict.add(Integer.parseInt(tappedimage.getTag().toString()));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void CheckWinner() {


        //ROW WINNING

        for (int i = 0; i <= 6; i += 3) {
            if (choice.get(i) != null || choice.get(i + 1) != null || choice.get(i + 2) != null) {
                if (Objects.equals(choice.get(i), choice.get(i + 1)) && Objects.equals(choice.get(i + 1), choice.get(i + 2))) {
                    txt.setText(choice.get(i) + " is the winner !!");
                    counter = false;
                    if(choice.get(i) == "lion"){
                        scoreLion ++ ;
                        txtScore.setText("Lion : " + String.valueOf(scoreLion));
                    }else if(choice.get(i) == "tiger"){
                        scoretiger ++;
                        txtScore.setText("Tiger : " + String.valueOf(scoretiger));
                    }
                    break;


                }
            }
        }

        // COLUMN WINNING

        for (int i = 0; i <= 2; i++) {
            if (choice.get(i) != null || choice.get(i + 3) != null || choice.get(i + 6) != null) {
                if (Objects.equals(choice.get(i), choice.get(i + 3)) && Objects.equals(choice.get(i + 3), choice.get(i + 6))) {
                    txt.setText(choice.get(i) + " is the winner !!");
                    counter = false;

                    if(choice.get(i) == "lion"){
                        scoreLion ++ ;
                        txtScore.setText("Lion : " + String.valueOf(scoreLion));
                    }else if(choice.get(i) == "tiger"){
                        scoretiger ++;
                        txtScore.setText("Tiger : " + String.valueOf(scoretiger));
                    }
                    break;
                }
            }
        }

        // CROSS Winning

        int i = 0, j = 2;

        if (choice.get(i) != null || choice.get(i + 4) != null || choice.get(i + 8) != null) {
            if (Objects.equals(choice.get(i), choice.get(i + 4)) && Objects.equals(choice.get(i + 4), choice.get(i + 8))) {
                txt.setText(choice.get(i) + " is the winner !!");
                counter = false;

                if(choice.get(i) == "lion"){
                    scoreLion ++ ;
                    txtScore.setText("Lion : " + String.valueOf(scoreLion));
                }else if(choice.get(i) == "tiger"){
                    scoretiger ++;
                    txtScore.setText("Tiger : " + String.valueOf(scoretiger));
                }
            }
        }

        if (choice.get(j) != null || choice.get(j + 2) != null || choice.get(j + 4) != null) {
            if (Objects.equals(choice.get(j), choice.get(j + 2)) && Objects.equals(choice.get(j + 2), choice.get(j + 4))) {
                txt.setText(choice.get(j) + " is the winner !!");
                counter = false;

                if(choice.get(j) == "lion"){
                    scoreLion ++ ;
                    txtScore.setText("Lion : " + String.valueOf(scoreLion));
                }else if(choice.get(j) == "tiger"){
                    scoretiger ++;
                    txtScore.setText("Tiger : " + String.valueOf(scoretiger));
                }
            }
        }

    }

    private void Reset() {

        for (int i = 0; i <= arrayTapped.size() - 1; i++) {

            img = findViewById(arrayTapped.get(i));

            img.animate().alpha(0f);

        }

        arrayTapped.clear();
        txt.setText("");
        choice.clear();
        counter = true;
        CurrentPlayer = player.ONE;
        scoretiger = 0 ;
        scoreLion = 0 ;
        txtScore.setText("Lion : 0\nTiger : 0");

    }

    private void Again() {

        for (int i = 0; i <= arrayTapped.size() - 1; i++) {

            img = findViewById(arrayTapped.get(i));

            img.animate().alpha(0f);

        }

        arrayTapped.clear();
        txt.setText("");
        choice.clear();
        counter = true;
        CurrentPlayer = player.ONE;

        txtScore.setText("Lion : "+scoreLion +"\nTiger : " +scoretiger);


    }


}









