package com.ceschin.copyyourtag;

import static android.support.v4.content.ContextCompat.getSystemService;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ceschin.copyyourtag.models.TagModel;

import java.util.ArrayList;
import java.util.List;

public class TagAdapter extends ArrayAdapter<TagModel> {
    public TagAdapter(Context context, List<TagModel> tagModels, int resource) {
        super(context, 0, tagModels);

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemVIew = convertView;
        if(listItemVIew == null){
            listItemVIew = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TagModel currentTag = getItem(position);
        TextView tvTags = (TextView) listItemVIew.findViewById(R.id.tvViewTags);
        tvTags.setText(currentTag.getTags());

        Button btCopy = (Button) listItemVIew.findViewById(R.id.btCopy);

        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setClipboard(getContext(), tvTags.getText().toString());
            }
        });

        return listItemVIew;
    }

    private void setClipboard(Context ctx, String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(getContext(), ClipboardManager.class);
        ClipData clip = ClipData.newPlainText("Copied tags", text);
        clipboard.setPrimaryClip(clip);

    }
}
