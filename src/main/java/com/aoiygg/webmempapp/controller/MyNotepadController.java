package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.model.MyNotepadsUser;
import com.aoiygg.webmempapp.model.Notepad;
import com.aoiygg.webmempapp.model.UserDetailsImpl;
import com.aoiygg.webmempapp.repository.NotepadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyNotepadController {

    NotepadRepository notepadRepository;
    private final MailSender sender;

    @Autowired
    public MyNotepadController(NotepadRepository notepadRepository, MailSender sender) {
        this.notepadRepository = notepadRepository;
        this.sender = sender;
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

    @GetMapping("/mailAddressForm")
    public String mailAddressForm() {
        return "mailAddressForm";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute(new UserDetailsImpl(new MyNotepadsUser()));
        return "signUp";
    }

    @PostMapping("/sentMail")
    public String sendSighUpMail(@RequestParam String mailAddress) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("yggdrasill0430@gmail.com");
        message.setTo(mailAddress);
        message.setSubject("test mail");
        message.setText("This is test mail from gmail server with spring boot mail");

        sender.send(message);

        return "/myNotepads";
    }
}
