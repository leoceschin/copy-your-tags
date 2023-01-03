package com.ceschin.copyyourtag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ceschin.copyyourtag.db.TagRepository;
import com.ceschin.copyyourtag.models.TagModel;

public class AddActivity extends AppCompatActivity {

    EditText etTags;
    Button addTags;

    TagRepository tagRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTags = (EditText) findViewById(R.id.etTags);
        addTags = (Button) findViewById(R.id.btAddTags);

        TagModel tagModel = new TagModel();
        tagRepository = new TagRepository(getApplicationContext());

        addTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagModel.setTags(etTags.getText().toString());
                tagRepository.insert(tagModel);
            }
        });


    }
}