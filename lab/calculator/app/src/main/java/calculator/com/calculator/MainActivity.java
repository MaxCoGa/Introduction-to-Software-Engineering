package calculator.com.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private enum Operator{ADD,SUBTRACT,DIVIDE,MULTIPLY,NONE}
    private static TextView results;
    private Operator oper = Operator.NONE;
    private String num1 = "";
    private String num2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        results=(TextView)findViewById(R.id.resultEdit);
        results.setText("0");
    }

    private void appendDigit(String digit) {
        if (oper == Operator.NONE) {
            if (digit.equalsIgnoreCase(".") && num1.contains(".")) {
                return;
            }
            num1 += digit;
            results.setText(num1);
        } else {
            if (digit.equalsIgnoreCase(".") && num2.contains(".")) {
                return;
            }
            num2 += digit;
            results.setText(num2);
        }
    }
    public void btn01Click(View view) {
        appendDigit("1");
    }
    public void btn02Click(View view) {
        appendDigit("2");
    }
    public void btn03Click(View view) {
        appendDigit("3");
    }
    public void btn04Click(View view) {
        appendDigit("4");
    }
    public void btn05Click(View view) {
        appendDigit("5");
    }
    public void btn06Click(View view) {
        appendDigit("6");
    }
    public void btn07Click(View view) {
        appendDigit("7");
    }
    public void btn08Click(View view) {
        appendDigit("8");
    }
    public void btn09Click(View view) {
        appendDigit("9");
    }
    public void btn00Click(View view) {
        appendDigit("0");
    }
    public void btnAddClick(View view) {
        oper = Operator.ADD;
    }
    public void btnMinusClick(View view){
        oper = Operator.SUBTRACT;
    }
    public void btnDivideClick(View view) {
        oper = Operator.DIVIDE;
    }
    public void btnClearClick(View view) {
        oper = Operator.NONE;
        num1="";
        results.setText("0");
    }
    public void btnDotClick(View view) {
        appendDigit(".");
    }
    public void btnMultiplyClick(View view) {
        oper = Operator.MULTIPLY;
    }
    public void btnResultClick(View view) {
        if (num1.length() > 0 && num2.length() > 0){
            Double result = 0.0;
            if (oper.equals(Operator.ADD)) {
                result = Double.parseDouble(num1) + Double.parseDouble(num2);
            } else if (oper.equals(Operator.SUBTRACT)) {
                result = Double.parseDouble(num1) - Double.parseDouble(num2);
            } else if (oper.equals(Operator.DIVIDE)) {
                result = Double.parseDouble(num1) / Double.parseDouble(num2);
            } else if (oper.equals(Operator.MULTIPLY)) {
                result = Double.parseDouble(num1) * Double.parseDouble(num2);
            }
            num1=String.valueOf(result);
            num2="";
            results.setText(num1);
        }
    }

}