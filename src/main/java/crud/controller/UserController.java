package crud.controller;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> users = repository.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name, @RequestParam("age") String age
            , @RequestParam(value = "isAdmin", required = true, defaultValue = "false") Boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setAdmin(isAdmin);
        user.setCreatedDate();
        repository.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id) {
        repository.removeUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public String updateUser(Model model, @PathVariable Integer id) {
        model.addAttribute("userAttribute", repository.get(id));
        return "updateUser";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public String updateUser(@RequestParam("name") String name, @RequestParam("age") String age
            , @RequestParam(value = "isAdmin", required = true, defaultValue = "false") Boolean isAdmin
            , @PathVariable Integer id) {
        User user = repository.get(id);
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setAdmin(isAdmin);
        repository.updateUser(user);
        return "redirect:/";
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView getUsers1(){
//        List<User> users = repository.getUsers();
//        return new ModelAndView("index", "users", users);
//    }


}
