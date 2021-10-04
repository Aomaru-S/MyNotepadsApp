package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.model.*;
import com.aoiygg.webmempapp.model.key.CategoryKey;
import com.aoiygg.webmempapp.model.key.NotepadCategoryKey;
import com.aoiygg.webmempapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@Controller
@Transactional
public class MyNotepadController {

    NotepadRepository notepadRepository;
    AuthMailAddressRepository authMailAddressRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    NotepadCategoryRepository notepadCategoryRepository;
    private final MailSender sender;
    EntityManager entityManager;

    @Autowired
    public MyNotepadController(NotepadRepository notepadRepository,
                               AuthMailAddressRepository authMailAddressRepository,
                               UserRepository userRepository,
                               CategoryRepository categoryRepository,
                               NotepadCategoryRepository notepadCategoryRepository,
                               MailSender sender,
                               EntityManager entityManager) {
        this.notepadRepository = notepadRepository;
        this.authMailAddressRepository = authMailAddressRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.notepadCategoryRepository = notepadCategoryRepository;
        this.sender = sender;
        this.entityManager = entityManager;
    }

    @GetMapping("/myNotepads")
    public String myNotepads(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(name = "category", required = false) List<String> catList) {
        List<Notepad> notepadList = new ArrayList<>();
        if (catList == null) {
            notepadList = notepadRepository.findNotepadByMailAddress(userDetails.getMailAddress());
        }
        Set<Long> notepadIdSet = new HashSet<>();
        if (catList != null) {
            catList.forEach(s -> {
                List<NotepadCategory> notepadCategories = notepadCategoryRepository.findNotepadCategoryByCategoryName(s);
                Set<Long> ids = new HashSet<>();
                notepadCategories.forEach(notepadCategory -> {
                    ids.add(notepadCategory.getNotepadId());
                });
                notepadIdSet.addAll(ids);
            });
            List<Notepad> finalNotepadList = notepadList;
            notepadIdSet.forEach(aLong -> {
                finalNotepadList.add(notepadRepository.findById(aLong).orElse(null));
            });
        }
        List<Category> categoryList = categoryRepository.findCategoryByMailAddress(userDetails.getMailAddress());
        model.addAttribute("notepadList", notepadList);
        model.addAttribute("categoryList", categoryList);
        return "myNotepads";
    }

    @GetMapping("/editNotepad")
    public String editNotepad(Model model) {
        model.addAttribute("notepad", new Notepad());
        return "editNotepad";
    }
    @PostMapping("/editNotepad")
    public String editNotepadPost(Model model) {
        model.addAttribute("notepad", new Notepad());
        return "editNotepad";
    }

    @RequestMapping({"/", "/index"})
    public String redirectMyNotepads() {
        return "redirect:/myNotepads";
    }

    @PostMapping("/editNotepadSubmit")
    public String editNotepadSubmit(@RequestParam(name = "category", required = false) List<String> categoryList, @ModelAttribute Notepad notepad, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Notepad np = notepadRepository.findById(notepad.getNotepadId()).orElse(null);
        if (np != null) {
            long notepadId = np.getNotepadId();
            categoryList.forEach(s -> {
                long count = notepadCategoryRepository.countAllByCategoryNameAndNotepadId(s, notepadId);
                if (count == 1) {
                    NotepadCategoryKey nck = new NotepadCategoryKey(notepadId, s);
                    notepadCategoryRepository.deleteById(nck);
                    entityManager.flush();
                    CategoryKey categoryKey = new CategoryKey(s, np.getMailAddress());
                    categoryRepository.deleteById(categoryKey);
                }
            });

            /*List<NotepadCategory> notepadCategoryList = new ArrayList<>();
            notepadCategoryList = notepadCategoryRepository.findNotepadCategoryByNotepadId(np.getNotepadId());
            notepadCategoryList.forEach(notepadCategory -> {
                long notepadId = notepadCategory.getNotepadId();
                String mailAddress = notepad.getMailAddress();
                NotepadCategoryKey nck = new NotepadCategoryKey(notepadId, mailAddress);

            });*/
        }
        notepad.setMailAddress(userDetails.getMailAddress());
        notepadRepository.save(notepad);
        entityManager.flush();
        categoryList.forEach(cat -> {
            Category category = new Category(cat, userDetails.getMailAddress());
            categoryRepository.save(category);
            NotepadCategory notepadCategory = new NotepadCategory(notepad.getNotepadId(), cat);
            notepadCategoryRepository.save(notepadCategory);
        });
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
                "http://localhost:8080/MyNotepads/signUp?uuid=" + uuid);

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
        myNotepadsUser.setRole("ROLE_" + "USER");
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
