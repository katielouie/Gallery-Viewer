package ui;

import model.Gallery;

import javax.swing.*;
import java.awt.*;

public class GalleryUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private JDesktopPane desktop;
    JPanel listPanel;
    private DefaultListModel listModel;
    private JList list;

    Gallery gallery;

    //Constructor to make the window
    public GalleryUI() {
        gallery = new Gallery();

        desktop = new JDesktopPane();
        setContentPane(desktop);
        setTitle("Gallery App");
        setSize(WIDTH, HEIGHT);

        desktop.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addGalleryPanel();
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
        JButton loadButton = new JButton("Load");
        btnPnl.add(saveButton);
        btnPnl.add(loadButton);

        listPanel.add(btnPnl, BorderLayout.NORTH);
    }

    public void addList() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
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


}
