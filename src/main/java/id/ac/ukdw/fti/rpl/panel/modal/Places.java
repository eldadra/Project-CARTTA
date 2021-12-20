package id.ac.ukdw.fti.rpl.panel.modal;

public class Places {
    private String name;
    private String people;
    private People person;

    public Places(String name, String people) {
        this.name = name;
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public String getPeople() {
        return people;
    }
}
