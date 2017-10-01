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

    private final static String LINKEDIN_AUTH_URL = "https://www.linkedin.com/oauth/v2/authorization";
    private final static String ACCESS_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";
    private final static String SCOPE = "scope=r_basicprofile";



    private static String client_id = "78r44ids5rjt42";
    private static String redirect_uri = "https://ais.armada.nu/api/student_profile/matching";
    private static String formatted_redirect_uri = "https%3A%2F%2Fais.armada.nu%2Fapi%2Fstudent_profile%2Fmatching";

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

    public static void makePostRequest(URL url){


        new PostAsync(url).execute();

    }


    public static String getAccessTokenUrl(String authToken){

        return ACCESS_TOKEN_URL + "?grant_type=authorization_code&code=" + authToken
                + "&redirect_uri=" + formatted_redirect_uri + "&client_id=" + client_id + "&client_secret=verysecret";



    }




    private class PostAsyncTask extends AsyncTask<URL, Void, Void>{


        @Override
        protected Void doInBackground(URL... params) {


            URL url = params[0];

            try {

                HttpURLConnection client = null;
                client = (HttpURLConnection) url.openConnection();


                client.setRequestMethod("POST");
                client.setRequestProperty("Key", "Value");
                client.setDoOutput(true);


                OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                //byte[] bytes = ("Honka").getBytes();
                //outputPost.write(bytes);

                outputPost.flush();
                outputPost.close();

                int responseCode = client.getResponseCode();


                Log.v(TAG, Integer.toString(responseCode));

            }
            catch (MalformedURLException me){}
            catch (IOException io){}

            return null;
        }
    }

}
