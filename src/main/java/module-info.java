module panel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens id.ac.ukdw.fti.rpl.panel to javafx.fxml;
    exports id.ac.ukdw.fti.rpl.panel;
    exports id.ac.ukdw.fti.rpl.panel.modal;
}
