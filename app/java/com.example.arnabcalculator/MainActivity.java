package com.example.arnabcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TokenStack operatorStack;
    private TokenStack valueStack;
    private boolean error;
    private double finalAnswer;
    private boolean flag;
    private boolean flag2;

    public MainActivity() {
        operatorStack = new TokenStack();
        valueStack = new TokenStack();
        error = false;
        flag = false;
        flag2 = false;
    }

    private void processOperator(Token t) {
        Token A = null, B = null;
        if (valueStack.isEmpty()) {
            error = true;
            return;
        } else {
            B = valueStack.top();
            valueStack.pop();
        }
        if (valueStack.isEmpty()) {
            error = true;
            return;
        } else {
            A = valueStack.top();
            valueStack.pop();
        }
        Token R = t.operate(A.getValue(), B.getValue());
        valueStack.push(R);
    }

    public double processInput(String input) {
        String[] parts = input.split(" ");
        Token[] tokens = new Token[parts.length];
        for (int n = 0; n < parts.length; n++) {
            tokens[n] = new Token(parts[n]);
        }
        for (int n = 0; n < tokens.length; n++) {
            Token nextToken = tokens[n];
            if (nextToken.getType() == Token.NUMBER) {
                valueStack.push(nextToken);
            } else if (nextToken.getType() == Token.OPERATOR) {
                if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
                    operatorStack.push(nextToken);
                } else {
                    while (!operatorStack.isEmpty() && nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
                        Token toProcess = operatorStack.top();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(nextToken);
                }
            } else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(nextToken);
            } else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
                while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
                    Token toProcess = operatorStack.top();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
                    operatorStack.pop();
                } else {
                    error = true;
                    return 0;
                }
            }

        }

        while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
            Token toProcess = operatorStack.top();
            operatorStack.pop();
            processOperator(toProcess);
        }

        if(!error) {
            Token result = valueStack.top();
            valueStack.pop();
            if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
                error = true;
                return 0;
            }
            finalAnswer = result.getValue();
        }
        return finalAnswer;
    }
    boolean checkOperator(char ch){
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }
    boolean notError(String str) {
        if(str=="My love for you" || str=="Syntax Error")
            return true;
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        final TextView calcuScreen = findViewById(R.id.CalculatorScreen);
        final TextView finalScreen = findViewById(R.id.FinalResult);
        final Button one = findViewById(R.id.one);
        final Button zero = findViewById(R.id.zero);
        final Button two = findViewById(R.id.two);
        final Button three = findViewById(R.id.three);
        final Button four = findViewById(R.id.four);
        final Button five = findViewById(R.id.five);
        final Button six = findViewById(R.id.six);
        final Button seven = findViewById(R.id.seven);
        final Button eight = findViewById(R.id.eight);
        final Button nine = findViewById(R.id.nine);
        final Button div = findViewById(R.id.divide);
        final Button mul = findViewById(R.id.product);
        final Button sub = findViewById(R.id.subtract);
        final Button add = findViewById(R.id.add);
        final Button point = findViewById(R.id.point);
        final Button equal = findViewById(R.id.equals);
        final Button lBrace = findViewById(R.id.leftBrace);
        final Button rBrace = findViewById(R.id.rightBrace);
        final View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id){
                    case R.id.zero:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("0");
                        break;
                    case R.id.one:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("1");
                        break;
                    case R.id.two:
                        if(flag2)
                            calcuScreen.setText("");
                        flag =false;
                        flag2 = false;
                        calcuScreen.append("2");
                        break;
                    case R.id.three:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("3");
                        break;
                    case R.id.four:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("4");
                        break;
                    case R.id.five:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("5");
                        break;
                    case R.id.six:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("6");
                        break;
                    case R.id.seven:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("7");
                        break;
                    case R.id.eight:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("8");
                        break;
                    case R.id.nine:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append("9");
                        break;
                    case R.id.point:
                        if(flag2)
                            calcuScreen.setText("");
                        flag = false;
                        flag2 = false;
                        calcuScreen.append(".");
                        break;
                    case R.id.equals:
                        flag2 = true;
                        if(calcuScreen.getText().toString().length()==0)
                            break;
                        double ans = processInput(calcuScreen.getText().toString());
                        if(error){
                            String errorMessage = "Syntax Error";
                            finalScreen.setText(errorMessage);
                        }
                        else {
                            String answer = Double.toString(ans);
                            String infinity = "My love for you";
                            if(answer.charAt(answer.length()-1)=='0' && answer.charAt(answer.length()-2)=='.')
                                answer = answer.substring(0,answer.length()-2);
                            if(ans == Double.POSITIVE_INFINITY || ans == Double.NEGATIVE_INFINITY)
                                finalScreen.setText(infinity);
                            else
                                finalScreen.setText(answer);
                        }
                        error = false;
                        break;
                    case R.id.divide:
                        if(!flag && flag2){
                            String getans = finalScreen.getText().toString();
                            if(!notError(getans)) {
                                calcuScreen.setText(getans);
                                flag2 = false;
                            }
                        }
                        if(calcuScreen.getText().toString().length()==0)
                            break;
                        String scren = calcuScreen.getText().toString();
                        if(!checkOperator(scren.charAt(scren.length()-1))) {
                            calcuScreen.append(" ");
                            calcuScreen.append("/");
                            calcuScreen.append(" ");
                        }
                        break;
                    case R.id.product:
                        if(!flag && flag2){
                            String getans = finalScreen.getText().toString();
                            if(!notError(getans)) {
                                calcuScreen.setText(getans);
                                flag2 = false;
                            }
                        }
                        if(calcuScreen.getText().toString().length()==0)
                            break;
                        String scren1 = calcuScreen.getText().toString();
                        if(!checkOperator(scren1.charAt(scren1.length()-1))) {
                            calcuScreen.append(" ");
                            calcuScreen.append("*");
                            calcuScreen.append(" ");
                        }
                        break;
                    case R.id.subtract:
                        if(!flag && flag2){
                            String getans = finalScreen.getText().toString();
                            if(!notError(getans)) {
                                calcuScreen.setText(getans);
                                flag2 = false;
                            }
                        }
                        String scren2 = calcuScreen.getText().toString();
                        if(calcuScreen.getText().toString().length()==0){
                            calcuScreen.append("-");
                            flag2 = false;
                        }
                        else if(!checkOperator(scren2.charAt(scren2.length()-2))) {
                            calcuScreen.append(" ");
                            calcuScreen.append("-");
                            calcuScreen.append(" ");
                        }
                        break;
                    case R.id.add:
                        if(!flag && flag2){
                            String getans = finalScreen.getText().toString();
                            if(!notError(getans)) {
                                calcuScreen.setText(getans);
                                flag2 = false;
                            }
                        }
                        if(calcuScreen.getText().toString().length()==0)
                            break;
                        String scren3 = calcuScreen.getText().toString();
                        if(!checkOperator(scren3.charAt(scren3.length()-1))) {
                            calcuScreen.append(" ");
                            calcuScreen.append("+");
                            calcuScreen.append(" ");
                        }
                        break;
                    case R.id.leftBrace:
                        if(!flag && flag2){
                            calcuScreen.setText("( ");
                            finalScreen.setText("");
                            flag2 = false;
                            break;
                        }
                        String scren4 = calcuScreen.getText().toString();
                        if(scren4.length()>1 && checkOperator(scren4.charAt(scren4.length()-2))) {
                            calcuScreen.append(" ");
                            calcuScreen.append("(");
                            calcuScreen.append(" ");
                        }
                        break;
                    case R.id.rightBrace:
                        if(!flag && flag2){
                            /*calcuScreen.setText("");
                            finalScreen.setText("");*/
                            flag2 = false;
                        }
                        if(calcuScreen.getText().toString().length()==0)
                            break;
                        calcuScreen.append(" ");
                        calcuScreen.append(")");
                        calcuScreen.append(" ");

                        break;
                }
            }
        };
        zero.setOnClickListener(calculatorListener);
        one.setOnClickListener(calculatorListener);
        two.setOnClickListener(calculatorListener);
        three.setOnClickListener(calculatorListener);
        four.setOnClickListener(calculatorListener);
        five.setOnClickListener(calculatorListener);
        six.setOnClickListener(calculatorListener);
        seven.setOnClickListener(calculatorListener);
        eight.setOnClickListener(calculatorListener);
        nine.setOnClickListener(calculatorListener);
        point.setOnClickListener(calculatorListener);
        equal.setOnClickListener(calculatorListener);
        div.setOnClickListener(calculatorListener);
        mul.setOnClickListener(calculatorListener);
        add.setOnClickListener(calculatorListener);
        sub.setOnClickListener(calculatorListener);
        lBrace.setOnClickListener(calculatorListener);
        rBrace.setOnClickListener(calculatorListener);

        final Button delete = findViewById(R.id.del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String element = calcuScreen.getText().toString();
                int length = element.length();
                if(length>0){
                    if(length > 1 && checkOperator(element.charAt(element.length()-2))) {
                        element = element.substring(0, length - 3);
                        calcuScreen.setText(element);
                    }
                    else{
                        element = element.substring(0,length-1);
                        calcuScreen.setText(element);
                    }
                }
            }
        });

        final Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcuScreen.setText("");
                finalScreen.setText("");
                flag = true;
            }
        });
    }

}
