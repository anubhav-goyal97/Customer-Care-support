package com.anubhav.lenovo.dell;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.annotation.TargetApi;

public class searchhhh extends AppCompatActivity {
EditText e1;Button b1;
    String text,u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhhh);
        e1=(EditText)findViewById(R.id.editText3);
        b1=(Button) findViewById(R.id.buttonOk);
    }

    public void open(View view){
        WebView webView=new WebView(this);
        text=e1.getText().toString();
        setContentView(webView);
        String url="https://www.dell.com/support/search/in/en/inbsd1#q=";
        url=url.concat(text.trim());

        String url2="&sort=relevancy";
        url=url.concat(url2);
        u="www.dell.com/support/search/in/en/inbsd1#q="+text+"&sort=relevancy";

//        String []url3=text.split(" ");
//        String url4="";
//        for(int i=0;i<url3.length;++i)
//        {
//            if(i==0)
//                url4=url4.concat(url3[0]);
//            else
//            {   String t="%20";
//                url3[i]=t.concat(url3[i]);
//                url4=url4.concat(url3[i]);
//            }
//        }


//        url=url.concat(url4);
//        url=url.concat(url2);
       url="https://www.dell.com/support/search/in/en/inbsd1#q=battery plugged in not charging&sort=relevancy";
       webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

//        Intent browserintent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
//        startActivity(browserintent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searchhhh, menu);

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
}
