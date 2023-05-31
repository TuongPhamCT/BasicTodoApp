package com.example.buoi4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class NewToDoItem extends AppCompatActivity {
    Button saveButton;
    EditText edText1;
    EditText editText2;
    DatePicker abc;
    CheckBox doneCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_to_do_item_screen);

        saveButton = (Button) findViewById(R.id.saveBtn);
        edText1 = (EditText) findViewById(R.id.titleed);
        editText2 = (EditText) findViewById(R.id.desced);
        abc = (DatePicker)findViewById(R.id.datePK);
        doneCB = (CheckBox)findViewById(R.id.doneCB);

        Intent intent = getIntent();
        if (intent.getStringExtra("name").equals("new"))
        {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(NewToDoItem.this, MainActivity.class);
                    ToDoItem item = new ToDoItem(edText1.getText().toString(), editText2.getText().toString(), abc.getDayOfMonth(), abc.getMonth(), abc.getYear(), doneCB.isChecked());
                    intent.putExtra("newItem", item);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
        if (intent.getStringExtra("name").equals("edit"))
        {
            ToDoItem todo = (ToDoItem) intent.getSerializableExtra("selectedItem");
            edText1.setText(todo.getTitle());
            editText2.setText(todo.getDescription());
            abc.updateDate(todo.getYear(), todo.getMonth(), todo.getDay());
            doneCB.setChecked(todo.getDone());

            int position = intent.getIntExtra("position", 0);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(NewToDoItem.this, MainActivity.class);
                    ToDoItem item = new ToDoItem(edText1.getText().toString(), editText2.getText().toString(), abc.getDayOfMonth(), abc.getMonth(), abc.getYear(), doneCB.isChecked());
                    intent.putExtra("editItem", item);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }

    }
}