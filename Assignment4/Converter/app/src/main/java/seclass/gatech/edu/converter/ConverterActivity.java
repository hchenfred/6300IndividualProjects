package seclass.gatech.edu.converter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity {

    private EditText distValue;
    private EditText distResult;
    private Spinner convertFromSpinner;
    private Spinner convertToSpinner;
    private String convertFromSelection = "Mile";
    private String convertToSelection = "Mile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distValue = (EditText)findViewById(R.id.distValue);
        distResult = (EditText)findViewById(R.id.distResult);

        addItemsOnConvertFromSpinner();
        convertFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertFromSelection = convertFromSpinner.getSelectedItem().toString();
                //Toast.makeText(getBaseContext(), convertFromSelection + " selected for convert from value", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addItemsOnConvertToSpinner();
        convertToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertToSelection = convertToSpinner.getSelectedItem().toString();
                //Toast.makeText(getBaseContext(), convertToSelection + " selected for convert to value", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addItemsOnConvertFromSpinner() {
        convertFromSpinner = (Spinner)findViewById(R.id.convertFromSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.convertFrom_arrays,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertFromSpinner.setAdapter(adapter);
    }

    private void addItemsOnConvertToSpinner() {
        convertToSpinner = (Spinner)findViewById(R.id.convertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.convertTo_arrays,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertToSpinner.setAdapter(adapter);
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.buttonConvert:
                String value = distValue.getText().toString();
                if (value.length() > 0) {
                    if (convertFromSelection.contentEquals(convertToSelection)) {
                        distResult.setText(value);
                    } else {
                       distResult.setText(convertUnit(convertFromSelection, convertToSelection, value));
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Empty value!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            case R.id.buttonReset:
                distValue.setText("");
                distResult.setText("");
                convertFromSpinner.setSelection(0);
                convertToSpinner.setSelection(0);
                break;
        }

    }

    public String convertUnit(String convertFrom, String convertTo, String value) {
        double input = Double.parseDouble(value);
        double result = 0.00;
        DecimalFormat format = new DecimalFormat("#.##");
        if (convertFrom.contentEquals("Mile") && convertTo.contentEquals("Km")) {
            result = input * 1.60934;
        } else if (convertFrom.contentEquals("Mile") && convertTo.contentEquals("Feet")) {
            result = input * 5280;
        } else if (convertFrom.contentEquals("Mile") && convertTo.contentEquals("Inch")) {
            result = input * 63360;
        } else if (convertFrom.contentEquals("Mile") && convertTo.contentEquals("Meter")) {
            result = input * 1609.34;
        } else if (convertFrom.contentEquals("Mile") && convertTo.contentEquals("Cm")) {
            result = input * 160934;
        } else if (convertFrom.contentEquals("Feet") && convertTo.contentEquals("Mile")) {
            result = input * 0.000189394;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Feet") && convertTo.contentEquals("Inch")) {
            result = input * 12;
        } else if (convertFrom.contentEquals("Feet") && convertTo.contentEquals("Meter")) {
            result = input * 0.3048;
        } else if (convertFrom.contentEquals("Feet") && convertTo.contentEquals("Cm")) {
            result = input * 30.48;
        } else if (convertFrom.contentEquals("Feet") && convertTo.contentEquals("Km")) {
            result = input * 0.0003048;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Inch") && convertTo.contentEquals("Mile")) {
            result = input * 0.0000158;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Inch") && convertTo.contentEquals("Feet")) {
            result = input * 0.0833333;
        } else if (convertFrom.contentEquals("Inch") && convertTo.contentEquals("Km")) {
            result = input / 39370;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Inch") && convertTo.contentEquals("Meter")) {
            result = input * 0.0254;
        } else if (convertFrom.contentEquals("Inch") && convertTo.contentEquals("Cm")) {
            result = input * 2.54;
        } else if (convertFrom.contentEquals("Km") && convertTo.contentEquals("Mile")) {
            result = input / 1.60934;
        } else if (convertFrom.contentEquals("Km") && convertTo.contentEquals("Feet")) {
            result = input * 3280.84;
        } else if (convertFrom.contentEquals("Km") && convertTo.contentEquals("Inch")) {
            result = input * 39370.1;
        } else if (convertFrom.contentEquals("Km") && convertTo.contentEquals("Meter")) {
            result = input * 1000;
        } else if (convertFrom.contentEquals("Km") && convertTo.contentEquals("Cm")) {
            result = input * 100000;
        } else if (convertFrom.contentEquals("Meter") && convertTo.contentEquals("Mile")) {
            result = input * 0.000621371;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Meter") && convertTo.contentEquals("Feet")) {
            result = input * 3.28084;
        } else if (convertFrom.contentEquals("Meter") && convertTo.contentEquals("Inch")) {
            result = input * 39.3701;
        } else if (convertFrom.contentEquals("Meter") && convertTo.contentEquals("Km")) {
            result = input / 1000;
        } else if (convertFrom.contentEquals("Meter") && convertTo.contentEquals("Cm")) {
            result = input * 100;
        } else if (convertFrom.contentEquals("Cm") && convertTo.contentEquals("Mile")) {
            result = input * 0.0000062137;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Cm") && convertTo.contentEquals("Feet")) {
            result = input * 0.0328084;
        } else if (convertFrom.contentEquals("Cm") && convertTo.contentEquals("Inch")) {
            result = input * 0.393701;
        } else if (convertFrom.contentEquals("Cm") && convertTo.contentEquals("Km")) {
            result = input / 100000;
            format = new DecimalFormat("#.#######");
        } else if (convertFrom.contentEquals("Cm") && convertTo.contentEquals("Meter")) {
            result = input / 100;
        }
        return String.valueOf(format.format(result));
    }
}
