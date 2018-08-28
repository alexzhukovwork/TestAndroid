package com.test.evaluationtestandroidjava.helpers;

import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;

/**
 * Created by Алексей on 24.08.2018.
 */

public class APIConnector {

    private static String executeQuery(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = client.execute(httpPost);
        InputStream in = response.getEntity().getContent();
        String body = convertStreamToString(in);
        client.close();

        return body;
    }

    public static String executeSongsQuery(String id) throws IOException {
        return executeQuery("https://itunes.apple.com/lookup?id=" + id + "&entity=song&sort=alphabet");
    }

    public static String executeAlbumsQuery(String name) throws IOException {
        name = name.replace(" ", "+");
        return executeQuery("https://itunes.apple.com/search?term=" + name + "&entity=album");
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
