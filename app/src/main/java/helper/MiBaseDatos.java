package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.Bicicleta;


public class MiBaseDatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "almacen.db";
    //TABLA_BICICLETA
    public static final String TABLA_BICICLETA = "bicicleta";
    public static final String COLUMNA_BICICLETA_ID = "_id";
    public static final String COLUMNA_BICICLETA_SERIAL = "serial";
    public static final String COLUMNA_BICICLETA_COLOR_PRINCIPAL = "color_principal";
    public static final String COLUMNA_BICICLETA_COLOR_SECUNDARIO = "color_secundario";
    public static final String COLUMNA_BICICLETA_MARCA = "marca";
    public static final String COLUMNA_BICICLETA_TIPO = "tipo_bicicleta";
    public static final String COLUMNA_BICICLETA_URL_IMAGEN = "uri_imagen";

    public static final String SQL_CREAR_TABLA_BICICLETA = "create table " + TABLA_BICICLETA +
            " ( "+COLUMNA_BICICLETA_ID+" integer primary key autoincrement, "
                +COLUMNA_BICICLETA_SERIAL+" text not null, "
                +COLUMNA_BICICLETA_COLOR_PRINCIPAL+" text not null, "
                +COLUMNA_BICICLETA_COLOR_SECUNDARIO+" text not null, "
                +COLUMNA_BICICLETA_MARCA+" text not null, "
                +COLUMNA_BICICLETA_TIPO+" text not null, "
                +COLUMNA_BICICLETA_URL_IMAGEN+" text not null);";

    private static MiBaseDatos miBaseDatos;
    public static MiBaseDatos getInstance(Context context){
        if(miBaseDatos == null){
            miBaseDatos = new MiBaseDatos(context);
        }
        return miBaseDatos;
    }

    public MiBaseDatos(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_BICICLETA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long crearBicicleta(Bicicleta bicicleta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_BICICLETA_SERIAL,bicicleta.getSerial());
        values.put(COLUMNA_BICICLETA_COLOR_PRINCIPAL,bicicleta.getColor_principal());
        values.put(COLUMNA_BICICLETA_COLOR_SECUNDARIO,bicicleta.getColor_secundario());
        values.put(COLUMNA_BICICLETA_MARCA,bicicleta.getMarca());
        values.put(COLUMNA_BICICLETA_TIPO,bicicleta.getTipo_bicicleta());
        values.put(COLUMNA_BICICLETA_URL_IMAGEN,bicicleta.getUri_imagen());

        long inserto = db.insert(TABLA_BICICLETA,null,values);

        db.close();

        return inserto;
    }


    public Cursor leerBicicletas(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { COLUMNA_BICICLETA_ID,
                                COLUMNA_BICICLETA_SERIAL,
                                COLUMNA_BICICLETA_COLOR_PRINCIPAL,
                                COLUMNA_BICICLETA_COLOR_SECUNDARIO,
                                COLUMNA_BICICLETA_MARCA,
                                COLUMNA_BICICLETA_TIPO,
                                COLUMNA_BICICLETA_URL_IMAGEN};
        //List<Entidad> lista = new ArrayList<Entidad>();

        Cursor cursor = db.query(TABLA_BICICLETA, projection, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int actualizarBicicleta(Bicicleta bicicleta){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(COLUMNA_BICICLETA_SERIAL,bicicleta.getSerial());
        values.put(COLUMNA_BICICLETA_COLOR_PRINCIPAL,bicicleta.getColor_principal());
        values.put(COLUMNA_BICICLETA_COLOR_SECUNDARIO,bicicleta.getColor_secundario());
        values.put(COLUMNA_BICICLETA_MARCA,bicicleta.getMarca());
        values.put(COLUMNA_BICICLETA_TIPO,bicicleta.getTipo_bicicleta());
        values.put(COLUMNA_BICICLETA_URL_IMAGEN,bicicleta.getUri_imagen());

        int i = db.update(TABLA_BICICLETA, values," serial = "+bicicleta.getSerial(),null);
        db.close();
        return i;
    }

    public boolean eliminarBicicleta(String serial){
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(TABLA_BICICLETA, " serial = "+serial,null);
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}
