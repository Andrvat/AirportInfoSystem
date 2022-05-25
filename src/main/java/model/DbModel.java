package model;

import annotations.DbTable;
import entities.Departure;
import entities.Location;
import entities.Ticket;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.*;

public class DbModel {
    private final HashMap<String, Class<?>> entitiesClasses = new HashMap<>();

    private final List<String> prettyViewingTables = new ArrayList<>() {{
       add(Ticket.class.getAnnotation(DbTable.class).name());
       add(Departure.class.getAnnotation(DbTable.class).name());
       add(Location.class.getAnnotation(DbTable.class).name());
    }};

    public DbModel() {
        this.findAllEntitiesClasses();
    }

    private void findAllEntitiesClasses() {
        var reflections = new Reflections("entities", new SubTypesScanner(false));
        var classesSet = new HashSet<>(reflections.getSubTypesOf(Object.class));
        for (var c : classesSet) {
            DbTable annotation = c.getAnnotation(DbTable.class);
            if (!Objects.isNull(annotation)) {
                this.entitiesClasses.put(annotation.name(), c);
            }
        }
    }

    public HashMap<String, Class<?>> getEntitiesClasses() {
        return this.entitiesClasses;
    }

    public Class<?> getEntityClassByKey(String key) {
        return this.entitiesClasses.get(key);
    }

    public List<String> getPrettyViewingTables() {
        return prettyViewingTables;
    }
}
