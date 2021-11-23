package edu.autonoma.appbicicletasparcial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import helper.MiBaseDatos;
import modelo.Bicicleta;

public class MainActivity extends AppCompatActivity {
    MiBaseDatos miBaseDatos;
    TextView tv_resultado;

    EditText et_id;
    EditText et_serial;
    EditText et_color_principal;
    EditText et_color_secundario;
    EditText et_marca;
    EditText et_tipo_bicicleta;
    EditText et_uri_imagen;

    String serial;
    String color_principal;
    String color_secundario;
    String marca;
    String tipo_bicicleta;
    String uri_imagen;

    String path="";

    String uri_image="sin_uri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_serial           = findViewById(R.id.et_serial);
        et_color_principal  = findViewById(R.id.et_color_principal);
        et_color_secundario = findViewById(R.id.et_color_secundario);
        et_marca            = findViewById(R.id.et_marca);
        et_tipo_bicicleta   = findViewById(R.id.et_tipo_bicicleta);
        tv_resultado        = findViewById(R.id.tv_resultado);

        miBaseDatos = MiBaseDatos.getInstance(getApplicationContext());
    }


    public void agregar(View view) {

        serial              = et_serial.getText().toString();
        color_principal     = et_color_principal.getText().toString();
        color_secundario    = et_color_secundario.getText().toString();
        marca               = et_marca.getText().toString();
        tipo_bicicleta      = et_tipo_bicicleta.getText().toString();
        uri_imagen          = uri_image;

        Bicicleta bicicleta = new Bicicleta(serial,
                                            color_principal,
                                            color_secundario,
                                            marca,
                                            tipo_bicicleta,
                                            uri_imagen);
        long resultado = miBaseDatos.crearBicicleta(bicicleta);
        tv_resultado.setText(""+resultado);
    }

    public void cargarImagen(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        /*
        if(intent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = crearArchivoFoto();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (photoFile != null){
                Uri fotoUri = FileProvider.getUriForFile(MainActivity.this,"edu.autonoma.appbicicletasparcial",photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
                startActivityForResult(intent,1);
            }
        }*/

        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),1);
    }

    private File crearArchivoFoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir);
        path = file.getAbsolutePath();
        return file;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            //Obtencion de la imagen
            Uri uri = data.getData();
            uri_image = String.valueOf(uri);
            Log.d("FOTO",uri_image);
            //Apuntamiento en el cloud storage de firebase
        }
    }

    public void listar(View view) {
        //tv_resultado.setText(miBaseDatos.mostrarDatosEntidad());
        Intent intent = new Intent(this, ListarActivity.class);
        startActivity(intent);
    }


    public void eliminar(View view){
        String serial = et_serial.getText().toString();
        boolean elimino = miBaseDatos.eliminarBicicleta(serial);
        if (elimino){
            Toast.makeText(this, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No fue posible eliminar", Toast.LENGTH_SHORT).show();
        }
        tv_resultado.setText(""+elimino);
    }

    public void actualizar(View view){
        String serial = et_serial.getText().toString();
        color_principal     = et_color_principal.getText().toString();
        color_secundario    = et_color_secundario.getText().toString();
        marca               = et_marca.getText().toString();
        tipo_bicicleta      = et_tipo_bicicleta.getText().toString();
        uri_imagen          = uri_image;

        Bicicleta bicicleta = new Bicicleta(serial,
                color_principal,
                color_secundario,
                marca,
                tipo_bicicleta,
                uri_imagen);
        int resultado = miBaseDatos.actualizarBicicleta(bicicleta);
        if (resultado > 0){
            Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No fue posible actualizar", Toast.LENGTH_SHORT).show();
        }
        tv_resultado.setText(""+resultado);
    }
}