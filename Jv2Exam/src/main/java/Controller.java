import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements DAOBook{

    List<BookProduct> list = new ArrayList<>();

    public Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Book Management";
        String username = "Rinowo";
        String password = "Rinochan205.";
        return DriverManager.getConnection(dbURL, username, password);
    }

    private static final String SQL_INSERT = "INSERT INTO Book VALUES (?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM Book";

    public void add(BookProduct books) {
        list.add(books);
    }

    public void SaveToFile() throws IOException {
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get("BooksManagement.json"));
        gson.toJson(this.list, writer);
        writer.close();
    }

    @Override
    public int INSERT(BookProduct book) throws SQLException {
        PreparedStatement preparedStatement = null;
        int result;
        Connection connection = getConnection();
        preparedStatement = connection.prepareStatement(SQL_INSERT);
        preparedStatement.setString(1, book.getBookId());
        preparedStatement.setString(2, book.getBookName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setDouble(4, book.getPrice());
        result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("Inserted");
        } else {
            System.err.println("Not working");
        }

        return result;
    }

    @Override
    public List<BookProduct> SELECT() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BookProduct> books = new ArrayList<>();
        preparedStatement = connection.prepareStatement(SQL_SELECT);

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("bookId");
            String name = resultSet.getString("bookName");
            String author = resultSet.getString("author");
            double price = resultSet.getDouble("price");
            System.out.println("Book id: " + id + "| Book name: " + name +
                    "| Book author: " + author + "| Book price: $" + price);
        }
        return books;
    }
}
