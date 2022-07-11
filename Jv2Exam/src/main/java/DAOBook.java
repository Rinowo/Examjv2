import java.sql.SQLException;
import java.util.List;

public interface DAOBook {
    int INSERT(BookProduct book) throws SQLException;
    List<BookProduct> SELECT() throws SQLException;
}
