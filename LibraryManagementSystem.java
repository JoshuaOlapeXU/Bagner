import java.util.ArrayList;
import java.util.List;


interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Manga implements LibraryItem {
    private String title;
    private boolean borrowed;

    public Manga(String title, String author, int publicationYear) {
        this.title = title;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
        System.out.println("---------------------------------------");
        System.out.println("Manga \"" + title + "\" has been borrowed.");
    }

    @Override
    public void returnItem() {
        borrowed = false;
        System.out.println("---------------------------------------");
        System.out.println("Manga \"" + title + "\" has been returned.");
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}


class DVD implements LibraryItem {
    private String title;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
        System.out.println("---------------------------------------");
        System.out.println("DVD \"" + title + "\" has been borrowed.");
    }

    @Override
    public void returnItem() {
        borrowed = false;
        System.out.println("---------------------------------------");
        System.out.println("DVD \"" + title + "\" has been returned.");
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}


abstract class LibraryUser {
    List<LibraryItem> borrowedItems;

    public LibraryUser() {
        borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        } else {
            System.out.println("---------------------------------------");
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        } else {
            System.out.println("---------------------------------------");
            System.out.println("Item is not borrowed by this user.");
        }
    }

    abstract void printItemsBorrowed();
}


class Student extends LibraryUser {
    @Override
    void printItemsBorrowed() {
        System.out.println("---------------------------------------");
        System.out.println("Student has borrowed:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
    }
}


class Teacher extends LibraryUser {
    @Override
    void printItemsBorrowed() {
        System.out.println("---------------------------------------");
        System.out.println("Teacher has borrowed:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        
        Manga Manga1 = new Manga("Beyond Journey's End", "Kanehito Yamada", 2020);
        Manga Manga2 = new Manga("Violet Evergarden", "Kana Akatsuki", 2015);
        DVD dvd1 = new DVD("Her Blue Sky", "Tatsuyuki Nagai", 148);
        DVD dvd2 = new DVD("Maquia", "Mari Okada", 155);


        Student student1 = new Student();
        Teacher teacher1 = new Teacher();


        student1.borrowItem(Manga1);
        student1.borrowItem(dvd1);
        teacher1.borrowItem(Manga2);
        teacher1.borrowItem(dvd2);

        student1.printItemsBorrowed();
        teacher1.printItemsBorrowed();

        student1.returnItem(Manga1);
        teacher1.returnItem(dvd2);

        student1.printItemsBorrowed();
        teacher1.printItemsBorrowed();
    }
}
