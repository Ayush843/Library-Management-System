import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();
        TransactionService transactionService = new TransactionService();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Register Member");
            System.out.println("4. List Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    bookService.addBook(title, author, category);
                    break;
                case 2:
                    bookService.listBooks();
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    memberService.registerMember(name, contact, email);
                    break;
                case 4:
                    memberService.listMembers();
                    break;
                case 5:
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Member ID: ");
                    int memberId = sc.nextInt();
                    transactionService.borrowBook(bookId, memberId);
                    break;
                case 6:
                    System.out.print("Transaction ID: ");
                    int transactionId = sc.nextInt();
                    transactionService.returnBook(transactionId);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
