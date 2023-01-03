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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ceschin.copyyourtag.models.TagModel;

import java.util.List;

public class TagAdapter extends BaseAdapter {
    Context context;
    List<TagModel> tagModels;


    public TagAdapter(Context ctx, List<TagModel> tagModels) {
        this.context = ctx;
        this.tagModels = tagModels;

    }

    @Override
    public int getCount() {
        return tagModels.size();
    }

    @Override
    public Object getItem(int position) {
        return tagModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tagModels.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TagModel currentTag = tagModels.get(position);

        View listItemVIew = convertView;
        if(listItemVIew == null){
            listItemVIew = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView tvTags = (TextView) listItemVIew.findViewById(R.id.tvViewTags);
        tvTags.setText(currentTag.getTags());

        Button btCopy = (Button) listItemVIew.findViewById(R.id.btCopy);

        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setClipboard(context, tvTags.getText().toString());
            }
        });

        return listItemVIew;
    }

    private void setClipboard(Context ctx, String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(context, ClipboardManager.class);
        ClipData clip = ClipData.newPlainText("Copied tags", text);
        clipboard.setPrimaryClip(clip);

        makeToast(ctx, "Tags copied");

    }

    private void makeToast (Context ctx, String text){
        Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
