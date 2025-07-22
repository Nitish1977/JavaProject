package com.contactbook.controller;

import com.contactbook.model.Contact;
import com.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Contact> contacts = (search == null || search.isEmpty()) ?
                contactService.getAllContacts() : contactService.searchContacts(search);
        model.addAttribute("contacts", contacts);
        model.addAttribute("search", search);
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "add";
    }

    @PostMapping("/add")
    public String addContact(@ModelAttribute Contact contact) {
        contactService.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContact(id);
        model.addAttribute("contact", contact);
        return "edit";
    }

    @PostMapping("/edit")
    public String editContact(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/";
    }
}
