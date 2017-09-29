package example.com.linkedintest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Henrik on 2017-09-01.
 */

public class PostAsync extends AsyncTask<Void, Void, Void> {

    private final String TAG = PostAsync.class.getSimpleName();

    private URL url;

    public PostAsync(URL url){
        this.url = url;

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            HttpURLConnection client = null;
            client = (HttpURLConnection) url.openConnection();

            client.setRequestMethod("POST");
            client.setRequestProperty("Key", "Value");
            client.setDoOutput(true);

            OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
            byte[] bytes = ("Honka").getBytes();
            outputPost.write(bytes);

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
