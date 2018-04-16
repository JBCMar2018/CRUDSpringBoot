package me.afua.demospring;

import me.afua.demospring.model.Book;
import me.afua.demospring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    BookRepository library;

    @RequestMapping("/")
    public String index(Model model)
    {
            model.addAttribute("bookList",library.findAll());
            return "index";
    }

    @GetMapping("/add")
    public String addBook(Model model)
    {
        /*Add an object to the container  (model) named aBook in the HTML, containing
        * an empty instantiated book object so that the user can enter values into it*/
        model.addAttribute("aBook",new Book());
        return "addbook";
    }

    @PostMapping("/savebook")
    public String saveBook(@Valid @ModelAttribute("aBook") Book book, BindingResult result)
    {
        if(result.hasErrors())
        {
            return  "addBook";
        }

        library.save(book);
        return "redirect:/";
    }
}
