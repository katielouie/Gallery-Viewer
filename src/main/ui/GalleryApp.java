package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class GalleryApp {
    Gallery gallery = new Gallery();
    Scanner in = new Scanner(System.in);

    public GalleryApp() {
        runGallery();
    }

    //processes user input for main menu
    public void runGallery() {
        boolean keepGoing = true;
        String command;
        while (keepGoing) {
            mainDisplayMenu();
            command = in.next().toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                mainProcessCommand(command);
            }
        }
    }

    // EFFECTS: Displays main options to user
    public void mainDisplayMenu() {
        displayDivider();
        System.out.println("Please select: ");
        System.out.println(" a: add new artwork");
        System.out.println(" l: view list of artworks");
        System.out.println(" f: filter artworks by medium or subject");
        System.out.println(" d: view artwork in detail");
        System.out.println(" q: quit");
        System.out.print("Enter your command: ");
    }

    // MODIFIES: this
    // EFFECTS: processes the main menu's command
    public void mainProcessCommand(String command) {
        switch (command) {
            case "a":
                addMenu();
                break;
            case "l":
                listMenu();
                break;
            case "f":
                filterMenu();
                break;
            case "d":
                detailsMenu();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for to add new pieces
    public void addMenu() {
        String title;
        do {
            displayDivider();
            System.out.print("Enter the title: ");
            title = in.next();
            if (gallery.titleId(title) != -1) {
                System.out.println("Title already exists! Choose another.");
            }
        } while (gallery.titleId(title) != -1);
        System.out.print("Enter the medium: ");
        String medium = in.next();
        System.out.print("Enter the subject: ");
        String subject = in.next();
        gallery.addPiece(title, medium, subject);
        System.out.println("Piece added! Going back to main menu");
    }

    // EFFECTS: Displays sorting options to user
    public void listDisplayOptions() {
        displayDivider();
        System.out.println("View list by: ");
        System.out.println(" i: id");
        System.out.println(" t: title");
        System.out.println(" b: go back");
        System.out.print("Enter your command: ");
    }

    // EFFECTS: processes user input for the list menu
    public void listMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            listDisplayOptions();
            String command = in.next().toLowerCase();
            switch (command) {
                case "i":
                    displayListWithOrdering(gallery.sortById());
                    break;
                case "t":
                    displayList(gallery.sortByTitle());
                    break;
                case "b":
                    keepGoing = false;
                default:
                    System.out.println("Invalid input, try again");
            }
        }
    }

    // EFFECTS: Displays filter options to user
    public void filterDisplayOptions() {
        displayDivider();
        System.out.println("Filter by: ");
        System.out.println(" m: medium");
        System.out.println(" s: subject");
        System.out.println(" b: go back");
        System.out.print("Enter your command: ");
    }

    // EFFECTS: processes user input for the filtering menu
    public void filterMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            filterDisplayOptions();
            String command = in.next();
            switch (command) {
                case "m":
                    filterMedium();
                    break;
                case "s":
                    filterSubject();
                    break;
                case "b":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }
        }
    }

    // EFFECTS: processes user input to filter by medium
    public void filterMedium() {
        boolean keepGoing = true;
        while (keepGoing) {
            displayDivider();
            System.out.print("Enter medium: ");
            String command = in.next();
            if (gallery.containsMedium(command)) {
                displayList(gallery.filterByMedium(command));
                keepGoing = false;
            } else {
                System.out.println("Medium doesn't exist! Try again");
            }
        }
    }

    // EFFECTS: processes user input to filter by subject
    public void filterSubject() {
        boolean keepGoing = true;
        while (keepGoing) {
            displayDivider();
            System.out.print("Enter subject: ");
            String command = in.next();
            if (gallery.containsSubject(command)) {
                displayList(gallery.filterBySubject(command));
                keepGoing = false;
            } else {
                System.out.println("Subject doesn't exist! Try again");
            }
        }
    }

    // EFFECTS: Prints out details (title, medium, subject) of piece`
    public void displayDetails(ArtPiece artPiece) {
        System.out.println("Title: " + artPiece.getTitle());
        System.out.println("Medium: " + artPiece.getMedium());
        System.out.println("Subject: " + artPiece.getSubject());
    }

    // EFFECTS: processes user input for the detailing menu
    public void detailsMenu() {
        boolean keepGoing = true;
        do {
            displayDivider();
            System.out.print("Enter title of piece or b to go back: ");
            String command = in.next();
            int id = gallery.titleId(command);
            if (command.equalsIgnoreCase("b")) {
                keepGoing = false;
            } else if (id != -1) {
                displayDetails(gallery.pieceById(id));
            } else {
                System.out.println("Title doesn't exist! Please try again");
            }
        } while (keepGoing);
    }


    // EFFECTS: Displays List of Titles
    public void displayList(ArrayList<ArtPiece> gallery) {
        for (ArtPiece piece : gallery) {
            System.out.println(piece.getTitle());
        }
    }

    // EFFECTS: Displays List of Titles with id
    public void displayListWithOrdering(ArrayList<ArtPiece> gallery) {
        for (ArtPiece piece : gallery) {
            System.out.println("[" + piece.getId() + "]: " + piece.getTitle());
        }
    }

    // EFFECTS: displays divider
    public void displayDivider() {
        // string method repeat doesn't work
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
