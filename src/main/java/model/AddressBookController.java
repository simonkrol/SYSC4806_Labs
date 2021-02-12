package model;
/**
 * @author Simon Krol
 * SYSC4806 Lab 4 - Feb 4th 2021
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookRepository repository;
    @Autowired
    private BuddyInfoRepository buddy_repository;


    @GetMapping(value={"/addressBooks", "/"})
    public String addressBookForm(Model model) {
        model.addAttribute("addressBooks", repository.findAll());
        model.addAttribute("addressBook", new AddressBook());
        return "addressBook/index";
    }

    @GetMapping("addressBooks/{id}")
    public String addressBookShow(@PathVariable("id") long id, Model model) {
        AddressBook book = repository.findById(id);

        model.addAttribute("addressBook", book);
        model.addAttribute("buddy", new BuddyInfo());
        return "addressBook/show";
    }

    @PostMapping("/addressBooks")
    public String addressBookSubmit() {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);

        return "redirect:/addressBooks/" + addressBook.getId();
    }

    @PostMapping("/addressBooks/{id}/addBuddy")
    public String addBuddy(@PathVariable("id") long id, @RequestParam(value = "name") String name,
                           @RequestParam(value="phoneNumber") String phoneNumber,
                            @RequestParam(value="address") String address) {
        AddressBook addressBook = repository.findById(id);
        BuddyInfo buddy = new BuddyInfo(name, phoneNumber, address);
        addressBook.addBuddy(buddy);
        repository.save(addressBook);

        return "redirect:/addressBooks/" + addressBook.getId();
    }

    @PostMapping("/addressBooks/{id}/removeBuddy")
    public String removeBuddy(@PathVariable("id") long id, @RequestParam(value = "name") String name,
                           @RequestParam(value="phoneNumber") String phoneNumber) {
        AddressBook addressBook = repository.findById(id);
        List<BuddyInfo> buddies = buddy_repository.findByNameAndPhoneNumber(name, phoneNumber);
        for(BuddyInfo buddy: buddies)
        {
            addressBook.removeBuddy(buddy);
        }
        repository.save(addressBook);
        return "redirect:/addressBooks/" + addressBook.getId();
    }
}