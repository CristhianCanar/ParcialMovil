package modelo;

public class Bicicleta {
    private String _id;
    private String serial;
    private String color_principal;
    private String color_secundario;
    private String marca;
    private String tipo_bicicleta;
    private String uri_imagen;

    public Bicicleta(String _id, String serial, String color_principal, String color_secundario, String marca, String tipo_bicicleta, String uri_imagen) {
        this._id = _id;
        this.serial = serial;
        this.color_principal = color_principal;
        this.color_secundario = color_secundario;
        this.marca = marca;
        this.tipo_bicicleta = tipo_bicicleta;
        this.uri_imagen = uri_imagen;
    }

    public Bicicleta(String serial, String color_principal, String color_secundario, String marca, String tipo_bicicleta, String uri_imagen) {
        this.serial = serial;
        this.color_principal = color_principal;
        this.color_secundario = color_secundario;
        this.marca = marca;
        this.tipo_bicicleta = tipo_bicicleta;
        this.uri_imagen = uri_imagen;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getColor_principal() {
        return color_principal;
    }

    public void setColor_principal(String color_principal) {
        this.color_principal = color_principal;
    }

    public String getColor_secundario() {
        return color_secundario;
    }

    public void setColor_secundario(String color_secundario) {
        this.color_secundario = color_secundario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo_bicicleta() {
        return tipo_bicicleta;
    }

    public void setTipo_bicicleta(String tipo_bicicleta) {
        this.tipo_bicicleta = tipo_bicicleta;
    }

    public String getUri_imagen() {
        return uri_imagen;
    }

    public void setUri_imagen(String uri_imagen) {
        this.uri_imagen = uri_imagen;
    }
}
