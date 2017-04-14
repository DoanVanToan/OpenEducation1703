package toandoan.framgia.com.android_12_menu_searchview_dialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 13/04/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Contact> mContacts;

    public ContactAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View v = mLayoutInflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts != null ? mContacts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextName;
        private TextView mTextPhone;
        private TextView mTextAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.text_phone);
            mTextAddress = (TextView) itemView.findViewById(R.id.text_address);
        }

        public void bindData(Contact contact) {
            if (contact == null) return;
            mTextName.setText(contact.getName());
            mTextAddress.setText(contact.getAddress());
            mTextPhone.setText(contact.getPhone());
        }
    }
}
