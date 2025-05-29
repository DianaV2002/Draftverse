package com.example.DraftVerse.controller;

import com.example.DraftVerse.dto.NoteDTO;
import com.example.DraftVerse.exception.ResourceNotFoundException;
import com.example.DraftVerse.response.ApiResponse;
import com.example.DraftVerse.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/myworkspaces/{workspace_id}/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @PostMapping
    public ResponseEntity<ApiResponse<NoteDTO>> createNote(
            @PathVariable("workspace_id") Integer workspaceId,
            @RequestBody NoteDTO noteDTO
    ) {

        try {
            noteDTO.setWorkspaceId(workspaceId);
            ApiResponse<NoteDTO> response = noteService.createNote(noteDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Conflict or invalid input", null), HttpStatus.CONFLICT);
        }
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<NoteDTO>>> getAllNotes(
            @PathVariable("workspace_id") Integer workspaceId
    ) {
        try {
            ApiResponse<List<NoteDTO>> response = noteService.getAllNotesByWorkspaceId(workspaceId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{note_id}")
    public ResponseEntity<ApiResponse<NoteDTO>> getNoteById(
            @PathVariable("workspace_id") Integer workspaceId,
            @PathVariable("note_id") Integer noteId
    ) {
        try {
            ApiResponse<NoteDTO> response = noteService.getNoteById(workspaceId, noteId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{note_id}")
    public ResponseEntity<ApiResponse<NoteDTO>> updateNote(
            @PathVariable("workspace_id") Integer workspaceId,
            @PathVariable("note_id") Integer noteId,
            @RequestBody NoteDTO noteDTO
    ) {
        try {
            ApiResponse<NoteDTO> response = noteService.updateNote(workspaceId, noteId, noteDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{note_id}")
    public ResponseEntity<ApiResponse<Void>> deleteNote(
            @PathVariable("workspace_id") Integer workspaceId,
            @PathVariable("note_id") Integer noteId
    ) {
        try {
            ApiResponse<Void> response = noteService.deleteNote(workspaceId, noteId);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<NoteDTO>> getFilteredNotes(@PathVariable("workspace_id") Integer workspaceId) {
        List<NoteDTO> notes = noteService.getNotesFilteredByKeyword(workspaceId);
        return ResponseEntity.ok(notes);
    }




}
