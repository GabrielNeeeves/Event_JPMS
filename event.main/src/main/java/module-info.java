module event.main {
    requires event.db;
    requires event.model.Event;
    requires event.model.Participant;
    requires event.eventView;
    requires event.participantView;
    requires event.subsView;
    requires java.sql;
}