package model;

import javax.swing.*;

public class EventDefaultListModel extends DefaultListModel<ArtPiece> {
    public EventDefaultListModel() {
        super();
    }

    @Override
    public ArtPiece remove(int index) {
        ArtPiece removedArtPiece = super.remove(index);
        EventLog.getInstance().logEvent(new Event("Removed Art Piece: " + removedArtPiece.getTitle()));
        return removedArtPiece;

    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void addElement(ArtPiece element) {
        super.addElement(element);
        EventLog.getInstance().logEvent(new Event("Added Artpiece: " + element.getTitle()));
    }

    // EFFECTS: Prints EventLog
    public void printEventLog() {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }
}
