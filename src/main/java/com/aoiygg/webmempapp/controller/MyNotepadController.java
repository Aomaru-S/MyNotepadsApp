package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.model.MyNotepadsUser;
import com.aoiygg.webmempapp.model.Notepad;
import com.aoiygg.webmempapp.model.UserDetailsImpl;
import com.aoiygg.webmempapp.repository.NotepadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MyNotepadController {

    NotepadRepository notepadRepository;

    @Autowired
    public MyNotepadController(NotepadRepository notepadRepository) {
        this.notepadRepository = notepadRepository;
    }

    @GetMapping("/myNotepads")
    public String myNotepads(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Notepad> notepadList = notepadRepository.findNotepadByMailAddress(userDetails.getMailAddress());
        model.addAttribute("notepadList", notepadList);
        return "myNotepads";
    }

    @GetMapping("/editNotepad")
    public String editNotepad(Model model) {
        model.addAttribute("notepad", new Notepad());
        return "editNotepad";
    }

    @RequestMapping({"/", "/index", "/index.html", "myNotepad", "myNotepad.html", "myNotepads.html"})
    public String redirectMyNotepads() {
        return "redirect:/myNotepads";
    }

    @RequestMapping("/editNotepad.html")
    public String redirectEditNotepad() {
        return "redirect:/editNotepad";
    }

    @PostMapping("/editNotepadSubmit")
    public String editNotepadSubmit(@ModelAttribute Notepad notepad, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        notepad.setMailAddress(userDetails.getMailAddress());
        notepadRepository.save(notepad);
        return "redirect:/myNotepads";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute(new UserDetailsImpl(new MyNotepadsUser()));
        return "signUp";
    }
}
