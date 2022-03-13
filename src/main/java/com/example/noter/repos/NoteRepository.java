package com.example.noter.repos;

import com.example.noter.model.Note;
import com.example.noter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

  Iterable<Note> findByOwner(User owner);

}
