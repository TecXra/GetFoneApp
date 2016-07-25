/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gcm.play.android.samples.com.gcmquickstart;
import java.io.IOException;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import Utils.webservices.*;


public class QuickstartPreferences implements AsyncResponse {


    public static RequestExecutor executor ;
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String Token = "Token";

    public static final String SERVER_HOST = "http://192.168.8.100";
    public static final String URL_PATH = "/cloud";
    public static final String URL_Send_Receivevd_SMS = "/storerecieveconversation";

    public static final String URL_Token_Update = "/tokenupdate";

    public static final String URL_Send_Contacts = "/storecontacts";
    public static HttpClient httpclient;

    @Override
    public void onProcessCompelete(Object result) {

    }

    public static void sendTokenToServer(String _token)
    {


       HttpClient httpclient = HttpClientBuilder.create().build();//HttpClients.createDefault();//


        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("sender", "456"));
            formparams.add(new BasicNameValuePair("message", _token));
        //    formparams.add(new BasicNameValuePair("messaage", "value2"));



            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            //	entity.setContentType("application/x-www-form-urlencoded");
            //	entity.setChunked(true);

            HttpPost httpPost = new HttpPost(SERVER_HOST + URL_PATH);
            //	httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setEntity(entity);


            HttpResponse response = httpclient.execute(httpPost);
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


*/
        }


        catch (ClientProtocolException e) {

        }
        catch (IOException e) {

        }




    /*
        try {
        // Create connection to send GCM Message request.
        URL url = new URL(SERVER_HOST + URL_PATH);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

     //   conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
     //   conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
        // Send GCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jGcmData.toString().getBytes());

        // Read GCM response.
        InputStream inputStream = conn.getInputStream();
        String resp = IOUtils.toString(inputStream);
        System.out.println(resp);
        System.out.println("Check your device/emulator for notification or logcat for " +
                "confirmation of the receipt of the GCM message.");
    } catch (IOException e) {
        System.out.println("Unable to send GCM message.");
        System.out.println("Please ensure that API_KEY has been replaced by the server " +
                "API key, and that the device's registration token is correct (if specified).");
        e.printStackTrace();
    }
*/
    }


}
