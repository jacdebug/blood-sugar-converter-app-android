package com.jacdebug.bloodsugarconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText mgEdit;
    EditText mmolEdit;

    TextView mgText;
    TextView mmolText;

    TextWatcher mgTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mmolEdit.removeTextChangedListener(mmolTextWatcher);

            try {
                String val = editable.toString();
                double mmol = Double.parseDouble(val) / 18;
                mmolEdit.setText(String.format("%.2f", mmol));
                mmolText.setVisibility(View.VISIBLE);
                mgText.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                mmolEdit.setText("");
                mmolText.setVisibility(View.INVISIBLE);
                mgText.setVisibility(View.INVISIBLE);
            }

            mmolEdit.addTextChangedListener(mmolTextWatcher);
        }
    };

    TextWatcher mmolTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mgEdit.removeTextChangedListener(mgTextWatcher);

            try {
                String val = editable.toString();
                double mmol = Double.parseDouble(val) * 18;
                mgEdit.setText(String.format("%.2f", mmol));
                mmolText.setVisibility(View.VISIBLE);
                mgText.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                mgEdit.setText("");
                mmolText.setVisibility(View.INVISIBLE);
                mgText.setVisibility(View.INVISIBLE);
            }

            mgEdit.addTextChangedListener(mgTextWatcher);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgEdit = findViewById(R.id.mg_edit);
        mmolEdit = findViewById(R.id.mmol_edit);

        mgText = findViewById(R.id.mg_text);
        mmolText = findViewById(R.id.mmol_text);

        mgEdit.addTextChangedListener(mgTextWatcher);
        mmolEdit.addTextChangedListener(mmolTextWatcher);
    }
}
