package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

// EFFECTS: Displays Instructions and processes user input
public class GalleryApp {
    private static final String JSON_STORE = "./data/gallery.json";

    Gallery gallery = new Gallery();
    Scanner in = new Scanner(System.in);

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public GalleryApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        System.out.println(" v: view artwork in detail");
        System.out.println(" d: delete an artwork");
        System.out.println(" s: save/load gallery to file");
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
            case "v":
                detailsMenu();
                break;
            case "d":
                deleteMenu();
                break;
            case "s":
                dataMenu();
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
            if (gallery.titleIndex(title) != -1) {
                System.out.println("Title already exists! Choose another.");
            }
        } while (gallery.titleIndex(title) != -1);
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
        System.out.println(" o: order created");
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
                case "o":
                    displayListWithOrdering(gallery.sortById());
                    break;
                case "t":
                    displayList(gallery.sortByTitle());
                    break;
                case "b":
                    keepGoing = false;
                    break;
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
            int id = gallery.titleIndex(command);
            if (command.equalsIgnoreCase("b")) {
                keepGoing = false;
            } else if (id != -1) {
                displayDetails(gallery.pieceByIndex(id));
            } else {
                System.out.println("Title doesn't exist! Please try again");
            }
        } while (keepGoing);
    }

    private void deleteMenu() {
        boolean keepGoing = true;
        do {
            displayDivider();
            System.out.print("Enter title of the piece you want to delete or b to go back: ");
            String command = in.next();
            int id = gallery.titleIndex(command);
            if (command.equalsIgnoreCase("b")) {
                keepGoing = false;
            } else if (id != -1) {
                gallery.deletePiece(command);
                System.out.println("Piece Deleted, going back now");
                keepGoing = false;
            } else {
                System.out.println("Title doesn't exist! Please try again");
            }
        } while (keepGoing);
    }

    // EFFECTS: Displays saving options to user
    public void dataOptions() {
        displayDivider();
        System.out.println(" s: save");
        System.out.println(" l: load");
        System.out.println(" b: go back");
        System.out.print("Enter your command: ");
    }

    private void dataMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            dataOptions();
            String command = in.next();
            switch (command) {
                case "s":
                    saveGallery();
                    break;
                case "l":
                    loadGallery();
                    break;
                case "b":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }
        }
    }

    // EFFECTS: Displays List of Titles
    public void displayList(ArrayList<ArtPiece> gallery) {
        for (ArtPiece piece : gallery) {
            System.out.println(piece.getTitle());
        }
    }

    // EFFECTS: Displays List of Titles with id
    public void displayListWithOrdering(ArrayList<ArtPiece> gallery) {
        int i = 1;
        for (ArtPiece piece : gallery) {
            System.out.println("[" + i + "]: " + piece.getTitle());
            i++;
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

    // EFFECTS: saves the gallery to file
    private void saveGallery() {
        try {
            jsonWriter.open();
            jsonWriter.write(gallery);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads gallery from file
    private void loadGallery() {
        try {
            gallery = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

