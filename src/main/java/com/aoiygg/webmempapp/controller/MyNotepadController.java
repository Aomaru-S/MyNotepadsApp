package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.model.Notepad;
import com.aoiygg.webmempapp.repository.NotepadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyNotepadController {

    NotepadRepository notepadRepository;

    @Autowired
    public MyNotepadController(NotepadRepository notepadRepository) {
        this.notepadRepository = notepadRepository;
    }

    @GetMapping("/myNotepads")
    public String myNotepads(Model model, HttpServletRequest request) {
        List<Notepad> notepadList = notepadRepository.findAll();
        model.addAttribute("notepadList", notepadList);
        System.out.println(request.getRemoteUser());
        return "myNotepads";
    }

    @GetMapping("/editNotepad")
    public String editNotepad(Model model) {
        model.addAttribute("notepad", new Notepad());
        return "editNotepad";
    }

    @RequestMapping({"/", "/index", "/index.html", "myNotepad", "myNotepad.html","myNotepads.html"})
    public String redirectMyNotepads() {
        return "redirect:/myNotepads";
    }

    @RequestMapping("/editNotepad.html")
    public String redirectEditNotepad() {
        return "redirect:/editNotepad";
    }

    @PostMapping("/editNotepadSubmit")
    public String editNotepadSubmit(@ModelAttribute Notepad notepad) {
        System.out.println(notepad.toString());
        notepadRepository.save(notepad);
        return "redirect:/myNotepads";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
