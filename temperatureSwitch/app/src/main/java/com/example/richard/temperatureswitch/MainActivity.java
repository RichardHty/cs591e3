package com.example.richard.temperatureswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private temperatureCalculator tcal;
    private EditText celsiusInput;
    private EditText fahrenheitInput;
    private TextView celsiusOutput;
    private TextView fahrenheitOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcal = new temperatureCalculator(0.0f, 32.0f);

        celsiusInput = (EditText) findViewById(R.id.celsiusInput);
        celsiusOutput = (TextView) findViewById(R.id.celsiusOutput);
        fahrenheitInput = (EditText) findViewById(R.id.fahrenheitInput);
        fahrenheitOutput = (TextView) findViewById(R.id.fahrenheitOutput);


//
        celsiusInput.addTextChangedListener(new TextChangeHandler(0));
        fahrenheitInput.addTextChangedListener(new TextChangeHandler(1));
    }

    private class TextChangeHandler implements TextWatcher{
        private int type = -1;
        public TextChangeHandler(int ID){
            type = ID;
        }
        public void beforeTextChanged(CharSequence s,int start,int count,int after){

        }
        public void onTextChanged(CharSequence s,int start, int before,int after){



        }
        public void afterTextChanged(Editable e) {
            if (type == 0) {
                String cel = celsiusInput.getText().toString();
                try {
                    float celFloat = Float.parseFloat(cel);
                    tcal.setCel(celFloat);
                    float fahResult = tcal.celToFah();
                    celsiusOutput.setText(Float.toString(fahResult));
                } catch (NumberFormatException excp) {
                    excp.printStackTrace();
                }
            }
            if (type == 1) {
                String fah = fahrenheitInput.getText().toString();
                try {
                    float fahFloat = Float.parseFloat(fah);
                    tcal.setFah(fahFloat);
                    float celResult = tcal.fahToCel();
                    fahrenheitOutput.setText(Float.toString(celResult));

                } catch (NumberFormatException excp) {
                    excp.printStackTrace();
                }
            }
        }

    }
}
