package Utils.webservices;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import gcm.play.android.samples.com.gcmquickstart.ContactsData;
import gcm.play.android.samples.com.gcmquickstart.R;

/**
 * Created by Ali ( 03154342359 ) on 7/20/2016.
 */
public class GetFoneContactModel
{

ArrayList<ContactsData> contacts;



    public ArrayList<ContactsData> getContacts(Context context)
{
    ArrayList<ContactsData> contactsList = new ArrayList<ContactsData>();

    //get phone contacts from mobile db
    Cursor phones =context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
    while (phones.moveToNext())
    {
        contactsList.add( new ContactsData(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))));

    }
    phones.close();


    return contactsList;
}

    ArrayList<String> contactList;
    Cursor cursor;
    int counter;
   public ArrayList<ContactsData> arrayList = new ArrayList<ContactsData>();
    public GetFoneContactModel() {

    }

    public GetFoneContactModel(Context context) {
        getContact(context);

    }

    public void getContact (Context context) {


        contactList = new ArrayList<String>();
        ContactsData contacts = new ContactsData();


        String phoneNumber = null;
        String email = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        StringBuffer output;

        ContentResolver contentResolver =context.getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {

            counter = 0;
            while (cursor.moveToNext()) {
                output = new StringBuffer();
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                contacts.setName(cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    output.append("\n First Name:" + contacts.getName());

                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
                            new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        contacts.setNumber(phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)));
                        output.append("\n Phone number:" + contacts.getNumber());

                    }

                    phoneCursor.close();


                }
//===================================arrayList for contacts object ================================
                arrayList.add(contacts);
                // Add the contact to the ArrayList
                contactList.add(output.toString());
            }

        }


    }




}
