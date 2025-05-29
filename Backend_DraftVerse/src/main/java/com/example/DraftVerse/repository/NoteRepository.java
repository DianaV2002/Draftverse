package com.example.DraftVerse.repository;

import com.example.DraftVerse.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByWorkspaceId(Integer workspaceId);

    @Query("SELECT n FROM Note n " +
            "WHERE n.workspace.id = :workspace_id " +
            "AND EXISTS (SELECT 1 FROM Filter f WHERE f.workspace.id = n.workspace.id " +
            "AND LOWER(n.title) LIKE LOWER(CONCAT('%', f.name, '%')))")
    List<Note> findByWorkspaceAndFilterName(@Param("workspace_id") Integer workspaceId);


}
