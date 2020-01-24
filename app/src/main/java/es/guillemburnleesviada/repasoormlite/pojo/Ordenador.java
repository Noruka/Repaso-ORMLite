package es.guillemburnleesviada.repasoormlite.pojo;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ordenadores") //Implementando ORMlite-android
public class Ordenador {

    @DatabaseField(generatedId = true, columnName = "id_ordenador") // Solo le pongo el column name si quiero poner otro nombre
    private int id;
    @DatabaseField(canBeNull = false)
    private String marca;
    @DatabaseField(canBeNull = false)
    private String modelo;
    @DatabaseField(canBeNull = false)
    private int ram;
    @DatabaseField(canBeNull = false)
    private int hd;
    @DatabaseField(canBeNull = false)
    private float valoracion;

    public Ordenador() {
    }

    public Ordenador(String marca, String modelo, int ram, int hd, float valoracion) {
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.hd = hd;
        this.valoracion = valoracion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
