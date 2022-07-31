package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userServiceImpl;
    @Autowired
    public void setUserService (UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        List<User> users = userServiceImpl.index();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.showUser(id));
        return "show";
    }

    @GetMapping( "users/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @RequestMapping(value = "users/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImpl.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.showUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImpl.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/users";
    }
}