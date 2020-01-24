package es.guillemburnleesviada.repasoormlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import es.guillemburnleesviada.repasoormlite.adapters.AdapterOrdenador;
import es.guillemburnleesviada.repasoormlite.pojo.Ordenador;
import es.guillemburnleesviada.repasoormlite.sqlite.OrmHelper;

public class MainActivity extends AppCompatActivity {

    // Recycler View
    private RecyclerView recyclerView;
    private int resource = R.layout.card_ordenador;
    private AdapterOrdenador adapter;
    private ArrayList<Ordenador> ordenadores;

    //BD ORM Helper
    private OrmHelper helper;
    private Dao ordenadoresDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        helper = OpenHelperManager.getHelper(this, OrmHelper.class);

        try {
            ordenadoresDao = helper.getOrdenadorDAO();
            ordenadores = (ArrayList<Ordenador>) ordenadoresDao.queryForAll(); // devuelve un list normal
        } catch (SQLException e) {
            ordenadores = new ArrayList<>();
        }

        adapter = new AdapterOrdenador(this, resource, ordenadores);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearOrdenador().show();
            }
        });
    }

    private AlertDialog crearOrdenador(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.alert_ordenador, null);

        final EditText txtMarca = layout.findViewById(R.id.txtMarcaAlert);
        final EditText txtModelo = layout.findViewById(R.id.txtModeloAlert);
        final EditText txtRAM = layout.findViewById(R.id.txtRamAlert);
        final EditText txtHD = layout.findViewById(R.id.txtHdAlert);
        final RatingBar rbAlert = layout.findViewById(R.id.ratingBarAlert);

        builder.setTitle("Crear Ordenador");
        builder.setNeutralButton("Cerrar", null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String marca = txtMarca.getText().toString();
                String modelo = txtModelo.getText().toString();
                int RAM = Integer.parseInt(txtRAM.getText().toString());
                int HD = Integer.parseInt(txtHD.getText().toString());
                float rating = rbAlert.getRating();

                Ordenador ordenador = new Ordenador(marca, modelo, RAM, HD, rating);

                try {
                    ordenadoresDao.create(ordenador);
                    ordenadores.add(ordenador);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    Toast.makeText(MainActivity.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(layout);

        return builder.create();
    }

}
