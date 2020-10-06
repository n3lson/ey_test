package com.ey.task_1.db;

import com.ey.task_1.file.TextFile;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.ey.task_1.util.Constants.*;

public class DatabaseImporter implements AutoCloseable {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/ey?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "r00tRoo7";

    private static final String INSERT_QUERY = "INSERT INTO random_data(r_date, r_latin, r_cyrillic, r_int, r_float) VALUES(?, ?, ?, ?, ?);";

    private final Connection connection;

    public DatabaseImporter() throws SQLException, ClassNotFoundException {
        this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Class.forName(JDBC_DRIVER);
    }

    public void importData(TextFile src) throws IOException, SQLException, ParseException {
        List<String> lines = src.lines();
        int total = lines.size();
        int imported = 0;

        PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY);

        for (String line: lines) {
            String[] fields = line.split(DELIMITER);
            statement.setDate(1, new Date(new SimpleDateFormat(DATE_PATTERN).parse(fields[0]).getTime()));
            statement.setString(2, fields[1]);
            statement.setString(3, fields[2]);
            statement.setInt(4, Integer.parseInt(fields[3]));
            statement.setDouble(5, Double.parseDouble(fields[4]));
            statement.execute();

            System.out.printf("Rows imported: %d; Rows left: %d\r", ++imported, total - imported);
        }
        statement.close();
        System.out.println(ANSI_CYAN +"\nImporting finished." + ANSI_RESET);
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
