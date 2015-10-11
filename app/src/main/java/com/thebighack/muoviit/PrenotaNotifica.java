package com.thebighack.muoviit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
        setContentView(R.layout.activity_prenota_notifica);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        findViewById(R.id.bellButton).setVisibility(View.VISIBLE);

                    }
                },
                200);

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
            Toast.makeText(mContext, "Notifica Attivata! :D ", Toast.LENGTH_SHORT).show();
            finish();



            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.setAction("com.thebighack.muoviit.START_DESTINATION_NOTIFY");
                            PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                            NotificationCompat.Builder b = new NotificationCompat.Builder(mContext);

                            b.setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_ALL)
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.bell_256)
                                    .setTicker("Hearty365")
                                    .setContentTitle("Autobus in Arrivo!")
                                    .setContentText("Il tuo autobus è in arrivo! corri :)")
                                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                                    .setContentIntent(contentIntent)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setContentInfo("Info");


                            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(1, b.build());
                        }
                    },
                    5000);




        }
    }
}
