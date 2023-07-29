package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;
    String formula = "";
    String tempFormula = "";
    String workings = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        workingsTV = findViewById(R.id.workingsTextView);
        resultsTV = findViewById(R.id.resultsTextView);
    }

    private void setWorkings(String givenValue) {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    private void check() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }
        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers) {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i)))
                numberLeft = workings.charAt(i) + numberLeft;
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char ch) {
        return (ch <= '9' && ch >= '0') || ch == '.';
    }

    public void clearOnclick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketsOnclick(View view) {
        if (leftBracket == true) {
            setWorkings("(");
            leftBracket = false;
        } else {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnclick(View view) {
        setWorkings("^");
    }

    public void divisionOfOnclick(View view) {
        setWorkings("/");
    }

    public void mulOfOnclick(View view) {
        setWorkings("*");
    }

    public void addOfOnclick(View view) {
        setWorkings("+");
    }

    public void subOfOnclick(View view) {
        setWorkings("-");
    }

    public void oneOfOnclick(View view) {
        setWorkings("1");
    }

    public void twoOfOnclick(View view) {
        setWorkings("2");
    }

    public void threeOfOnclick(View view) {
        setWorkings("3");
    }

    public void fourOfOnclick(View view) {
        setWorkings("4");
    }

    public void fiveOfOnclick(View view) {
        setWorkings("5");
    }

    public void sixOfOnclick(View view) {
        setWorkings("6");
    }

    public void sevenOfOnclick(View view) {
        setWorkings("7");
    }

    public void eightOfOnclick(View view) {
        setWorkings("8");
    }

    public void nineOfOnclick(View view) {
        setWorkings("9");
    }

    public void zeroOfOnclick(View view) {
        setWorkings("0");
    }

    public void equalsOnclick(View view) {
        Double result = null;
        check();

        try {
            result = (double) new javax.script.ScriptEngineManager().getEngineByName("rhino").eval(formula);
            resultsTV.setText(result.toString());
        } catch (Exception e) {
            resultsTV.setText("Error");
        }
    }
}
