package com.example.buoi4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<ToDoItem> arrayList = new ArrayList<>();
    ToDoListAdapter adapter;
    ActivityResultLauncher<Intent> new_launcher;
    ActivityResultLauncher<Intent> edit_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new ToDoListAdapter(this, R.layout.to_do_item_view, arrayList);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        new_launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();

                        arrayList.add((ToDoItem) intent.getSerializableExtra("newItem"));
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });
        edit_launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        ToDoItem todo = (ToDoItem) intent.getSerializableExtra("editItem");
                        arrayList.set(intent.getIntExtra("position", 0), todo);
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.itemMenu:
                Intent intent = new Intent(MainActivity.this, NewToDoItem.class);
                intent.putExtra("name", "new");
                new_launcher.launch(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listView)
        {
            getMenuInflater().inflate(R.menu.menu_choice, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.itemEdit:
                Intent intent = new Intent(MainActivity.this, NewToDoItem.class);
                intent.putExtra("name", "edit");
                intent.putExtra("position", info.position);
                intent.putExtra("selectedItem", arrayList.get(info.position));
                edit_launcher.launch(intent);
                break;
            case R.id.itemDelete:
                arrayList.remove(info.position);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;
            case R.id.itemDone:
                ToDoItem markItem = arrayList.get(info.position);
                markItem.setDone(true);
                arrayList.set(info.position, markItem);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}