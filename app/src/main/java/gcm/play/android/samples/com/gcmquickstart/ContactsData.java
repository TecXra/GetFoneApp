package gcm.play.android.samples.com.gcmquickstart;

/**
 * Created by mahma on 5/17/2016.
 */
public class ContactsData {
    private String number;
    // SMS text name
    private String name;

 public ContactsData(){

    }



   public ContactsData(String number, String name ){
        this.number=number;
        this.name=name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
