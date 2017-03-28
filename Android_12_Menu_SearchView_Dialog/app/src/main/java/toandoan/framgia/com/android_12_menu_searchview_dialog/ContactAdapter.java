package toandoan.framgia.com.android_12_menu_searchview_dialog;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 13/04/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Contact> mContacts;

    public Context getContext() {
        return mContext;
    }

    private Context mContext;

    public ContactAdapter(
        List<Contact> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mContacts.get(position));
        holder.mButtonPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(),holder.mButtonPopupMenu);
                popup.inflate(R.menu.contact_context_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                Toast.makeText(getContext(),"Edit Button Pressed",Toast
                                    .LENGTH_SHORT).show();
                                break;
                            case R.id.menu_delete:
                                mContacts.remove(position);
                                ContactAdapter.this.notifyItemRemoved(position);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts != null ? mContacts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextName;
        private TextView mTextPhone;
        private TextView mTextAddress;
        private ImageButton mButtonPopupMenu;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.text_phone);
            mTextAddress = (TextView) itemView.findViewById(R.id.text_address);
            mButtonPopupMenu = (ImageButton) itemView.findViewById(R.id.button_popup_menu);
        }

        public void bindData(Contact contact) {
            if (contact == null) return;
            mTextName.setText(contact.getName());
            mTextAddress.setText(contact.getAddress());
            mTextPhone.setText(contact.getPhone());
        }
    }
}
