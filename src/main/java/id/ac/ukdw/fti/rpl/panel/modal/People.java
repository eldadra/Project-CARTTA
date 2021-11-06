package id.ac.ukdw.fti.rpl.panel.modal;

public class People {
    private String name;
    private String verse;
    private String verseText;

    public People(String name) {
        this.name = name;
    }

    public People(String name, String verse, String verseText) {
        this.name = name;
        this.verse = verse;
        this.verseText = verseText;
    }

    public String getName() {
        return name;
    }

    public String getVerse() {
        return verse;
    }

    public String getVerseText() {
        return verseText;
    }
}
