package com.example.richard.calculatorapp;

import android.util.Log;

import java.util.Stack;

/**
 * Created by richard on 2/4/18.
 */

public class Calculation {
    private Stack<Double> valueStack;
    private Stack<Character> operatorStack;
    private String text;
    private String result;

    public Calculation(String txt){
        valueStack = new Stack<>();
        operatorStack = new Stack<>();
        text = constants.CHAR_BEGIN_SIGN + txt + constants.CHAR_END_SIGN;
        result = "";
    }

    private void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void calculate(){
        boolean dotMode = false;
        boolean numMode = false;

        String floatNum = "";
        String longNum = "";

        if (text == null || text.isEmpty()){
            setResult("ERROR");
            return;
        }
        int textLeng = text.length();

        for (int i=0;i<textLeng;i++){
            char c = text.charAt(i);

            if (isNumber(c)){
                numMode = true;
                if (dotMode) {
                    numMode = false;
                    floatNum += c;
                }
                if (numMode)
                    longNum += c;

            }else {
                if (numMode){
                    numMode = false;
                    double num = Double.valueOf(longNum);
                    valueStack.push(num);
                    longNum = "";
                }
                if (dotMode){
                    dotMode = false;
                    double num = Double.valueOf(floatNum);
                    valueStack.push(num);
                    floatNum = "";
                }
                if (c == constants.CHAR_DOT){
                    dotMode = true;
                    if (!valueStack.isEmpty()) {
                        double temp = valueStack.pop();
                        floatNum = Integer.toString((int) temp) + constants.CHAR_DOT;
                    }else{
                        floatNum = constants.CHAR_ZERO + ".";
                    }
                }
                else if (!comparePriority(c)) {
                    oneStepCalculate();
                    if (!operatorStack.isEmpty()) {
                        char ch = operatorStack.peek();
                        while (ch != constants.CHAR_DOT && ch!=constants.CHAR_BEGIN_SIGN && !comparePriority(ch, c)) {
                            oneStepCalculate();
                            if (!operatorStack.isEmpty())
                                ch = operatorStack.peek();
                            else break;
                        }
                    }
                    operatorStack.push(c);
                }else{
                    operatorStack.push(c);
                }
            }
        }

        if (valueStack.size() == 1 ){
            double re = precisionRound(valueStack.pop(),10);
            setResult(Double.toString(re));
        }else{
            setResult("ERROR.");
        }
    }
    private void oneStepCalculate(){

        char topOperator = operatorStack.pop();
        if (topOperator == constants.CHAR_BEGIN_SIGN)
            return;
        if (valueStack.isEmpty()){
            return;
        }
        double firstParameter = valueStack.pop();
        double secondParameter ;
        double result = 0;
        int errorFlag = -1;
        switch (topOperator){
            case constants.CHAR_PLUS:
                secondParameter = valueStack.pop();
                result = firstParameter + secondParameter;
                break;
            case constants.CHAR_MINUS:
                secondParameter = valueStack.pop();
                result = secondParameter - firstParameter;
                break;
            case constants.CHAR_MULTIPLY:
                secondParameter = valueStack.pop();
                result = firstParameter * secondParameter;
                break;
            case constants.CHAR_DIVIDE:
                secondParameter = valueStack.pop();
                if (firstParameter == 0)
                    errorFlag = 1;
                else
                    result = secondParameter/ firstParameter  ;
                break;
            case constants.CHAR_SQRT:
                if (firstParameter < 0)
                    errorFlag = 1;
                else
                    result = Math.sqrt(firstParameter);
                break;
            case constants.CHAR_DOT:
                secondParameter = valueStack.pop();
                result = firstParameter/10 + secondParameter;
                break;
            case constants.CHAR_BEGIN_SIGN:
            case constants.CHAR_END_SIGN:
                errorFlag = 0;
                break;
            default:

                break;
        }
        if (errorFlag == -1 ){
            valueStack.push(result);
        }else if (errorFlag == 1 ){
            setResult("ERROR.");
        }

    }
    private boolean isNumber(char c){
        boolean result;
        switch (c){
            case constants.CHAR_ZERO:
            case constants.CHAR_ONE:
            case constants.CHAR_TWO:
            case constants.CHAR_THREE:
            case constants.CHAR_FOUR:
            case constants.CHAR_FIVE:
            case constants.CHAR_SIX:
            case constants.CHAR_SEVEN:
            case constants.CHAR_EIGHT:
            case constants.CHAR_NINE:
                result = true;
                break;
            default:result = false;
                break;
        }

        return result;
    }

    private boolean comparePriority(char operator){
        if (operatorStack.isEmpty()){
            return true;
        }
        char topOperator = operatorStack.peek();
        int topOperatorPriority = operatorPriority(topOperator);
        int opPriority = operatorPriority(operator);

        return topOperatorPriority < opPriority;
    }
    private boolean comparePriority(char operator1,char operator2){

        int topOperatorPriority = operatorPriority(operator1);
        int opPriority = operatorPriority(operator2);

        return topOperatorPriority < opPriority;
    }

    private int operatorPriority(char operator){
        int priority = -1;
        switch (operator){
            case constants.CHAR_PLUS:
            case constants.CHAR_MINUS:
                priority = 1;
                break;
            case constants.CHAR_MULTIPLY:
            case constants.CHAR_DIVIDE:
                priority = 2;
                break;
            case constants.CHAR_SQRT:
                priority = 3;
                break;
            case constants.CHAR_DOT:
                priority = 4;
                break;
            case constants.CHAR_BEGIN_SIGN:
                priority = 0;
                break;
            case constants.CHAR_END_SIGN:
                priority = -1;
                break;
            default:break;
        }
        return priority;
    }
    private double precisionRound(double number, int precision) {
        double factor = Math.pow(10, precision);
        return Math.round(number * factor) / factor;
    }

}
