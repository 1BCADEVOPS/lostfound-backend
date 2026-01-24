package com.example.lostfound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "lost_found_items")
public class LostFoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String phoneNumber;

    private String itemName;
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;   // LOST or FOUND

    @Enumerated(EnumType.STRING)
    private Status status; // NOT_RETURNED or RETURNED

    public enum Type {
        LOST, FOUND
    }

    public enum Status {
        NOT_RETURNED, RETURNED
    }
}
