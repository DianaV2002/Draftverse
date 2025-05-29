package com.example.DraftVerse.persistence;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="Note", schema="draft")
public class Note{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;

    @Column(name = "workspace_id")
    private Integer workspace_id;

    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getWorkspaceId() {
        return workspace_id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setWorkspaceId(Integer workspaceId) {
        this.workspace_id = workspaceId;
    }

}
