package toandoan.framgia.com.android_12_menu_searchview_dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.local.ContactDataSource;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,
            View.OnClickListener {
        private TextView mTextName;
        private TextView mTextPhone;
        private TextView mTextAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.text_phone);
            mTextAddress = (TextView) itemView.findViewById(R.id.text_address);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void bindData(Contact contact) {
            if (contact == null) return;
            mTextName.setText(contact.getName());
            mTextAddress.setText(contact.getAddress());
            mTextPhone.setText(contact.getPhone());
        }

        @Override
        public boolean onLongClick(final View view) {
            final ContactDataSource dataSource = new ContactDataSource(view.getContext());
            // show confirm dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.delete)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setMessage(R.string.msg_confirm_delete)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // TODO: 18/04/2017 delete item
                            Contact contact = mContacts.get(getAdapterPosition());
                            boolean deleteResult = dataSource.deleteContactById(contact.getId());
                            if (deleteResult) {
                                mContacts.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                            } else {
                                Toast.makeText(view.getContext(), "Delete contact failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .create()
                    .show();

            return false;
        }

        @Override
        public void onClick(View view) {
            ContactDataSource contactDataSource = new ContactDataSource(view.getContext());
            Contact contact = mContacts.get(getAdapterPosition());
            contact.setName("UPDATED ");
            contact.setPhone("UPDATED ");
            contact.setAddress("UPDATE ");
            if (contactDataSource.updateContact(contact)){
                // update thanfh cong
                notifyDataSetChanged();
            }else {
                // update k thanh cong
                Toast.makeText(view.getContext(), "Update failed", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
