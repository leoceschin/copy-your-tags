package com.ceschin.copyyourtag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ceschin.copyyourtag.db.TagRepository;
import com.ceschin.copyyourtag.models.TagModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    ListView listViewTags;

    TagRepository tagRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.btAdd);
        listViewTags = (ListView) findViewById(R.id.lvList);

        tagRepository = new TagRepository(getApplicationContext());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, AddActivity.class);
                startActivity(it);
            }
        });
        try{
            List<TagModel> tagsList;
            tagsList = tagRepository.getAllTags();
            if(tagsList != null){
                TagAdapter adapter = new TagAdapter(this, tagsList, 0);
                listViewTags.setAdapter(adapter);
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }





    }
}