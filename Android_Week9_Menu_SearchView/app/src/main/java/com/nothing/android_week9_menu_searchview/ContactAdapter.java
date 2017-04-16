package com.nothing.android_week9_menu_searchview;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CDATASection;

import java.text.Normalizer;
import java.util.List;

/**
 * Created by THM on 4/13/2017.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> mListContact;

    public ContactAdapter(List<Contact> contactList) {
        mListContact = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent,
            false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mListContact.get(position));
    }

    @Override
    public int getItemCount() {
        return mListContact == null ? 0 : mListContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {
        private TextView mTextName, mTextNumber, mTextAddress;
        private ImageView mIconPop;
        private WorkingWithContact mInterfaceContact;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            mInterfaceContact = (WorkingWithContact) itemView.getContext();
            mIconPop = (ImageView) itemView.findViewById(R.id.image_pop);
            mIconPop.setOnClickListener(this);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextAddress = (TextView) itemView.findViewById(R.id.text_address);
            mTextNumber = (TextView) itemView.findViewById(R.id.text_number);
        }

        public void bindData(Contact item) {
            if (item != null) {
                String strSearch = item.getSearch();
                if (!strSearch.equals("")) {
                    mTextName.setText(highlight(strSearch, item.getName()));
                    mTextAddress.setText(highlight(strSearch, item.getAddress()));
                    mTextNumber.setText(highlight(strSearch, item.getNumber()));
                } else {
                    mTextName.setText(item.getName());
                    mTextAddress.setText(item.getAddress());
                    mTextNumber.setText(item.getNumber());
                }
            }
        }

        @Override
        public void onClick(View v) {
            mInterfaceContact.onMenuPop(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            final ContactDataSource db = new ContactDataSource(v.getContext());
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.warning)
                .setMessage(R.string.confirm_message)
                .setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isDeleted = db.deleteContact(mListContact.get(getAdapterPosition())
                            .getId());
                        if(isDeleted){
                            mListContact.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        } else {
                            Toast.makeText(null, "Can't delete", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, null);
            builder.show();
            return true;
        }
    }

    public interface WorkingWithContact {
        void onMenuPop(int position, View v);
    }

    public CharSequence highlight(String search, String originalText) {
        // ignore case and accents
        // the same thing should have been done for the search text
        String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        int start = normalizedText.indexOf(search);
        if (start < 0) {
            // not found, nothing to to
            return originalText;
        } else {
            // highlight each appearance in the original text
            // while searching in normalized text
            Spannable highlighted = new SpannableString(originalText);
            while (start >= 0) {
                int spanStart = Math.min(start, originalText.length());
                int spanEnd = Math.min(start + search.length(), originalText.length());
                highlighted
                    .setSpan(new BackgroundColorSpan(Color.GREEN), spanStart, spanEnd, Spannable
                        .SPAN_EXCLUSIVE_EXCLUSIVE);
                start = normalizedText.indexOf(search, spanEnd);
            }
            return highlighted;
        }
    }
}
