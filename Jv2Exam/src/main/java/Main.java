import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller();

        while (true) {
            menu();
            int choose = sc.nextInt();

            switch (choose) {
                case 1 -> {
                    System.out.print("Enter book id: "); String id = sc.next();
                    sc.nextLine();
                    System.out.print("Enter book name: "); String name = sc.nextLine();
                    System.out.print("Enter book author: "); String author = sc.nextLine();
                    System.out.print("Enter book price: "); double price = sc.nextDouble();
                    BookProduct books = new BookProduct(id, name, author, price);
                    controller.add(books);
                    controller.INSERT(books);
                }
                case 2 -> controller.SaveToFile();
                case 3 -> controller.SELECT();
                case 4 -> System.exit(0);
            }
        }
    }

    public static void menu() {
        System.out.println("1. Add book records and save to database");
        System.out.println("2. Save to file");
        System.out.println("3. Display book records");
        System.out.println("4. Exit");
    }
}
