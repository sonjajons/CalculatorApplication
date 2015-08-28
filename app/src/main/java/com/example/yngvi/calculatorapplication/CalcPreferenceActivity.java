package com.example.yngvi.calculatorapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Yngvi on 28/08/15.
 */
public class CalcPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource(R.xml.preferences);
    }
}
