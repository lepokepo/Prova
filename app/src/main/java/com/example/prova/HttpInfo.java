package com.example.prova;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpInfo extends AsyncTask<Void, Void, String> {
    String cep;
    public HttpInfo(String cep) {
        this.cep = cep;
    }



    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL site = new URL("http://viacep.com.br/ws/" + this.cep + "/json");
            HttpURLConnection myConnection =
                    (HttpURLConnection) site.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.connect();
            InputStream inputStream = myConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String a;
            while ((a = reader.readLine()) != null) {
                buffer.append(a);
            }
            if (buffer.length() == 0) {
                return null;
            }
            if (myConnection != null) {
                myConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("Erro", "Erro fechando o stream", e);
                }
            }
            return buffer.toString();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}