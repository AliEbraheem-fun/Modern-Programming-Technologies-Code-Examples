package lecture.six.jdbc;

import java.sql.*;

public class Main {

    static final String DATABASE_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    static final String USERNAME = "sa";
    static final String PASSWORD = "";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement()
        ) {
            // 1. Создание таблицы
            stmt.execute("""
                CREATE TABLE people (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    firstname VARCHAR(255),
                    surname VARCHAR(255),
                    address VARCHAR(255)
                )
            """);
            System.out.println("Таблица создана.\n");
            printAll(stmt);

            // 2. Добавление данных через PreparedStatement
            try (PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO people (firstname, surname, address) VALUES (?, ?, ?)")) {
                insertPerson(insertStmt, "John", "Doe", "123 Main St");
                insertPerson(insertStmt, "Alice", "Smith", "456 Park Ave");
                insertPerson(insertStmt, "Bob", "Brown", "789 Elm St");
            }
            System.out.println("Данные добавлены через PreparedStatement.\n");
            printAll(stmt);

            // 3. Обновление данных через PreparedStatement
            try (PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE people SET address = ? WHERE firstname = ?")) {
                updateStmt.setString(1, "999 Updated Address");
                updateStmt.setString(2, "Alice");
                updateStmt.executeUpdate();
            }
            System.out.println("Адрес обновлён через PreparedStatement.\n");
            printAll(stmt);

            // 4. Удаление данных через PreparedStatement
            try (PreparedStatement deleteStmt = conn.prepareStatement(
                    "DELETE FROM people WHERE firstname = ?")) {
                deleteStmt.setString(1, "Bob");
                deleteStmt.executeUpdate();
            }
            System.out.println("Одна запись удалена через PreparedStatement.\n");
            printAll(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Вставка одной записи с параметрами
    private static void insertPerson(PreparedStatement stmt, String firstname, String surname, String address) throws SQLException {
        stmt.setString(1, firstname);
        stmt.setString(2, surname);
        stmt.setString(3, address);
        stmt.executeUpdate();
    }

    // Вывод содержимого таблицы
    private static void printAll(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM people");
        System.out.println("Текущее содержимое таблицы:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Имя: " + rs.getString("firstname"));
            System.out.println("Фамилия: " + rs.getString("surname"));
            System.out.println("Адрес: " + rs.getString("address"));
            System.out.println("---------------------------");
        }
        System.out.println();
        rs.close();
    }
}
