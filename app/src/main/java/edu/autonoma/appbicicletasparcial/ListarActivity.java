package edu.autonoma.appbicicletasparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import adapter.CursorAdapter;
import helper.MiBaseDatos;

public class ListarActivity extends AppCompatActivity {
    private CursorAdapter cursorAdapter;
    private Cursor cursorBicis;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listView =findViewById(R.id.lista);
        cursorBicis = MiBaseDatos.getInstance(this).leerBicicletas();
        cursorAdapter = new CursorAdapter(this, cursorBicis,0);
        listView.setAdapter(cursorAdapter);
    }
}