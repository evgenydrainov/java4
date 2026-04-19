package com.student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DivisionRegistry {

    private final Map<String, Division> byName = new HashMap<>();
    private int nextId = 1;

    public Division getOrCreate(String name) {
        Division existing = byName.get(name);
        if (existing != null) {
            return existing;
        }
        Division created = new Division(nextId++, name);
        byName.put(name, created);
        return created;
    }

    public int size() {
        return byName.size();
    }

    public Collection<Division> all() {
        return byName.values();
    }
}
