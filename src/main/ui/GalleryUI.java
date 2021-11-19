package ui;

import model.ArtPiece;
import model.Gallery;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// EFFECTS: Makes a Window displaying the gallery
public class GalleryUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final String JSON_GALLERY = "./data/gallery.json";

    private final JDesktopPane desktop;
    JPanel listPanel;

    private DefaultListModel<ArtPiece> listModel;
    private JList<ArtPiece> list;
    JScrollPane listScrollPane;
    private JLabel picture;


    // EFFECTS: Constructor to make the window
    public GalleryUI() {

        desktop = new JDesktopPane();
        setContentPane(desktop);
        setTitle("Gallery App");
        setSize(WIDTH, HEIGHT);

        desktop.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addGalleryPanel();
        addList();
        addViewPanel();
        setVisible(true);
    }

    // EFFECTS: Split the screen to add save, load, list, and edit menus
    public void addGalleryPanel() {
        listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(250,800));
        listPanel.setBackground(Color.WHITE);
        listPanel.setLayout(new BorderLayout());
        desktop.add(listPanel, BorderLayout.WEST);

        addDataButtons();
        addEditButtons();
    }

    // EFFECTS: Shows art piece, title, medium, and subject
    public void addViewPanel() {
        //viewPanel = new JPanel();
        picture = new JLabel();
        picture.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(picture);
        desktop.add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: Add save/Load Buttons
    public void addDataButtons() {
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> save());

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> load());

        btnPnl.add(saveButton);
        btnPnl.add(loadButton);

        listPanel.add(btnPnl, BorderLayout.NORTH);
    }

    // EFFECTS: Add List to ScrollPane
    public void addList() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(e -> updatePicture());
        listScrollPane = new JScrollPane(list);
        listPanel.add(listScrollPane);
    }

    // EFFECTS: Updates picture to selected value
    protected void updatePicture() {
        if (!list.isSelectionEmpty()) {
            ImageIcon icon = new ImageIcon(list.getSelectedValue().getPath());
            picture.setIcon(icon);
        }
    }

    // EFFECTS: Add buttons to add/remove
    public void addEditButtons() {
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        addButton.addActionListener(e -> addMenu());
        btnPnl.add(addButton);
        btnPnl.add(removeButton);
        removeButton.addActionListener(e -> removeArtPiece());

        listPanel.add(btnPnl, BorderLayout.SOUTH);
    }

    // EFFECTS: Pop-Up Menu for inputting data for new artPieces
    public void addMenu() {
        JInternalFrame frame = new JInternalFrame("Add", false, true);
        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add((new JLabel("Title")));
        TextField titleField = new TextField(30);
        panel.add(titleField);

        panel.add((new JLabel("Subject")));
        TextField subjectField = new TextField(30);
        panel.add(subjectField);

        panel.add((new JLabel("Medium")));
        TextField mediumField = new TextField(30);
        panel.add(mediumField);

        panel.add((new JLabel("Filepath")));
        TextField pathField = new TextField(30);
        panel.add(pathField);

        JButton button = new JButton("Add");
        button.addActionListener(e -> addArtPiece(titleField, subjectField, mediumField, pathField));
        panel.add(button);

        frame.add(panel);
        frame.setSize(300,300);
        desktop.add(frame);
        frame.show();
    }

    // REQUIRES: Text fields for title, subject, medium, and path
    // EFFECTS: Method for getting the art piece and adding it to the list
    public void addArtPiece(TextField titleField, TextField subjectField, TextField mediumField, TextField pathField) {
        String title = titleField.getText();
        String subject = subjectField.getText();
        String medium = mediumField.getText();
        String path = pathField.getText();
        ArtPiece artPiece = new ArtPiece(title, subject, medium, path);
        listModel.addElement(artPiece);
    }

    // EFFECTS: Removes artPiece from List
    public void removeArtPiece() {
        if (!list.isSelectionEmpty()) {
            listModel.remove(list.getSelectedIndex());
        }
        picture.setIcon(null);
        list.updateUI();
    }

    // EFFECTS: Saves Gallery to file
    public void save() {
        Gallery gallery = new Gallery();

        for (int i = 0; i < listModel.size(); i++) {
            System.out.println(listModel.elementAt(i).getTitle());
            gallery.addPiece(listModel.elementAt(i));
        }

        JsonWriter jsonWriter = new JsonWriter(JSON_GALLERY);
        try {
            jsonWriter.open();
            jsonWriter.write(gallery);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_GALLERY);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_GALLERY);
        }
    }

    // EFFECTS: Loads gallery from file
    public void load() {
        listModel.clear();
        Gallery gallery = new Gallery();
        JsonReader jsonReader = new JsonReader(JSON_GALLERY);
        try {
            gallery = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_GALLERY);
        }
        for (ArtPiece artPiece: gallery.getGalleryAsArrayList()) {
            listModel.addElement(artPiece);
        }
    }


}
