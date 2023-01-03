package com.ceschin.copyyourtag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ceschin.copyyourtag.db.TagRepository;
import com.ceschin.copyyourtag.models.TagModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        final List<TagModel> tagsList;
        try{
            tagsList = tagRepository.getAllTags();
            if(tagsList != null){
                TagAdapter adapter = new TagAdapter(this, tagsList);
                listViewTags.setAdapter(adapter);
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }


        listViewTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TagModel tagModel = (TagModel)adapterView.getItemAtPosition(position);

                Intent it = new Intent(MainActivity.this, AddActivity.class);
                it.putExtra("tags", tagModel);

                startActivity(it);


            }
        });



    }
}