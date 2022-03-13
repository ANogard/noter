package com.example.noter.controller;

import com.example.noter.model.Note;
import com.example.noter.model.User;
import com.example.noter.repos.NoteRepository;
import com.example.noter.repos.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NoterController {

  private final NoteRepository noteRepository;
  private final UserRepository userRepository;

  public NoterController(NoteRepository noteRepository, UserRepository userRepository){
    this.noteRepository = noteRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String home(Authentication authentication, Model model){
    if (authentication != null) {
      User owner = userRepository.findByUsername(authentication.getName());
      Iterable<Note> iterable = noteRepository.findByOwner(owner);
      int count = 0;
      if (iterable instanceof Collection) {
        count = ((Collection<?>) iterable).size();
      }
      model.addAttribute("count", count);
    }
    return "homepage";
  }

  @PostMapping("/new")
  public String add(@AuthenticationPrincipal User user, @RequestParam String text, Model model){
    text = text.strip();
    if(text != null && !text.isEmpty()) {
      noteRepository.save(new Note(text, user));
    }else{
      model.addAttribute("isVisible", "true");
    }
    return "new";
  }

  @GetMapping("/new")
  public String form(){
    return "new";
  }

  @GetMapping("/notes")
  public String notes(Authentication authentication, Model model) {
    if (authentication != null) {
      User owner = userRepository.findByUsername(authentication.getName());
      ArrayList<Note> list = new ArrayList<>();
      Iterable<Note> iterable = noteRepository.findByOwner(owner);
      for (Note note : iterable) {
        list.add(note);
      }
      model.addAttribute("notes", list);
    }
    return "notes";
  }
}
