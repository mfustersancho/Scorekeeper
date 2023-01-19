package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_SCORE_1 = "state_score_1";
    private static final String STATE_SCORE_2 = "state_score_2";
    private int mScoreTeam1;
    private int mScoreTeam2;

    private TextView mScoreViewTeam1;
    private TextView mScoreViewTeam2;

    ImageButton mBtnMinusTeam1;
    ImageButton mBtnPlusTeam1;
    ImageButton mBtnMinusTeam2;
    ImageButton mBtnPlusTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            mScoreTeam1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScoreTeam2 = savedInstanceState.getInt(STATE_SCORE_2);
        }

        mScoreViewTeam1 = findViewById(R.id.txt_score_team_1);
        mScoreViewTeam2 = findViewById(R.id.txt_score_team_2);

        mScoreViewTeam1.setText(String.valueOf(mScoreTeam1));
        mScoreViewTeam2.setText(String.valueOf(mScoreTeam2));

        mBtnMinusTeam1 = findViewById(R.id.btn_minus_team_1);
        mBtnPlusTeam1 = findViewById(R.id.btn_plus_team_1);
        mBtnMinusTeam2 = findViewById(R.id.btn_minus_team_2);
        mBtnPlusTeam2 = findViewById(R.id.btn_plus_team_2);

        mBtnMinusTeam1.setOnClickListener(v -> decreaseScore(v));
        mBtnPlusTeam1.setOnClickListener(v -> increaseScore(v));
        mBtnMinusTeam2.setOnClickListener(v -> decreaseScore(v));
        mBtnPlusTeam2.setOnClickListener(v -> increaseScore(v));

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_SCORE_1, mScoreTeam1);
        outState.putInt(STATE_SCORE_2, mScoreTeam2);
        super.onSaveInstanceState(outState);
    }

    private void increaseScore(View v) {
        if(v == mBtnPlusTeam1) {
            mScoreTeam1++;
            mScoreViewTeam1.setText(String.valueOf(mScoreTeam1));
        } else if(v == mBtnPlusTeam2) {
            mScoreTeam2++;
            mScoreViewTeam2.setText(String.valueOf(mScoreTeam2));
        }
    }

    private void decreaseScore(View v) {
        if(v == mBtnMinusTeam1) {
            mScoreTeam1--;
            if(mScoreTeam1 < 0)
                mScoreTeam1 = 0;
            mScoreViewTeam1.setText(String.valueOf(mScoreTeam1));
        } else if(v == mBtnMinusTeam2) {
            mScoreTeam2--;
            if(mScoreTeam2 < 0)
                mScoreTeam2 = 0;
            mScoreViewTeam2.setText(String.valueOf(mScoreTeam2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_NO));
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        recreate();
        return true;
    }
}