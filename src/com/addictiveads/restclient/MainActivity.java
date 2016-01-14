package com.addictiveads.restclient;

//import android.support.v7.app.ActionBarActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    protected Button mSendButton;
    protected TextView mServerResponse;
    protected EditText mYourName;

    protected final String BASE_URL = "http://dev.addictiveadsnetwork.com/api/v1/test/hello/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServerResponse = (TextView) findViewById(R.id.text_server_response);
        mYourName = (EditText) findViewById(R.id.edit_name);
        mSendButton = (Button) findViewById(R.id.button_send);

        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = mYourName.getText().toString();
                mServerResponse.setText(name);

                // here call the class which has implemented AsyncTask with URL to access Web Service
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
