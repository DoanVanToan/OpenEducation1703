package toandoan.framgia.com.android_12_menu_searchview_dialog.data.model;

/**
 * Created by framgia on 13/04/2017.
 */

public class Contact {
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(String name, String phone, String address) {
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
