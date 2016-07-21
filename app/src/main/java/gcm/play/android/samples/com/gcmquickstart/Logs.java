package gcm.play.android.samples.com.gcmquickstart;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by mahma on 5/17/2016.
 */
public class Logs extends Activity {
    TextView textView = null;
    ArrayList<LogsData> arrayList = new ArrayList<LogsData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        textView = (TextView) findViewById(R.id.textview_call);
        getCallDetails();
    }

    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        LogsData callLogs = new LogsData();
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
		/* Query the CallLog Content Provider */
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, strOrder);
        int Phnum = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int Calltype = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");
        while (managedCursor.moveToNext()) {
            callLogs.setNumber(managedCursor.getString(Phnum));
            String strcallDate = managedCursor.getString(date);
            Date callDate = new Date(Long.valueOf(strcallDate));
            String myDate = new String();
            myDate = callDate.toString();
            callLogs.setDate(myDate);


            callLogs.setDuration(managedCursor.getString(duration));

            String callType = null;
            String Type = managedCursor.getString(Calltype);
            int callcode = Integer.parseInt(Type);
            switch (callcode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    callLogs.setType("Outgoing");
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    callLogs.setType("Incoming");
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    callLogs.setType("Missed");
                    break;
            }
            sb.append("\nPhone Number:--- " + callLogs.getNumber() + " \nCall Type:--- "
                    + callLogs.getType() + " \nCall Date:--- " + callLogs.getDate()
                    + " \nCall duration in sec :--- " + callLogs.getDuration());
            sb.append("\n----------------------------------");
//============================== arrayList for logs object ====================
            arrayList.add(callLogs);
        }
        managedCursor.close();
        textView.setText(sb);
    }
}