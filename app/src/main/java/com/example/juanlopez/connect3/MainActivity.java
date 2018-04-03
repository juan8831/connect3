package com.example.juanlopez.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    //0 for circle, 1 for x
    int currentPlayer = 0;

    public void onGridClick(View view){

        ImageView imgView = (ImageView)view;
        String name = getResources().getResourceEntryName(view.getId());
        int num = Integer.parseInt(name.substring(3));

        Log.i("numClicked", Integer.toString(num));

        if(gameState[num-1] == -1)
        {
            if(currentPlayer == 0)
            {
                imgView.setImageResource(R.drawable.circle);
                gameState[num-1] = currentPlayer;

            }
            else
            {
                imgView.setImageResource(R.drawable.x);
                gameState[num-1] = currentPlayer;
            }

            if (gameIsOver())
            {
                Toast.makeText(getApplicationContext(), "Player " + currentPlayer + " Won!" , Toast.LENGTH_LONG).show();
                TextView resultText = (TextView) findViewById(R.id.resultText);
                resultText.setText("Player " + currentPlayer + " Won!");

            }

            //refactor this
            if(currentPlayer == 0)
            {

                currentPlayer = 1;

            }
            else
            {
                currentPlayer = 0;
            }


        }

    }

    public void resetGrid(View view)
    {
        RelativeLayout mainLayout = findViewById(R.id.mainGrid);
        int numOfChildren = mainLayout.getChildCount();
        for(int i = 0; i < numOfChildren; i++)
        {
            View currentView = mainLayout.getChildAt(i);
            if(getResources().getResourceEntryName(currentView.getId()).startsWith("btn"))
            {
                ((ImageView)currentView).setImageResource(0);
            }


        }

        for(int i =0; i <gameState.length; i++)
        {
            gameState[i] = -1;
        }
        TextView resultText = (TextView) findViewById(R.id.resultText);
        resultText.setText("");
    }

    public boolean gameIsOver()
    {

        int [][] winnableStates =
                {
                        {1,2,3},{4,5,6},{7,8,9},
                        {1,4,7},{2,5,8},{3,6,9},
                        {1,5,9},{3,5,7}
                };

        for(int [] state: winnableStates)
        {
            if(gameState[state[0]-1] == gameState[state[1]-1]
                    && gameState[state[1]-1] == gameState[state[2]-1]
                    && gameState[state[0]-1] != -1)
            {
                return true;
            }
        }
        return false;


    }
}
