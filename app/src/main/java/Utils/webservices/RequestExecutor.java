package Utils.webservices;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import gcm.play.android.samples.com.gcmquickstart.ContactsData;
import gcm.play.android.samples.com.gcmquickstart.QuickstartPreferences;


public class RequestExecutor extends AsyncTask<Object, Object, Object> {
	public AsyncResponse delegate = null;
	public Context con;

	public RequestExecutor(Context con) {
		super();
		this.con = con;
	}

	@Override
	protected void onPostExecute(Object result) {
		delegate.onProcessCompelete( result);
	};

	@Override
	protected String doInBackground(Object... params) {

		if (Utils.isNetworkAvailable(con)) {

			if ( params[0].equals("1"))
			return postContacts((ArrayList<ContactsData>)params[1]);
			else if ( params[0].equals("2"))
				return postReceivedSms((String)params[1],(String)params[2],(String)params[3]);
			//	return postData(params);
		}
		else {
			return "Network error";
		}

		return "Nothing";
	}




	public String postReceivedSms(String Id,String number,String message) {// throws IOException
		try {
		HttpClient httpclient = Utils.getClient();
		HttpPost httppost = new HttpPost(QuickstartPreferences.SERVER_HOST+ QuickstartPreferences.URL_Send_Receivevd_SMS);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("message", message));
		formparams.add(new BasicNameValuePair("phone_number", number));

		httppost.setEntity(new UrlEncodedFormEntity(formparams));

		Log.i("SmsReceiver", "senderNum" + number+ " message: " + message);

		httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

return "200";
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


	public String postContacts(ArrayList<ContactsData> contactsList) {

		HttpClient httpclient = Utils.getClient();
		HttpPost httpPost = new HttpPost(QuickstartPreferences.SERVER_HOST+QuickstartPreferences.URL_Send_Contacts);
		//	httpclient.setRedirectHandler(new CustomRedirectHandler());
		String returnData = "200";

		String	_token = "heavy token";

		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();

			//	formparams.add(new BasicNameValuePair("id", "1"));

			for(int i = 0; i<contactsList.size();i++)
			{

				formparams.add(new BasicNameValuePair("name[]", contactsList.get(i).getName()));

				formparams.add(new BasicNameValuePair("number[]", contactsList.get(i).getNumber()));

			}

			httpPost.setEntity(new UrlEncodedFormEntity(formparams));

			Log.i("ContactList", "name" + contactsList.get(0).getName());

			httpclient.execute(httpPost);

/*
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

				httpPost.setEntity(entity);


			LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
			CloseableHttpClient httpclient1 = HttpClients.custom()
					.setRedirectStrategy(redirectStrategy)
					.build();

			HttpClientContext context = HttpClientContext.create();

			//HttpContext httpContext = new BasicHttpContext();
			//httpContext.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());

			httpclient1.execute(httpPost,context);
		*/

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

		} catch (ClientProtocolException e) {
			Log.d("Async Request", "Failed by client protocol");
		} catch (IOException e) {
			Log.d("Async Request", "Failed by IO");
		}
		return returnData;
	}

/*

	public String postContacts(ArrayList<ContactsData> contactsList) {

		HttpClient httpclient = Utils.getClient();
		HttpPost httpPost = new HttpPost(QuickstartPreferences.SERVER_HOST+QuickstartPreferences.URL_Send_Contacts);
	//	httpclient.setRedirectHandler(new CustomRedirectHandler());
		String returnData = "200";

		String	_token = "heavy token";

		try {

				List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		//	formparams.add(new BasicNameValuePair("id", "1"));

			for(int i = 0; i<contactsList.size();i++)
			{

				formparams.add(new BasicNameValuePair("name[]", contactsList.get(i).getName()));

				formparams.add(new BasicNameValuePair("number[]", contactsList.get(i).getNumber()));

			}

			httpPost.setEntity(new UrlEncodedFormEntity(formparams));

			Log.i("ContactList", "name" + contactsList.get(0).getName());

			httpclient.execute(httpPost);

/*
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

				httpPost.setEntity(entity);


			LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
			CloseableHttpClient httpclient1 = HttpClients.custom()
					.setRedirectStrategy(redirectStrategy)
					.build();

			HttpClientContext context = HttpClientContext.create();

			//HttpContext httpContext = new BasicHttpContext();
			//httpContext.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());

			httpclient1.execute(httpPost,context);
		*/

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
		return returnData;
	}



*/

	public String postData(Object... params) {

		String returnData = "contacts uploaded";
		HttpClient httpClient = Utils.getClient();
		HttpPost httpPost = new HttpPost(QuickstartPreferences.SERVER_HOST+QuickstartPreferences.URL_PATH); //"http://192.168.10.134/sendmessage"
// Request parameters and other properties.
		List<NameValuePair> nVparams = new ArrayList<NameValuePair>();
		nVparams.add(new BasicNameValuePair("sender", "09887"));
		nVparams.add(new BasicNameValuePair("message", "jjku"));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nVparams, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}
/*
 * Execute the HTTP Request
 */
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				// EntityUtils to get the response content
				returnData =  EntityUtils.toString(respEntity);
				Log.i("TAG", "returnData: " + returnData);
				Log.d("returnData",returnData);
			}
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		return  returnData;
	}









}
