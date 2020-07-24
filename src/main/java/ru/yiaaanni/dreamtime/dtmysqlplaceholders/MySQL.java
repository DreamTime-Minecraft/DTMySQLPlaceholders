package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import ru.sgk.dreamtimeapi.data.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    private static Database db;

    public MySQL(String host, String user, String password, int port, String base) {
        db = new Database(host, port, user, password, base);
    }

    protected String get(String table, String whereWhat, String whereEquals, String select) {
        String sql = "SELECT "+select+" FROM `"+table+"` WHERE `"+whereWhat+"`='"+whereEquals+"';";
        try {
            ResultSet rs = db.query(sql);
            String object = (String)rs.getObject(select);
            return object;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "NUU";
    }

    protected Database getMamka() {
        return db;
    }

}
