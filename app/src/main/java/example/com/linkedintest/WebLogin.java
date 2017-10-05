package example.com.linkedintest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Henrik on 2017-09-29.
 */

public class WebLogin {

    private final static String TAG = WebLogin.class.getSimpleName();

    private final static String LINKEDIN_AUTH_URL = "https://www.linkedin.com/oauth/v2/authorization";
    private final static String ACCESS_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";
    private final static String SCOPE = "scope=r_basicprofile";




    private static String client_id = "78r44ids5rjt42";

    // This is a fake URL, it doesn't have to be real
    private static String redirect_uri = "https://nu.armada.linkedin/oauth";
    private static String formatted_redirect_uri = "https%3A%2F%2Fnu.armada.linkedin%2Foauth";

    public WebLogin(){


    }


    public static void initLogin(){


    }

    public static String getLinkedinAuthUrl(){

        return LINKEDIN_AUTH_URL+"?response_type=code&client_id="+client_id+"&redirect_uri="+formatted_redirect_uri+"&state=987654321&scope=r_basicprofile";
    }

    public static String getRedirect_uri(){
        return redirect_uri;
    }


    private static String generateState(){


        return "";
    }

    public static void makePostRequest(String url, String data){

        Log.d(TAG, "makePostRequest()");
        new PostAsyncTask().execute(url, data);

    }


    public static String getAccessTokenUrl(String authToken){

        /*
        return ACCESS_TOKEN_URL + "?grant_type=authorization_code&code=" + authToken
                + "&redirect_uri=" + formatted_redirect_uri + "&client_id=" + client_id + "&client_secret=verysecret";
                */

        return ACCESS_TOKEN_URL;




    }

    public static String getAccessTokenData(String authToken){
        return "grant_type=authorization_code&code=" + authToken
                + "&redirect_uri=" + formatted_redirect_uri + "&client_id=" + client_id + "&client_secret=verysecret";

    }




    private static class PostAsyncTask extends AsyncTask<String, Void, Void>{



        @Override
        protected Void doInBackground(String... params) {


            Log.d(TAG, "doInBackground POST");


            try {

                URL url = new URL(params[0]);
                String data = params[1];

                HttpURLConnection client = null;
                client = (HttpURLConnection) url.openConnection();


                client.setRequestMethod("POST");
                client.setDoOutput(true);


                OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                byte[] bytes = data.getBytes();
                outputPost.write(bytes);

                outputPost.flush();
                outputPost.close();

                int responseCode = client.getResponseCode();


                Log.d(TAG, Integer.toString(responseCode));

            }
            catch (MalformedURLException me){Log.d(TAG, "Malformed URL WebLogin");}
            catch (IOException io){Log.d(TAG, "IO exception WebLogin");}

            return null;
        }
    }

}
