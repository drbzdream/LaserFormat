package com.example.titivoradac.laserformat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.Toast;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    private EditText laserNationalNo;
    private Button submitLaserNationnalNo;
//    private TextView successSubmitLaserNationNoToAPI;

    //we need to block the :afterTextChanges method to be called again after we just replaced the EditText text
    private boolean editedFlag = false;

    private String laserNationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laserNationalNo = (EditText) findViewById(R.id.sign_up_national_id);
        submitLaserNationnalNo = (Button) findViewById(R.id.button_submit);
//        successSubmitLaserNationNoToAPI = (TextView) findViewById(R.id.result_send_api);

        laserNationalNo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if(editedFlag) return;

                editedFlag = true;
                // removing old dashes
                StringBuilder lasertextshow = new StringBuilder();
                lasertextshow.append(s.toString().replace("-", ""));


                if (lasertextshow.length() > 3){
                    lasertextshow.insert(3, "-");
                }
                if (lasertextshow.length() > 11){
                    lasertextshow.insert(11, "-");
                }

                s.replace(0, s.length(), lasertextshow.toString());
                editedFlag = false;

                laserNationText = s.toString();

            }

        });

        submitLaserNationnalNo.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String test = laserNationalNo.getText().toString();
                        String laserFormat = "[a-zA-Z]{2}[0-9]-[0-9]{7}-[0-9]{2}";

                        if(test.matches(laserFormat)){
                            Toast.makeText(MainActivity.this, "Avaliable!!!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}


