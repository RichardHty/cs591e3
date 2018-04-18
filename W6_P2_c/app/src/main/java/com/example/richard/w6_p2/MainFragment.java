package com.example.richard.w6_p2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by richard on 3/26/18.
 */

public class MainFragment extends Fragment {

    private static String TAG = "MainFragment";
    private static String RED = "Red";
    private static String GREEN = "Green";
    private static String BLUE = "Blue";
    private static int RED_COLOR = Color.parseColor("red");
    private static int BLUE_COLOR = Color.parseColor("blue");
    private static int GREEN_COLOR =Color.parseColor("green");
    private TextView tvResult;
    private EditText editText;
    private CheckBox checkBoxRed;
    private CheckBox checkBoxGreen;
    private CheckBox checkBoxBlue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sharedpreference,null,false);

        tvResult = (TextView) view.findViewById(R.id.tv1);
        editText = (EditText) view.findViewById(R.id.et1);
        checkBoxBlue = (CheckBox) view.findViewById(R.id.cb_blue);
        checkBoxGreen = (CheckBox) view.findViewById(R.id.cb_green);
        checkBoxRed = (CheckBox) view.findViewById(R.id.cb_red);

        View.OnClickListener checkboxListener = onCheckboxListener();
        checkBoxBlue.setOnClickListener(checkboxListener);
        checkBoxGreen.setOnClickListener(checkboxListener);
        checkBoxRed.setOnClickListener(checkboxListener);


        return view;

    }
    public View.OnClickListener onCheckboxListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Is the view now checked?
                switch (v.getId()) {
                    case R.id.cb_blue:
                        checkBoxBlue.setChecked(true);
                        checkBoxGreen.setChecked(false);
                        checkBoxRed.setChecked(false);
                        tvResult.setText(BLUE);
                        tvResult.setBackgroundColor(BLUE_COLOR);
                        editText.setText(BLUE);

                        break;
                    case R.id.cb_green:
                        checkBoxGreen.setChecked(true);
                        checkBoxRed.setChecked(false);
                        checkBoxBlue.setChecked(false);
                        tvResult.setText(GREEN);
                        tvResult.setBackgroundColor(GREEN_COLOR);
                        editText.setText(GREEN);
                        break;
                    case R.id.cb_red:
                        checkBoxRed.setChecked(true);
                        checkBoxGreen.setChecked(false);
                        checkBoxBlue.setChecked(false);
                        tvResult.setText(RED);
                        tvResult.setBackgroundColor(RED_COLOR);
                        editText.setText(RED);
                        break;
                }
            }
        };

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveSharedPreferenceInfo();
        System.out.print(TAG+" onSaveInstanceState run.");

    }


    @Override
    public void onResume() {
        super.onResume();
        retrieveSharedPreferenceInfo();
    }

    private void saveSharedPreferenceInfo(){
        //1. Refer to the SharedPreference Object.
        SharedPreferences simpleAppInfo = this.getActivity().getSharedPreferences(TAG, Context.MODE_PRIVATE);  //Private means no other Apps can access this.

        //2. Create an Shared Preferences Editor for Editing Shared Preferences.
        //Note, not a real editor, just an object that allows editing...

        SharedPreferences.Editor editor = simpleAppInfo.edit();

        //3. Store what's important!  Key Value Pair, what else is new...

        editor.putString("edtText", editText.getText().toString());
        editor.putString("txtView", tvResult.getText().toString());
        editor.putInt("tv_background_color",((ColorDrawable)tvResult.getBackground()).getColor());
        editor.putBoolean("checkbox_blue",checkBoxBlue.isChecked());
        editor.putBoolean("checkbox_green",checkBoxGreen.isChecked());
        editor.putBoolean("checkbox_red",checkBoxRed.isChecked());

        //4. Save your information.
        editor.apply();

        Toast.makeText(this.getActivity(), "Shared Preference Data Updated.", Toast.LENGTH_LONG).show();
    }


    private void retrieveSharedPreferenceInfo(){
        SharedPreferences simpleAppInfo = this.getActivity().getSharedPreferences(TAG, Context.MODE_PRIVATE);


        //Retrieving data from shared preferences hashmap.

        editText.setText(simpleAppInfo.getString("edtText", RED));        //Shared Preferences use internal memory, not SD.
        tvResult.setText(simpleAppInfo.getString("txtView", RED));

        tvResult.setBackgroundColor(simpleAppInfo.getInt("tv_background_color",Color.parseColor("red")));
        checkBoxRed.setChecked(simpleAppInfo.getBoolean("checkbox_red", true));
        checkBoxGreen.setChecked(simpleAppInfo.getBoolean("checkbox_green", false));
        checkBoxBlue.setChecked(simpleAppInfo.getBoolean("checkbox_blue", false));
    }
}
