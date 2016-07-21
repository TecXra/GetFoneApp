package gcm.play.android.samples.com.gcmquickstart;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import Utils.webservices.GetFoneContactModel;

public class ContactTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_test);

        ArrayList<ContactsData> contacts = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            contacts.add( new ContactsData(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))));

        }
        phones.close();








    }
}
