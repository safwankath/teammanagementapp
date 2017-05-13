package com.example.safwankath.stallionshockey;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Safwan Kath on 2017-05-07.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    // you may separate this or combined to caller class.
    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker (AsyncResponse delegate, Context ctx){
        this.delegate = delegate;
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://ms-kath.000webhostapp.com/login.php";
        if (type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                /*HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);*/
                Object[] arr = httpInfo(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)arr[4];
                OutputStream outputStream = (OutputStream) arr[1];
                BufferedWriter bufferedWriter = (BufferedWriter) arr[0];

                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // get response
                InputStream inputStream = (InputStream) arr[2];
                BufferedReader bufferedReader = (BufferedReader) arr[3];
                String result="";
                String line="";
                while ((line = bufferedReader.readLine()) != null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    protected Object[] httpInfo(URL input_url){
        Object[] arr = new Object[2];
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) input_url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            arr[0]=bufferedWriter;
            arr[1]=outputStream;

            // get response
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            arr[2]=inputStream;
            arr[3]=bufferedReader;
            arr[4]=httpURLConnection;
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return arr;

    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        delegate.processFinish(result);
    }
}
