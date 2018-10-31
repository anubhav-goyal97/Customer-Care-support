package com.anubhav.lenovo.dell;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;






import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {
    Button b1;
    WebView mWebview;
    EditText e1,e2;
    String text, u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        b1 = (Button) findViewById(R.id.button2);
        e1 = (EditText) findViewById(R.id.editText10);
        e2=(EditText)findViewById(R.id.editText11);
    }

    public void searchwebsite(View view)
    {

                text = e1.getText().toString();
                mWebview = new WebView(this);








                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();


        try {

            Map<String, Object> outerjsonObj = new HashMap<>();
            outerjsonObj.put("text", text.trim());
            outerjsonObj.put("type","search".trim());
            outerjsonObj.put("id","mk636".trim());

            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String finalJsonObj = gson.toJson(outerjsonObj);

            final String requestBody = "search="+text.trim();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "10.6.10.213:3001/products";  // services URL
            // Log.d("request string", requestBody);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Log.d("VOLLEY", response);
                    // loadingspinner.hideLoadingIndicator();
                    progressDialog.dismiss();
                    try {

                        JSONArray sentiment = new JSONArray(response);
//                        if(arrDistricts.length()<1)
//                        {
//                            PopupWindow("Invalid User !\nPlease enter valid Mobile Number and Trip Password");
//                            return;
//                        }
                        JSONObject jsonObj = new JSONObject(sentiment.get(0).toString());
                        String conductId = jsonObj.getString("CONDUCTID");
//                        String mno = jsonObj.getString("CONDUCTMOBILENO");
//                        String tCode = jsonObj.getString("TRIPCODE");
//                        String tDate = jsonObj.getString("TRIPDATE");
//                        String dTime = jsonObj.getString("DEPTTIME");
//                        String aTime = jsonObj.getString("ARRTIME");
//                        tDate="16/08/2018";
//                        dTime="06:00 PM";
//                        aTime="01:00 AM";
                        e2.setText(conductId);
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                        Intent i = new Intent(getApplicationContext(), Demo2Activity.class);
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.putString("_conductorId",conductId);
//                        editor.putString("_mobileNumber",mno);
//                        editor.putString("_tripCode", tCode);
//                        editor.putString("_tripDate",tDate);
//                        editor.putString("_departureTime",dTime);
//                        editor.putString("_arrivalTime", aTime);
//                        editor.commit();
//                        startActivity(i);
                    } catch (Throwable t) {
                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    // loadingspinner.hideLoadingIndicator();
                    progressDialog.dismiss();
                    String errmsg = "";
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        errmsg = "Connection timeout.";
                    } else if (error instanceof AuthFailureError) {
                        //TODO
                        errmsg = "Autorization Failure.";
                    } else if (error instanceof ServerError) {
                        //TODO
                        errmsg = "Server Error";
                    } else if (error instanceof NetworkError) {
                        //TODO
                        errmsg = "Network Error.";
                    } else if (error instanceof ParseError) {
                        //TODO
                        errmsg = "Error in parsing.";
                    }
                    Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };
            int socketTimeout = 10000;//5 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue.add(stringRequest);
        } catch (Throwable t) {
            //  loadingspinner.hideLoadingIndicator();
            progressDialog.dismiss();
            t.printStackTrace();
        }














//       // e2.setText(text);
//
////        String json = "{\"Blogs\":[{\"id\":\"\"}]}";
////
//////        try{
//////            JSONObject obj=new JSONObject(json);
//////            Log.d("My App",obj.toString());
//////        }
//////        catch(Throwable t)
//////        {
//////            Log.e("My App","Could not parse malformed JSON:\""+json+"\"");
//////        }
////
////
////        String data = "";
////        try {
////            JSONObject jsonRootObject;
////            jsonRootObject = new JSONObject(json);
////
////            //Get the instance of JSONArray that contains JSONObjects
////            JSONArray jsonArray;
////            jsonArray = jsonRootObject.optJSONArray("Blogs");
////
////            //Iterate the jsonArray and print the info of JSONObjects
////            for (int i = 0; i < jsonArray.length(); i++) {
////                JSONObject jsonObject = jsonArray.getJSONObject(i);
////
////                String id = jsonObject.optString("id").toString();
////                String blog_name = jsonObject.optString("blog_name").toString();
////
////                data += "Blog Number " + (i + 1) + " : \n id= " + id + " ;";
////
////                e2.setText(data);
////            }
////        }  catch (JSONException err)
////            {
////                err.printStackTrace();
////            }
////
////
////JSONObject  action=null;
////    String search="search";
////        try
////        {
////             action=new JSONObject();
////
////            action.put("text:", text);
////            action.put("type:", search);
////            String message=action.toString();
////            e2.setText(message);
////
////        }
////        catch (JSONException e)
////        {
////            e.printStackTrace();
////        }
//
//
//
////        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
////
////        final Activity activity = this;
////
////        mWebview.setWebViewClient(new WebViewClient() {
////            @SuppressWarnings("deprecation")
////            @Override
////            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
////                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
////            }
////
////            @TargetApi(android.os.Build.VERSION_CODES.M)
////            @Override
////            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
////                // Redirect to deprecated method, so you can use it in all SDK versions
////                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
////            }
////        });
////
////        text = "https://www.dell.com/support/search/in/en/inbsd1#q=" + text;
////        text = text + "&sort=relevancy";
////
////        //mWebview .loadUrl("https://wwww.google.com");
////        mWebview.loadUrl(text);
////        setContentView(mWebview);
//
//
//
//
//
////
//        Client user=new Client(text);
////
////
//        sendNetworkRequest(user);
//
//
//









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SearchActivity.this, SearchActivity.class);
        startActivity(i);
    }
//
//    private void sendNetworkRequest(Client user)
//    {
//
//        Retrofit.Builder builder=new Retrofit.Builder().baseUrl("10.6.10.213:3001").addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit= builder.build();
//        searchData client=retrofit.create(searchData.class);
//        Call<Client> call =client.createSearchData(user);
//
//    }
}