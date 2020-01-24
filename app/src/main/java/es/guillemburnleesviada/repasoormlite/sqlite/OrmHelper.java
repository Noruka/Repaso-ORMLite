package es.guillemburnleesviada.repasoormlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import es.guillemburnleesviada.repasoormlite.configuracion.Configuracion;
import es.guillemburnleesviada.repasoormlite.pojo.Ordenador;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Ordenador, Integer> ordenadorDAO;

    public OrmHelper(Context context) {
        super(context, Configuracion.BD_NOMBRE, null, Configuracion.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Ordenador.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Ordenador.class, true);
            TableUtils.createTable(connectionSource, Ordenador.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Ordenador, Integer> getOrdenadorDAO() throws SQLException {
        if (ordenadorDAO == null){
            ordenadorDAO = getDao(Ordenador.class);
        }
        return ordenadorDAO;
    }
}
