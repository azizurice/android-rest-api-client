package com.addictiveads.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
                String urlString = BASE_URL + name;

                // Call the class which has implemented AsyncTask with URL
                // to access Web Service by Background thread
                new CallAddictiveAdsRestApi().execute(urlString);

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

    public class CallAddictiveAdsRestApi extends
            AsyncTask<String, Void, String> {

        private final String LOG_TAG = CallAddictiveAdsRestApi.class
                .getSimpleName();

        @Override
        protected String doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String serverResponseJsonString = null;

            try {
                // Construct the URL for addictiveadsnetwork.com
                // Uri builtUri = Uri.parse(params[0]).buildUpon().build();
                Uri builtUri = Uri.parse(params[0]).buildUpon().build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI " + builtUri.toString());

                // Open the connection and Send the Http GET request
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Adding a newline does make debugging a *lot* easier
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream is empty. No point in parsing.
                    return null;
                }
                serverResponseJsonString = buffer.toString();

                Log.v(LOG_TAG, "Server response: " + serverResponseJsonString);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If we didn't successfully get server response, there's
                // no point in attempting to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            // if (serverResponseJsonString.length() > 0) {
            // return "Got server response. JSON data needs to be processed!!";
            // }
            try {
                return getMessageFromJsonString(serverResponseJsonString);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.v(LOG_TAG, "Server response Message: " + result);
            if (result != null) {
                // Got the required message
                mServerResponse.setText(result.toString());
            }

        }

        private String getMessageFromJsonString(String serverResponseJsonString)
                throws JSONException {

            // Server response:
            // {"success":true,"data":{"message":"hello Azizur, from GET"}}
            // Following JSON objects need to be extracted.

            final String SR_SUCCESS = "success";
            final String SR_DATA = "data";
            final String SR_MESSAGE = "message";

            JSONObject serverResponseJson = new JSONObject(
                    serverResponseJsonString);
            boolean success = serverResponseJson.getBoolean(SR_SUCCESS);
            JSONObject jsonData = serverResponseJson.getJSONObject(SR_DATA);
            String message = jsonData.getString(SR_MESSAGE);

            if (success) {
                return message;
            } else {
                return null;
            }

        }

    }

}
