package MiniProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Book {
    private int id;
    private String title;
    private String author;
    private String edition;
    private String name;
    private String usn;
    private String date;
    private boolean isIssued;
    
    public Book(int id, String title, String author, String edition) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }
	
	public String getTitle() {
        return title;
    }
	public String getAuthor() {
        return author;
    }
	public String getEdition() {
        return edition;
    }
	
	public String getIsissued() {
		if(isIssued){
			return "Issued";
		}
		else{
			return "Available";
		}
	}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public String toString() {
        return "Title: " + title + ", \nAuthor: " + author + ", \nEdition: " + edition + ", \nName: " +name+ ",\nUSN:"+usn+",\nDate:"+date;
    }
    
   

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(int bookId) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == bookId) {
                iterator.remove();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }


    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void issueBook(int id, String name, String usn, String date) {
        Book book = getBookById(id);
        if (book != null) {
            if (!book.isIssued()) {
                book.setIssued(true);
                book.setName(name);
                book.setUsn(usn);
                book.setDate(date);
                System.out.println("You have successfully issued the book: " );
                System.out.println("Details : " + book);
            } else {
                System.out.println("Sorry, this book is already issued.");
            }
        } else {
            System.out.println("Sorry, this book does not exist.");
        }
    }

    public void returnBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            if (book.isIssued()) {
                book.setIssued(false);
                System.out.println("You have successfully returned the book: " );
                System.out.println("Details : " + book);
            } else {
                System.out.println("Sorry, this book is not issued.");
            }
        } else {
            System.out.println("Sorry, this book does not exist.");
        }
    }
	public void displayAllBooks() {
		System.out.println("List of Books:");
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.printf("| %-5s | %-30s | %-20s | %-10s | %-10s |\n", "ID", "Title", "Author", "Edition","Status");
		System.out.println("-------------------------------------------------------------------------------------------");
		for (Book book : books) {
			System.out.printf("| %-5d | %-30s | %-20s | %-10s | %-10s |\n", book.getId(), book.getTitle(), book.getAuthor(), book.getEdition(),book.getIsissued());
		}
		System.out.println("-------------------------------------------------------------------------------------------");
	}
	
	public void displayIssuedBooks() {
		boolean issuedBooksExist = false;
    System.out.println("List of Issued Books:");
    System.out.println("--------------------------------------------------------------------------------------------");
    System.out.printf("| %-5s | %-30s | %-20s | %-10s | %-10s | %-10s | %-15s |\n", "ID", "Title", "Author", "Edition", "Name", "USN", "Date");
    System.out.println("--------------------------------------------------------------------------------------------");
    for (Book book : books) {
        if (book.isIssued()) {
			issuedBooksExist = true;
            System.out.printf("| %-5d | %-30s | %-20s | %-10s | %-10s | %-10s | %-15s |\n", book.getId(), book.getTitle(), book.getAuthor(), book.getEdition(), book.getName(), book.getUsn(), book.getDate());
        }
		
    }
	if (!issuedBooksExist) {
        System.out.println("No books are currently issued.");
    }
    System.out.println("--------------------------------------------------------------------------------------------");
}
	public boolean isLibraryEmpty() {
    return books.isEmpty();
}
	public boolean isBookExist(int bookid)
	{
		for (Book book : books) {
			if (book.getId()==bookid)
			{
				return true;
			}
		}
		return false;
	}
}

public class LibraryManagementSystem
{
    public static void main(String[] args) 
	{
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
			System.out.println("-----------------");
			System.out.println("      MENU     ");
			System.out.println("-----------------");
            System.out.println("1. Add books");
            System.out.println("2. Delete books");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
			System.out.println("5. Display All Book in the Library");
			System.out.println("6. Display All Issued Book in the Library");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(scanner);

            switch (choice) {
                case 1:
					System.out.print("\nAdding A Book\n--------------\n");
					int id;
					lblid:
					for(;;){
                    System.out.print("Enter book ID: ");
                    id = getValidInput(scanner);
					if(library.isBookExist(id))
					{
						System.out.print("Book With ID already Exists\n");
					}
					else
					{
						break lblid;
					}
					}
                    System.out.print("Enter book title: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book edition: ");
                    String edition = scanner.nextLine();
                    Book newBook = new Book(id, title, author, edition);
                    library.addBook(newBook);
                    System.out.println("\nBook added successfully!\n");
                    break;

                case 2:
                    System.out.print("Enter the ID of the book to delete: ");
                    int deleteId = getValidInput(scanner);
                    library.deleteBook(deleteId);
                    break;


                case 3:
                	System.out.print("Enter student name:");
					scanner.nextLine();
                	String name = scanner.nextLine();
                	System.out.print("Enter USN: ");
                	String usn = scanner.nextLine();
					String date;
					lblDate:
					for(;;)
					{
						System.out.print("Enter Issue date (YYYY-MM-DD): ");
						date = scanner.nextLine();
						try 
						{
							LocalDate isdate = LocalDate.parse(date);
							break lblDate;
						} 
						catch (DateTimeParseException e) 
						{
							System.out.print("Invalid date format or date does not exist.\n");
						}
					}
                    System.out.print("Enter the ID of the book you want to issue: ");
                    int issueId = getValidInput(scanner);
                    library.issueBook(issueId, name, usn, date);
                    break;

                case 4:
					
                	System.out.println("Enter student name:");
                    String returnName = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter USN:");
					String returnUsn = scanner.nextLine();
					String returnDate;
					lblDate:
					for(;;)
					{
						System.out.print("Enter Return Date (YYYY-MM-DD): ");
						returnDate = scanner.nextLine();
						try 
						{
							LocalDate isdate = LocalDate.parse(returnDate);
							break lblDate;
						} 
						catch (DateTimeParseException e) 
						{
							System.out.print("Invalid date format or date does not exist.\n");
						}
					}
                    System.out.print("Enter the ID of the book you want to return: ");
                    int returnId = getValidInput(scanner);
                    library.returnBook(returnId);
                    break;
					 
				case 5:
					if(library.isLibraryEmpty()){
						System.out.println("Library Empty");
					}
					else{
					library.displayAllBooks();
					}
					break;
					
				case 6:
					library.displayIssuedBooks();
					break;
					
                case 7:
                    scanner.close();
                    System.out.println("Exiting library management system");
                    System.exit(0);
                    break;
				
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Validate input
    private static int getValidInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next();
        }
       
        return scanner.nextInt();
    }
}