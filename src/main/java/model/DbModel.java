package model;

import annotations.DbTable;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.*;
import java.util.stream.Collectors;

public class DbModel {
    private final HashMap<String, Class> entitiesClasses = new HashMap<>();

    public DbModel() {
        this.findAllEntitiesClasses();
    }

    private void findAllEntitiesClasses() {
        Reflections reflections = new Reflections("entities", new SubTypesScanner(false));
        Set<Class> classesSet = new HashSet<>(reflections.getSubTypesOf(Object.class));
        for (var c : classesSet) {
            DbTable annotation = (DbTable) c.getAnnotation(DbTable.class);
            if (!Objects.isNull(annotation)) {
                this.entitiesClasses.put(annotation.name(), c);
            }
        }
    }

    public HashMap<String, Class> getEntitiesClasses() {
        return this.entitiesClasses;
    }

    public Class getEntityClassByKey(String key) {
        return this.entitiesClasses.get(key);
    }
}
