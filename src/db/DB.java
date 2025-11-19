package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.DBConfig;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
            } catch (SQLException e) {
                throw new DbException("Erro ao conectar: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
