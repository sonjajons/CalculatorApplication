package com.example.yngvi.calculatorapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    private TextView m_display;
    private Vibrator m_vibrator;
    private Boolean m_use_vibrator = false;
    SharedPreferences m_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_display = (TextView) findViewById(R.id.display);
        m_vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        m_sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

    }

    @Override
    protected void onStart() {
        super.onStart();
        m_use_vibrator = m_sp.getBoolean("vibrate", false);
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,CalcPreferenceActivity.class);
            startActivity( intent );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonPressed( View view ) {
        Button buttonView = (Button) view;
        String text = null;
        //Log.i("Calculator", "Clicked ..");
        switch ( view.getId() ) {
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                m_display.append( buttonView.getText() );
                break;
            case R.id.buttonP:
                text = m_display.getText().toString();
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                if ( !(text.endsWith("+") || text.endsWith("-")) ) {
                    m_display.append( buttonView.getText() );
                }
                break;
            case R.id.buttonC:
                m_display.setText("");
                break;
            case R.id.buttonE:
                String result = evaluateExpression(m_display.getText().toString());
                m_display.setText(result);
                break;
        }

        if ( m_use_vibrator ) {
            m_vibrator.vibrate( 500 );
            Toast.makeText( getApplicationContext(), "Vibrate ...", Toast.LENGTH_SHORT);
        }

    }

    String evaluateExpression( String expr ) {
        BigInteger result = BigInteger.ZERO;
        StringTokenizer st = new StringTokenizer( expr, "[+\\-]", true );
        while ( st.hasMoreElements() ) {
            String token = st.nextToken();
        }
        return result.toString();
    }
}
