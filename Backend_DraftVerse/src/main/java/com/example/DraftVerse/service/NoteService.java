package com.example.DraftVerse.service;

import com.example.DraftVerse.dto.NoteDTO;
import com.example.DraftVerse.entity.Note;
import com.example.DraftVerse.entity.Workspace;
import com.example.DraftVerse.exception.ResourceNotFoundException;
import com.example.DraftVerse.response.ApiResponse;
import com.example.DraftVerse.repository.NoteRepository;
import com.example.DraftVerse.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;
    public List<NoteDTO> getNotesFilteredByKeyword(Integer workspaceId) {
        List<Note> notes = noteRepository.findByWorkspaceAndFilterName(workspaceId);

        // Convert the List of Notes to DTOs
        return notes.stream()
                .map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent(),
                        note.getCreatedAt(), note.getUpdatedAt(), note.getWorkspace().getId()))
                .collect(Collectors.toList());
    }
    @Transactional
    public ApiResponse<NoteDTO> createNote(NoteDTO noteDTO) {
        Workspace workspace = workspaceRepository.findById(noteDTO.getWorkspaceId())
                .orElseThrow(() -> new RuntimeException("Workspace not found"));

        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setWorkspace(workspace); // Ensure setWorkspace exists

        Note saved = noteRepository.save(note);

        NoteDTO savedNoteDTO = new NoteDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getCreatedAt(),
                saved.getUpdatedAt(),
                saved.getWorkspace().getId()
        );

        return new ApiResponse<>(true, "Note created successfully", savedNoteDTO);
    }
    @Transactional(readOnly = true)
    public ApiResponse<List<NoteDTO>> getAllNotesByWorkspaceId(Integer workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        List<Note> notes = noteRepository.findByWorkspaceId(workspaceId);

        List<NoteDTO> noteDTOs = notes.stream().map(note -> new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                note.getWorkspace().getId()
        )).collect(Collectors.toList());

        return new ApiResponse<>(true, "Notes fetched successfully", noteDTOs);
    }
    @Transactional(readOnly = true)
    public ApiResponse<NoteDTO> getNoteById(Integer workspaceId, Integer noteId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        if (!note.getWorkspace().getId().equals(workspaceId)) {
            throw new ResourceNotFoundException("Note not found in the specified workspace");
        }

        NoteDTO noteDTO = new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                note.getWorkspace().getId()
        );

        return new ApiResponse<>(true, "Note fetched successfully", noteDTO);
    }
    @Transactional
    public ApiResponse<NoteDTO> updateNote(Integer workspaceId, Integer noteId, NoteDTO noteDTO) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        if (!note.getWorkspace().getId().equals(workspaceId)) {
            throw new ResourceNotFoundException("Note does not belong to the specified workspace");
        }

        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        noteRepository.save(note);

        NoteDTO updatedNoteDTO = new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                note.getWorkspace().getId()
        );

        return new ApiResponse<>(true, "Note updated successfully", updatedNoteDTO);
    }
    @Transactional
    public ApiResponse<Void> deleteNote(Integer workspaceId, Integer noteId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        if (!note.getWorkspace().getId().equals(workspaceId)) {
            throw new ResourceNotFoundException("Note does not belong to the specified workspace");
        }

        noteRepository.delete(note);

        return new ApiResponse<>(true, "Note deleted successfully", null);
    }




}
