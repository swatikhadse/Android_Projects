package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText workingTextView; //to enter value
    TextView resultTextView; //to display result

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);
        workingTextView = findViewById(R.id.workingsTextView);
        workingTextView.setShowSoftInputOnFocus(false);
    }

    private void update(String value) {
        String oldString = workingTextView.getText().toString();
        /*getSelectionStart() ---> Return the offset of the selection anchor or cursor,
         or -1 if there is no selection or cursor.*/
        int cursorPos = workingTextView.getSelectionStart();
        String leftString = oldString.substring(0, cursorPos);
        String rightString = oldString.substring(cursorPos);
            workingTextView.setText(String.format("%s%s%s", leftString, value, rightString));
            workingTextView.setSelection(cursorPos + 1);

    }


    public void equalsOnClick(View view) {
        String userInput = workingTextView.getText().toString();
        Expression expression = new Expression(userInput);

        String result = String.valueOf(expression.calculate());

        //workingTextView.setText(result);
        resultTextView.setText(result);
        workingTextView.setSelection(userInput.length());

    }


    public void clearOnClick(View view) {
        workingTextView.setText("");
        resultTextView.setText("");
    }

    public void powerOfOnClick(View view) {
        update("^");
    }

    public void divisionOnclick(View view) {
        update("/");
    }

    public void multiplyOnClick(View view) {
        update("*");

    }

    public void subtractOnClick(View view) {
        update("-");

    }

    public void additionOnClick(View view) {
        update("+");

    }


    public void dotOnClick(View view) {
        update(".");

    }

    public void zeroOnClick(View view) {
        update("0");

    }

    public void threeOnClick(View view) {
        update("3");

    }

    public void twoOnClick(View view) {
        update("2");

    }

    public void oneOnClick(View view) {
        update("1");
    }

    public void sixOnClick(View view) {
        update("6");

    }

    public void fiveOnClick(View view) {
        update("5");

    }

    public void fourOnClick(View view) {
        update("4");

    }

    public void nineOnClick(View view) {
        update("9");

    }

    public void eightOnClick(View view) {
        update("8");

    }

    public void sevenOnClick(View view) {
        update("7");

    }

    public void bracketsOnClick(View view) {
        int cursor_pos = workingTextView.getSelectionStart();
        int openPar = 0; //to track count of "("
        int closePar = 0; //to track count of ")"
        int valueLen = workingTextView.getText().length();

        for (int i = 0; i < cursor_pos; i++) {
            //to check "(" from index i to i+1 (substring takes index 0 to  0+1 values)
            //(8+9)*7
            if (workingTextView.getText().toString().substring(i, i + 1).equals("(")) {
                openPar++;
            }
            if (workingTextView.getText().toString().substring(i, i + 1).equals(")")) {
                closePar++;
            }
        }
        if (openPar == closePar || workingTextView.getText().toString().substring(valueLen - 1, valueLen).equals("(")) {
            update("(");
        } else if (closePar < openPar && !workingTextView.getText().toString().substring(valueLen - 1, valueLen).equals("(")) {
            update(")");
        }

        //increase cursor position by 1
        workingTextView.setSelection(cursor_pos + 1);
    }


    public void backSpaceOnClick(View view) {
        int cursor_pos = workingTextView.getSelectionStart();
        int string_len = workingTextView.getText().length();
        if (cursor_pos != 0 && string_len != 0) {
            //Replace various characters in string
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) workingTextView.getText();
            spannableStringBuilder.replace(cursor_pos - 1, cursor_pos, "");
            workingTextView.setText(spannableStringBuilder);
            workingTextView.setSelection(cursor_pos - 1);
            resultTextView.setText("");
        }
    }
}