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
    private int tag_num;

    private TextView txt;
    private Button btn;
    private ImageView tappedimage, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView2);
        btn = findViewById(R.id.button);

        choice.clear();
        antiConflict.clear();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void imageClicked(final View ImageView) {

        tappedimage = (ImageView) ImageView;



            if (counter) {

                if (CurrentPlayer == player.ONE) {
                    tappedimage.setTranslationX(2000);
                    tappedimage.animate().alpha(1).translationXBy(-2000).rotation(3600).setDuration(2000);
                    tappedimage.setImageResource(R.drawable.tiger);
                    CurrentPlayer = player.TWO;
                    int tag = Integer.parseInt(tappedimage.getTag().toString());
                    choice.put(tag, "tiger");

                } else if (CurrentPlayer == player.TWO) {
                    tappedimage.setTranslationX(2000);
                    tappedimage.animate().alpha(1).translationXBy(-2000).rotation(3600).setDuration(2000);
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

            }
        }

        if (choice.get(j) != null || choice.get(j + 2) != null || choice.get(j + 4) != null) {
            if (Objects.equals(choice.get(j), choice.get(j + 2)) && Objects.equals(choice.get(j + 2), choice.get(j + 4))) {
                txt.setText(choice.get(j) + " is the winner !!");
                counter = false;

            }
        }

    }

    private void Reset() {

        for (int i = 0; i <= arrayTapped.size() - 1; i++) {

            img = findViewById(arrayTapped.get(i));
            Log.i("tag", "Reset: " + arrayTapped.get(i).toString());

            img.animate().alpha(0f);

        }

        arrayTapped.clear();
        txt.setText("");
        choice.clear();
        counter = true;
        CurrentPlayer = player.ONE;
    }
}








