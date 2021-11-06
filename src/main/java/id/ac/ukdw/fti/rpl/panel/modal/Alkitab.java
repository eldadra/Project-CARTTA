package id.ac.ukdw.fti.rpl.panel.modal;

public class Alkitab {
    private String bookName;
    private String verse;
    private String verseText;

    public Alkitab(String bookName, String verse, String verseText) {
        this.bookName = bookName;
        this.verse = verse;
        this.verseText = verseText;
    }

    public String getBookName() {
        return bookName;
    }

    public String getVerse() {
        return verse;
    }

    public String getVerseText() {
        return verseText;
    }
}
