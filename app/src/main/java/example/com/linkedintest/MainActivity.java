package example.com.linkedintest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;


import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private static final String host = "api.linkedin.com";
    private static final String topCardUrl = "https://" + host + "/v1/people/~:(id,first-name,last-name,public-profile-url)";


    private Button btnLogin;
    private Button btnPost;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.login_webview);

        webView.setWebViewClient(new WebViewClient(){


                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    String url = request.getUrl().toString();

                    if (url.startsWith(WebLogin.getRedirect_uri())) {
                        // webview not load url
                    }
                    else {
                        webView.loadUrl(WebLogin.getLinkedinAuthUrl());
                        return false;
                    }

                   return true;


                }

                @SuppressWarnings("deprecation")
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if (url.startsWith(WebLogin.getRedirect_uri())) {
                        // webview not load url

                        Uri uri = Uri.parse(url);
                        String accessToken = uri.getQueryParameter("response_type");


                    }
                    else{
                        webView.loadUrl(WebLogin.getLinkedinAuthUrl());
                        return false;
                    }

                    return true;
                }

            }


        );
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(WebLogin.getLinkedinAuthUrl());
        webView.requestFocus();


        //webView.loadDataWithBaseURL("", "hello", null, null, null);

        /*Uri uri = Uri.parse(WebLogin.getLinkedinAuthUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        /*
        final Activity thisActivity = this;

        btnPost = (Button) findViewById(R.id.btn_post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            try {
                URL url = new URL("https://ais.armada.nu/api/student_profile/matching");
                new PostAsync(url).execute();

            }
            catch (MalformedURLException me){}

            }
        });

       // generateHashkey();


        //((Button) findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {

           // @Override



        /* GENERATING A HASH KEY WITH THE generateHashKey() method works when you add it to
            my LinkedIn apps, accessToken will be valid

            However, generating a hash key with keytools in cmd doesn't work. You get a hask key
            but it seems like that hash key isn't associated with the app.

         */
        /*
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        LISession session = sessionManager.getSession();
        boolean accessTokenValid = session.isValid();

        if (!accessTokenValid) {

            LISessionManager.getInstance(getApplicationContext()).init(thisActivity, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {


                }

                @Override
                public void onAuthError(LIAuthError error) {

                }
            }, true);

        }

           // }
        //});


        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                apiHelper.getRequest(MainActivity.this, topCardUrl, new ApiListener() {
                    public void onApiSuccess(ApiResponse result) {
                        try {
              Log.v("result",result.getResponseDataAsJson().toString());
                        } catch (Exception e){
                                e.printStackTrace();
                        }
              }
               @Override
                    public void onApiError(LIApiError error) {
                }
                });

        */
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);


    }

    public void generateHashkey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "example.com.linkedintest",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                      //  ((TextView) findViewById(R.id.package_name)).setText(info.packageName);
                Log.v(TAG, Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage(), e);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

    private Scope buildScope(){return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }



}
