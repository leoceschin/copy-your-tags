package com.ceschin.copyyourtag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ceschin.copyyourtag.db.TagRepository;
import com.ceschin.copyyourtag.models.TagModel;

public class AddActivity extends AppCompatActivity {

    EditText etTags;
    Button addTags, deleteButton;

    TagModel tagModel;
    TagRepository tagRepository;
    boolean isInsert = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTags = (EditText) findViewById(R.id.etTags);
        deleteButton = (Button) findViewById(R.id.btDeleteTags);
        addTags = (Button) findViewById(R.id.btAddTags);

        tagModel = new TagModel();
        tagRepository = new TagRepository(getApplicationContext());


        Intent it = getIntent();

        if(it.hasExtra("tags")){
            tagModel = (TagModel) it.getSerializableExtra("tags");
            isInsert = false;
            //deleteButton.setClickable(true);
            deleteButton.setEnabled(true);
            etTags.setText(tagModel.getTags().toString());

        }

        addTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagModel.setTags(etTags.getText().toString());
                save(tagModel, isInsert);

                Toast.makeText(getApplicationContext(), "Tags saved", Toast.LENGTH_SHORT)
                        .show();


                Intent it = new Intent(AddActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("info: ", tagModel.toString());
                tagRepository.deleteTag(tagModel);

                Toast.makeText(getApplicationContext(), "Tags deleted", Toast.LENGTH_SHORT)
                        .show();

                Intent it = new Intent(AddActivity.this, MainActivity.class);
                startActivity(it);
            }
        });


    }

    private void save(TagModel tagModel, boolean isInsert){
        if(!isInsert){
            tagRepository.updateTag(tagModel);
        }else{
            tagRepository.insert(tagModel);
        }
    }
}