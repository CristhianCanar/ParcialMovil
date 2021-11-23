package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.autonoma.appbicicletasparcial.R;
import helper.MiBaseDatos;

public class CursorAdapter extends android.widget.CursorAdapter {

    LayoutInflater cursorInflater;
    public CursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return cursorInflater.inflate(R.layout.item_bicicleta,viewGroup,false);
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_serial;
        TextView tv_color_principal;
        TextView tv_color_secundario;
        TextView tv_marca;
        TextView tv_tipo_bicicleta;
        TextView tv_uri_imagen;
        ImageView iv_image;

        String serial;
        String color_principal;
        String color_secundario;
        String marca;
        String tipo_bicicleta;
        String uri_imagen;

        tv_serial           = view.findViewById(R.id.tv_serial);
        tv_color_principal  = view.findViewById(R.id.tv_color_principal);
        tv_color_secundario = view.findViewById(R.id.tv_color_secundario);
        tv_marca            = view.findViewById(R.id.tv_marca);
        tv_tipo_bicicleta   = view.findViewById(R.id.tv_tipo_bicicleta);
        tv_uri_imagen       = view.findViewById(R.id.tv_uri_imagen);
        iv_image            = view.findViewById(R.id.iv_imagen);

        serial              = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_SERIAL));
        color_principal     = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_COLOR_PRINCIPAL));
        color_secundario    = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_COLOR_SECUNDARIO));
        marca               = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_MARCA));
        tipo_bicicleta      = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_TIPO));
        uri_imagen          = cursor.getString(cursor.getColumnIndex(MiBaseDatos.COLUMNA_BICICLETA_URL_IMAGEN));

        iv_image.setImageURI(Uri.parse(uri_imagen));
        tv_serial.setText("serial: "+serial);
        tv_color_principal.setText("color_principal: "+color_principal);
        tv_color_secundario.setText("color_secundario: "+color_secundario);
        tv_marca.setText("marca: "+marca);
        tv_tipo_bicicleta.setText("tipo_bicicleta: "+tipo_bicicleta);
        tv_uri_imagen.setText("uri_imagen: "+uri_imagen);

    }
}
