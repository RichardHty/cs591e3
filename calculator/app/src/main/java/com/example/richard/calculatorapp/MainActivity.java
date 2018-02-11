package com.example.richard.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnAdd;
    private Button btnMultiply;
    private Button btnMinus;
    private Button btnDivide;
    private Button btnSqrt;
    private Button btnEqual;
    private Button btnDot;
    private Button btnClr;

    private TextView displayScreen;

    private String currentText = "0";
    private String temp;
    private boolean initial = true;
    private boolean calc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnSqrt = (Button) findViewById(R.id.btnSqrt);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnClr = (Button) findViewById(R.id.btnClr);
        btnMinus = (Button) findViewById(R.id.btnMinus);

        displayScreen = (TextView) findViewById(R.id.displayScreen);

        btn0.setTag(constants.BTN_ZERO);
        btn1.setTag(constants.BTN_ONE);
        btn2.setTag(constants.BTN_TWO);
        btn3.setTag(constants.BTN_THREE);
        btn4.setTag(constants.BTN_FOUR);
        btn5.setTag(constants.BTN_FIVE);
        btn6.setTag(constants.BTN_SIX);
        btn7.setTag(constants.BTN_SEVEN);
        btn8.setTag(constants.BTN_EIGHT);
        btn9.setTag(constants.BTN_NINE);
        btnDot.setTag(constants.BTN_DOT);

        btnAdd.setTag(constants.BTN_PLUS);
        btnSqrt.setTag(constants.BTN_SQRT);
        btnMultiply.setTag(constants.BTN_MULTIPLY);
        btnDivide.setTag(constants.BTN_DIVIDE);
        btnMinus.setTag(constants.BTN_MINUS);
        btnEqual.setTag(constants.BTN_EQUAL);

        btnClr.setTag(constants.BTN_CLEAR);

        /* add in event after calculation: if a number is pressed and not an operator, input bar is cleared
        *  not that the new number is added to the end of the old calculation
        *  - updated by sara 2/5/18
        */



        View.OnClickListener textListener = new View.OnClickListener() {
            public void onClick(View view) {
                int tag = (Integer) view.getTag();


                switch (tag) {
                    case constants.BTN_ZERO:
                        if (!initial && !calc)
                            currentText += constants.CHAR_ZERO;
                        else {
                            currentText = Character.toString(constants.CHAR_ZERO);
                            initial = false;
                        }
                        break;
                    case constants.BTN_ONE:
                        if (!initial && !calc)
                            currentText += constants.CHAR_ONE;
                        else {
                            currentText = Character.toString(constants.CHAR_ONE);
                            initial = false;
                        }
                        break;
                    case constants.BTN_TWO:
                        if (!initial && !calc)
                            currentText += constants.CHAR_TWO;
                        else {
                            currentText = Character.toString(constants.CHAR_TWO);
                            initial = false;
                        }
                        break;
                    case constants.BTN_THREE:
                        if (!initial && !calc)
                            currentText += constants.CHAR_THREE;
                        else {
                            currentText = Character.toString(constants.CHAR_THREE);
                            initial = false;
                        }
                        break;
                    case constants.BTN_FOUR:
                        if (!initial && !calc)
                            currentText += constants.CHAR_FOUR;
                        else {
                            currentText = Character.toString(constants.CHAR_FOUR);
                            initial = false;
                        }
                        break;
                    case constants.BTN_FIVE:
                        if (!initial && !calc)
                            currentText += constants.CHAR_FIVE;
                        else {
                            currentText = Character.toString(constants.CHAR_FIVE);
                            initial = false;
                        }
                        break;
                    case constants.BTN_SIX:
                        if (!initial && !calc)
                            currentText += constants.CHAR_SIX;
                        else {
                            currentText = Character.toString(constants.CHAR_SIX);
                            initial = false;
                        }
                        break;
                    case constants.BTN_SEVEN:
                        if (!initial && !calc)
                            currentText += constants.CHAR_SEVEN;
                        else {
                            currentText = Character.toString(constants.CHAR_SEVEN);
                            initial = false;
                        }
                        break;
                    case constants.BTN_EIGHT:
                        if (!initial && !calc)
                            currentText += constants.CHAR_EIGHT;
                        else {
                            currentText = Character.toString(constants.CHAR_EIGHT);
                            initial = false;
                        }
                        break;
                    case constants.BTN_NINE:
                        if (!initial && !calc)
                            currentText += constants.CHAR_NINE;
                        else {
                            currentText = Character.toString(constants.CHAR_NINE);
                            initial = false;
                        }
                        break;
                    case constants.BTN_DOT:
                        currentText += constants.CHAR_DOT;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_PLUS:
                        currentText += constants.CHAR_PLUS;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_MINUS:
                        currentText += constants.CHAR_MINUS;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_MULTIPLY:
                        currentText += constants.CHAR_MULTIPLY;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_DIVIDE:
                        currentText += constants.CHAR_DIVIDE;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_SQRT:
                        temp = currentText.substring(currentText.length()-1);
                        if (!isOperator(temp.charAt(0)))
                            currentText = currentText+constants.CHAR_MULTIPLY+constants.CHAR_SQRT;
                        else
                            currentText += constants.CHAR_SQRT;
                        initial = false;
                        calc = false;
                        break;
                    case constants.BTN_CLEAR:
                        currentText = Character.toString(constants.CHAR_ZERO);
                        initial = true;
                        calc = false;
                        break;
                    case constants.BTN_EQUAL:
                        Log.e("Testing","Here is currentString: "+currentText);
                        temp = currentText.substring(0,1);
                        Log.e("Testing","Here is currentString: "+temp);
                        if(temp.charAt(0) == constants.CHAR_MINUS)
                            currentText = "0" + currentText;
                        Log.e("Testing","Here is currentString: "+currentText);
                        Calculation cal = new Calculation(currentText);
                        cal.calculate();
                        calc = true;
                        currentText = cal.getResult();

                        break;
                    default:break;
                }
                displayScreen.setText(currentText);

            }
        };

        btn0.setOnClickListener(textListener);
        btn1.setOnClickListener(textListener);
        btn2.setOnClickListener(textListener);
        btn3.setOnClickListener(textListener);
        btn4.setOnClickListener(textListener);
        btn5.setOnClickListener(textListener);
        btn6.setOnClickListener(textListener);
        btn7.setOnClickListener(textListener);
        btn8.setOnClickListener(textListener);
        btn9.setOnClickListener(textListener);
        btnDot.setOnClickListener(textListener);

        btnAdd.setOnClickListener(textListener);
        btnMultiply.setOnClickListener(textListener);
        btnDivide.setOnClickListener(textListener);
        btnEqual.setOnClickListener(textListener);
        btnSqrt.setOnClickListener(textListener);
        btnMinus.setOnClickListener(textListener);

        btnClr.setOnClickListener(textListener);
    }

    private boolean isOperator(char c){
        boolean flag = false;
        switch (c){
            case constants.CHAR_PLUS:
            case constants.CHAR_MINUS:
            case constants.CHAR_MULTIPLY:
            case constants.CHAR_DIVIDE:
                flag = true;
                break;
        }
        return flag;
    }
}
