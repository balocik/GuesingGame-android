package com.example.wojciechkuczer.guessinggame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtGuess;
    private Button btnGuess;
    private Button btnNewGame;
    private TextView lblOutput;
    private int theNumber;
    private int numberOfTries = 7;

    public void checkGuess() {
        String guessText = txtGuess.getText().toString();
        String message = "";
        // check user guess

        try {

            int guess = Integer.parseInt(guessText);

            if (guess > theNumber) {
                message = guess + " was too high.You have " + --numberOfTries + " guesies left";
                lblOutput.setText(message);
            } else if (guess < theNumber) {
                message = guess + " was too low. You have " + --numberOfTries + " guesies left";
                lblOutput.setText(message);
            } else {
                message = guess + " is correct. You are a WINNER";
                txtGuess.setText("WINNER");
                lblOutput.setText(message);

            }


        } catch (Exception e) {
            lblOutput.setText("Enter whole number between 1 and 30");
        } finally {
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
        if(numberOfTries == 0) {
            txtGuess.setText("LOST");
            txtGuess.selectAll();
            lblOutput.setText("The number was " + theNumber);
            numberOfTries = 7;
        }
    }

    public void newGame() {
        theNumber = (int) (Math.random() * 30 + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        newGame();
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                numberOfTries = 7;
                txtGuess.setText("START");
                txtGuess.requestFocus();
                txtGuess.selectAll();
                lblOutput.setText("You have " + numberOfTries + " guessies left");
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
