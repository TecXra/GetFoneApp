package gcm.play.android.samples.com.gcmquickstart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import android.widget.Toast;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import Utils.webservices.Utils;
import Utils.webservices.RequestExecutor;
import Utils.webservices.AsyncResponse;

public class ReplyReceive extends BroadcastReceiver implements AsyncResponse {
    ArrayList<String> list  = new  ArrayList<String>();
    int duration = Toast.LENGTH_SHORT;
    Toast toast;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    @Override
   public  void onProcessCompelete(Object result)
    {



    }
    public void onReceive(Context context, Intent intent) {
        String senderNum = "";
        String message = "";

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    senderNum = currentMessage.getDisplayOriginatingAddress();


                    message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration).show();

                   // SendReciveMessage(message,senderNum,context);


                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);




                } // end for loop

                RequestExecutor executor = new RequestExecutor(context);
                executor.delegate = ReplyReceive.this;
                executor.execute("2","1",senderNum, message);


            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }

    //    clearAbortBroadcast();
   //     abortBroadcast();


    }


    public void SendReciveMessage(String message,String phoneNumber,Context context) throws IOException {

        HttpClient httpclient = Utils.getClient();
        HttpPost httppost = new HttpPost("http://getfone.dev/storerecieveconversation");

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("message", message));
            formparams.add(new BasicNameValuePair("phone_number", phoneNumber));

            httppost.setEntity(new UrlEncodedFormEntity(formparams));

            Log.i("SmsReceiver", "senderNum: "+ phoneNumber + " message: " + message);

            httpclient.execute(httppost);

/*

        HttpClient httpclient = Utils.getClient();

        // Web URL

        HttpPost httpPost = new HttpPost("http://192.168.1.103/storerecieveconversation");
        //	httpclient.setRedirectHandler(new CustomRedirectHandler());
        String returnData = "nothing..";
        String	_token = "heavy token";
        try {

            // Data to send

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("message", message));
            formparams.add(new BasicNameValuePair("phone_number", phoneNumber));

            //Content Type

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);

            //	entity.setChunked(true);
           //	HttpPost httpPost = new HttpPost(QuickstartPreferences.SERVER_HOST + QuickstartPreferences.URL_PATH);
           // httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setEntity(entity);


            LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
            CloseableHttpClient httpclient1 = HttpClients.custom()
                    .setRedirectStrategy(redirectStrategy)
                    .build();
            HttpClientContext context = HttpClientContext.create();
            httpclient.execute(httpPost,context);


            //HttpContext httpContext = new BasicHttpContext();
            //httpContext.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());

            //	HttpResponse response = httpclient.execute(httpPost);
            //httpclient.execute(httpPost);

            //Log.d("resone","response is :"+response.toString()+"data is:"+response.getStatusLine());
            //	returnData = EntityUtils.toString(response.getEntity());


            /*
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            System.out.println("Response Enity : \n"
                    + response.getEntity());

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                responseBuffer.append(inputLine+"\n");
            }
            reader.close();

            // print result
            System.out.println(responseBuffer.toString());







        } catch (ClientProtocolException e) {
            Log.d("Async Request", "Failed by client protocol");
        } catch (IOException e) {
            Log.d("Async Request", "Failed by IO");
        }

*/

    }


}











    /*
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "sms received..", Toast.LENGTH_LONG).show();
        final Bundle bundle = intent.getExtras();

        try {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            SmsMessage receivedtMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
            String senderPhoneNumber = receivedtMessage.getDisplayOriginatingAddress();





            Toast.makeText(context, "sms received from: " + senderPhoneNumber, Toast.LENGTH_LONG).show();
            //--
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }*/

