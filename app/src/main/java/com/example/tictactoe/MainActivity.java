package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;

    private int pl1_points;
    private int pl2_points;

    private TextView pl1_TV;
    private TextView pl2_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pl1_TV = findViewById(R.id.P1TV);
        pl2_TV = findViewById(R.id.P2TV);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String btnID = "btn_" + i + j;
                int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset = findViewById(R.id.resetBTN);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(player1Turn)
        {
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("O");
        }
        roundCount++;
        if(checkForWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2win();

            }
        }else if(roundCount==9)
        {
            roundCount=0;
            draw();
        }
        else
        {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin()
    {
        String[][] field = new String[3][3];

        for(int i =0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                        && !field[i][0].equals(""))
            {
                return true;
            }
        }

        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals(""))
            {
                return true;
            }
        }


        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals(""))
        {
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals(""))
        {
            return true;
        }
        return false;

    }

    private void player1Wins()
    {
        pl1_points++;
        Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
        updatepoints();
        resetboard();

    }
    private void player2win()
    {

        pl2_points++;
        Toast.makeText(this,"Player 2 wins",Toast.LENGTH_SHORT).show();
        updatepoints();
        resetboard();
    }
    private  void  draw()
    {
        Toast.makeText(this,"Match is Draw",Toast.LENGTH_SHORT).show();
        resetboard();

    }
    private  void resetboard()
    {
        roundCount=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
    }
    private void updatepoints()
    {
        pl1_TV.setText("Player 1 "+pl1_points);
        pl2_TV.setText("Player 2 "+pl2_points);
    }


}
