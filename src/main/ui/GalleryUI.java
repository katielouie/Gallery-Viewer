package ui;

import model.ArtPiece;
import model.Gallery;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GalleryUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private JDesktopPane desktop;
    JPanel listPanel;
    private DefaultListModel<ArtPiece> listModel;
    private JList<ArtPiece> list;


    //Constructor to make the window
    public GalleryUI() {
        Gallery gallery = new Gallery();

        desktop = new JDesktopPane();
        setContentPane(desktop);
        setTitle("Gallery App");
        setSize(WIDTH, HEIGHT);

        desktop.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addGalleryPanel();
        addList();
        setVisible(true);
    }

    //Split the screen to add save, load, list, and edit menus
    public void addGalleryPanel() {
        listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(250,800));
        listPanel.setBackground(Color.WHITE);
        listPanel.setLayout(new BorderLayout());
        desktop.add(listPanel, BorderLayout.WEST);

        addDataButtons();
        addEditButtons();
    }

    // Add save/Load Buttons
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
    
    public void addList() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        listPanel.add(listScrollPane);

    }

    // Add add/remove Buttons
    public void addEditButtons() {
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        btnPnl.add(addButton);
        btnPnl.add(removeButton);

        listPanel.add(btnPnl, BorderLayout.SOUTH);
    }

    public void save() {

    }

    // EFFECTS: Loads gallery from file
    public void load() {
        Gallery gallery = new Gallery();
        final String JSON_GALLERY = "./data/gallery.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_GALLERY);
        JsonReader jsonReader = new JsonReader(JSON_GALLERY);
        try {
            gallery = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_GALLERY);
        }
        for (ArtPiece artPiece: gallery.getGalleryAsArrayList()) {
            System.out.println(artPiece.getTitle());
            listModel.addElement(artPiece);
        }


    }


}
