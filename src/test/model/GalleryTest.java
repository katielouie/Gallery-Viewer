package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// EFFECTS: Test methods in Gallery
class GalleryTest {
    Gallery gallery;
    String t1, t2, t3;
    String m1, m2, m3;
    String s1, s2, s3;

    @BeforeEach
    public void setup() {
        gallery = new Gallery();
        t1 = "0";
        t2 = "0";
        t3 = "0";
        m1 = "0";
        m2 = "0";
        m3 = "0";
        s1 = "0";
        s2 = "0";
        s3 = "0";
    }

    @Test
    public void testAddPiece() {
        gallery.addPiece("t1", "m1", "s1");
        assertEquals("t1", gallery.pieceByIndex(0).getTitle());
        assertEquals("m1", gallery.pieceByIndex(0).getMedium());
        assertEquals("s1", gallery.pieceByIndex(0).getSubject());
    }

    @Test
    void testSortByTitle() {
        t1 = "a";
        t2 = "c";
        t3 = "b";
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        ArrayList<ArtPiece> sorted = gallery.sortByTitle();
        assertEquals("a", sorted.get(0).getTitle());
        assertEquals("b", sorted.get(1).getTitle());
        assertEquals("c", sorted.get(2).getTitle());
    }

    @Test
    void testSortById() {
        t1 = "a";
        t2 = "c";
        t3 = "b";
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        ArrayList<ArtPiece> sorted = gallery.sortById();
        assertEquals("a", sorted.get(0).getTitle());
        assertEquals("c", sorted.get(1).getTitle());
        assertEquals("b", sorted.get(2).getTitle());
    }

    @Test
    void testGetGalleryAsArrayList() {
        t1 = "a";
        t2 = "c";
        t3 = "b";
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        ArrayList<ArtPiece> array = gallery.getGalleryAsArrayList();
        assertEquals("a", array.get(0).getTitle());
        assertEquals("c", array.get(1).getTitle());
        assertEquals("b", array.get(2).getTitle());
    }

    @Test
    void testTitleId() {
        t1 = "a";
        t2 = "b";
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        assertEquals(1, gallery.titleIndex(t1));
        assertEquals(2, gallery.titleIndex(t2));
        assertEquals(-1, gallery.titleIndex("we"));

    }

    @Test
    void testPieceByIndex() {
        ArtPiece artPiece = new ArtPiece(t1, m2, s3);
        gallery.addPiece(artPiece);
        assertEquals(artPiece, gallery.pieceByIndex(0));
        assertNull(gallery.pieceByIndex(4));
    }

    @Test
    void testFilterByMedium() {
        String medium = "kangaroo";
        t1="x";
        t2="y";
        m1 = medium;
        m2 = "b";
        m3 = medium;
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        ArrayList<ArtPiece> array = gallery.filterByMedium(medium);
        assertEquals(2,array.size());
        assertEquals(t1,array.get(0).getTitle());
        assertEquals(t3,array.get(1).getTitle());
    }

    @Test
    void testFilterBySubject() {
        String subject = "giraffe";
        t1="x";
        t2="y";
        s1 = subject;
        s3 = subject;
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        ArrayList<ArtPiece> array = gallery.filterBySubject(subject);
        assertEquals(2,array.size());
        assertEquals(t1,array.get(0).getTitle());
        assertEquals(t3,array.get(1).getTitle());
    }

    @Test
    void testContainsMedium() {
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        m1 = "no";
        assertFalse(gallery.containsMedium(m1));
        gallery.addPiece(t1,m1,s1);
        assertTrue(gallery.containsMedium(m1));
    }

    @Test
    void testContainsSubject() {
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        s1 = "s";
        assertFalse(gallery.containsSubject(s1));
        gallery.addPiece(t1,m1,s1);
        assertTrue(gallery.containsSubject(s1));
    }

    @Test
    void testDelete() {
        t1 = "a";
        t2 = "c";
        t3 = "b";
        gallery.addPiece(t1, m1, s1);
        gallery.addPiece(t2, m2, s2);
        gallery.addPiece(t3, m3, s3);
        assertTrue(gallery.titleIndex(t2)!=-1);
        gallery.deletePiece(t2);
        assertEquals(-1, gallery.titleIndex(t2));
    }
}