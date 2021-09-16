package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.model.AuthMailAddress;
import com.aoiygg.webmempapp.model.MyNotepadsUser;
import com.aoiygg.webmempapp.model.Notepad;
import com.aoiygg.webmempapp.model.UserDetailsImpl;
import com.aoiygg.webmempapp.repository.AuthMailAddressRepository;
import com.aoiygg.webmempapp.repository.NotepadRepository;
import com.aoiygg.webmempapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Transactional
public class MyNotepadController {

    NotepadRepository notepadRepository;
    AuthMailAddressRepository authMailAddressRepository;
    UserRepository userRepository;
    private final MailSender sender;

    @Autowired
    public MyNotepadController(UserRepository userRepository, NotepadRepository notepadRepository, AuthMailAddressRepository authMailAddressRepository, MailSender sender) {
        this.notepadRepository = notepadRepository;
        this.authMailAddressRepository = authMailAddressRepository;
        this.sender = sender;
        this.userRepository = userRepository;
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

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam String mailAddress, Model model) {

        String uuid = UUID.randomUUID().toString();
        AuthMailAddress authMailAddress = new AuthMailAddress(mailAddress, uuid);

        authMailAddressRepository.save(authMailAddress);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("yggdrasill0430@gmail.com");
        message.setTo(mailAddress);
        message.setSubject("test mail");
        message.setText("アカウントに登録したいならリンク踏んでね♡ \n" +
                "http://localhost:8080/signUp?uuid=" + uuid);

        sender.send(message);

        model.addAttribute("mailAddress", mailAddress);
        return "mailSent";
    }

    @GetMapping("/signUp")
    public String signUp(@RequestParam String uuid, Model model) {
        MyNotepadsUser myNotepadsUser = new MyNotepadsUser();
        String mailAddress = authMailAddressRepository.findAuthMailAddressByUuid(uuid).getMailAddress();
        myNotepadsUser.setMailAddress(mailAddress);

        model.addAttribute("uuid", uuid);
        model.addAttribute("myNotepadsUser", myNotepadsUser);
        return "signUp";
    }

    @PostMapping("/signUpUser")
    public String signUpUser(@RequestParam String uuid, @ModelAttribute MyNotepadsUser myNotepadsUser, Model model) {
        myNotepadsUser.setRole("USER");
        String mailAddress = authMailAddressRepository.findAuthMailAddressByUuid(uuid).getMailAddress();
        myNotepadsUser.setMailAddress(mailAddress);
        userRepository.save(myNotepadsUser);
        model.addAttribute("username", myNotepadsUser.getUserName());
        return "redirect:signedUp";
    }

    @GetMapping("/signedUp")
    public String signedUp() {
        return "signedUp";
    }
}
