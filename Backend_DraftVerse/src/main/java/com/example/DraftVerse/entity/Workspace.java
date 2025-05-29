package com.example.DraftVerse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data

@Getter
@Setter
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=true)
    @NotBlank(message = "Numele workspace-ului nu poate fi nul")
    @Size(min=3,  max=50, message = "Titlul workspace-ului este intre 3 si 5 caractere")
    private String name;
    @OneToMany(mappedBy = "workspace",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;


    public Workspace(Integer id, String name, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Workspace() {
    }
}

