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
                        String temp = test.replaceAll("-", "");

                        int check_amount = 0;

                        for (int i = 0; i < temp.length(); i++) {
                            char charAt_check = temp.charAt(i);
                            if (Character.isLetter(charAt_check) && i < 2) {
                                System.out.println(charAt_check + " is a alphabet");
                                check_amount++;
                            }else if(Character.isDigit(charAt_check) && i >= 2){
                                System.out.println(charAt_check + " is a digit");
                                check_amount++;
                            }else {
                                System.out.println(charAt_check + " error");
                            }
                        }
                        System.out.println("Check: "+ check_amount);

                        if(check_amount == 12) {
                            Toast.makeText(MainActivity.this, "Avaliable!!!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

//        @Override
//        public void detectFormat(){
//            String first = laserNationText.substring(0, 2);  // gives "How ar"
//            String second = laserNationText.substring(laserNationText.length() / 2);
//        }

    }
}



/*

public class HelloWorld{

     public static void main(String []args){
        String test = new String("r94-3338833-44");
        String temp = test.replaceAll("-", "");

        int check_amount = 0;

        for (int i = 0; i < temp.length(); i++) {
            char charAt_check = temp.charAt(i);
            if (Character.isLetter(charAt_check) && i < 2) {
                System.out.println(charAt_check + " is a alphabet");
                check_amount++;
            }else if(Character.isDigit(charAt_check) && i >= 2){
                System.out.println(charAt_check + " is a digit");
                check_amount++;
            }else {
                System.out.println(charAt_check + " error");
            }
        }
        System.out.println("Check: "+ check_amount);

        if(check_amount == 12) {
            System.out.println("Avaliable!!!");
        }else {
            System.out.println("Not found!!!");
        }
        return ;
     }
}
 */
