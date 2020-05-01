package com.example.dictionarye;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
      TextView textView;
    EditText editText;
    Button button;
//    final String apikey = "realabbas";
    String txt;
//    String finalurl = "https://b8qalj4ph8.execute-api.ap-south-1.amazonaws.com/production/dictionary?word="+ txt +"&apikey=realabbas";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String baseUrl = "https://b8qalj4ph8.execute-api.ap-south-1.amazonaws.com/production/dictionary?word=cold&apikey=realabbas";
        editText = (EditText) findViewById(R.id.edit);
        button = findViewById(R.id.button);
//               net(baseUrl);
       textView =(TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt = editText.getText().toString();
                Log.d("dict", "onClick: " + txt);
                String finalurl = "https://b8qalj4ph8.execute-api.ap-south-1.amazonaws.com/production/dictionary?word="+ txt +"&apikey=realabbas";

//    dictionaryAsynTask task =new  dictionaryAsynTask();
//    task.execute(finalurl);
                net(finalurl);
//                Context context = null;
//
                InputMethodManager mngr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                mngr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

//                  net(baseUrl);
    }

    public void net(String BASE_URL) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                //Log.d("bitcoin", "JSON: " + response.toString());
                // JSONObject jsonObject = new JSONObject();
//                try {
////                    String price1 = response.getString("meaning");
                    Log.d("bitcoin", "onSuccess: " + response.toString());
                try {
                    Log.d("bitcoin", "onSuccess: "+ response.getString("meaning"));
                       textView.setText(response.getString("meaning"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
////                    mPriceTextView.setText(price1);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());
            }
        });

    }
}
//    private class dictionaryAsynTask extends AsyncTask<String, Void, String>
//    {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            String  result = fetchStateData(urls[0]);
//            return result;
//
//        }
//        protected void onPostExecute(String data) {
//            // Clear the adapter of previous earthquake data
//
//            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//            // data set. This will trigger the ListView to update.
////            textView.setText(data);
//            Log.d("jj", "onPostExecute: " + data);
//        }
//    }
//
//
//
//
//    private static URL createUrl(String stringUrl) {
//        URL url = null;
//        try {
//            url = new URL(stringUrl);
//        } catch (MalformedURLException e) {
//            Log.e("testing", "Problem building the URL ", e);
//        }
//        return url;
//    }
//
//    private static String makeHttpRequest(URL url) throws IOException {
//        String jsonResponse = "";
//
//        // If the URL is null, then return early.
//        if (url == null) {
//            return jsonResponse;
//        }
//
//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//        try {
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setReadTimeout(10000 /* milliseconds */);
//            urlConnection.setConnectTimeout(15000 /* milliseconds */);
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            // If the request was successful (response code 200),
//            // then read the input stream and parse the response.
//            if (urlConnection.getResponseCode() == 200) {
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//            } else {
//                Log.e("testing", "Error response code: " + urlConnection.getResponseCode());
//            }
//        } catch (IOException e) {
//            Log.e("testing", "Problem retrieving the earthquake JSON results.", e);
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (inputStream != null) {
//                // Closing the input stream could throw an IOException, which is why
//                // the makeHttpRequest(URL url) method signature specifies than an IOException
//                // could be thrown.
//                inputStream.close();
//            }
//        }
//        return jsonResponse;
//    }
//
//    private static String readFromStream(InputStream inputStream) throws IOException {
//        StringBuilder output = new StringBuilder();
//        if (inputStream != null) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            String line = reader.readLine();
//            while (line != null) {
//                output.append(line);
//                line = reader.readLine();
//            }
//        }
//        return output.toString();
//    }
//
//    private static String extractFeatureFromJson(String response) {
//        String meaning = null;
//        try {
//            JSONObject jsonresponse = new JSONObject(response);
//             meaning = jsonresponse.getString("meaning");
//
//        } catch (JSONException e) {
//            Log.e("testing", "extractFeatureFromJson: ", e);
//        }
//
//        return meaning;
//    }
//
//    public static String fetchStateData(String requestUrl) {
//
//        URL url = createUrl(requestUrl);
//        String jsonResponse = null;
//        try {
//            jsonResponse = makeHttpRequest(url);
//        } catch (IOException e) {
//            Log.e("testing", "Problem making the HTTP request.", e);
//        }
//     String finalmeaning  = extractFeatureFromJson(jsonResponse);
//        return finalmeaning;
//    }











