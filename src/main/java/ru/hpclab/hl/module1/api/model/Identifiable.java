package ru.hpclab.hl.module1.api.model;

import java.util.UUID;

public interface Identifiable {
    Long getId();

    void setId(Long id);

    UUID getIdentifier();

    void setIdentifier(UUID identifier);
}

