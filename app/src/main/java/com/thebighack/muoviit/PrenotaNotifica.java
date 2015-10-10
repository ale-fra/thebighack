package com.thebighack.muoviit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class PrenotaNotifica extends Activity {


    private WebView webview;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;


    WebView wv;

    JavaScriptInterface JSInterface;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bellButton).setVisibility(View.VISIBLE);

        super.onCreate(savedInstanceState);
        wv = (WebView) findViewById(R.id.webview);

        wv.getSettings().setJavaScriptEnabled(true);
        // register class containing methods to be exposed to JavaScript

        JSInterface = new JavaScriptInterface(this);
        wv.addJavascriptInterface(JSInterface, "JSInterface");

        wv.loadUrl("file:///android_asset/index.html");


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class JavaScriptInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        JavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void triggerAction() {
            Toast.makeText(mContext,"just a test",Toast.LENGTH_SHORT).show();
        }
    }
}
