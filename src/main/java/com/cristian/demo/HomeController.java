package com.cristian.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;


@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/addCar")
    public String carForm(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/processCar")
    public String processForm(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/detailCar/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "showCar";
    }

    @RequestMapping("/updateCar/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/deleteCar/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addCategory")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processCategory")
    public String processCar(@ModelAttribute Category category,
                             @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/addCategory";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            category.setPhoto(uploadResult.get("url").toString());
            categoryRepository.save(category);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/addCategory";
        }
        return "redirect:/";
    }
    @RequestMapping("/detailCategory/{id}")
    public String showCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "showCategory";
    }

    @RequestMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/deleteCategory/{id}")
    public String delCategory(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String searchPerson(@RequestParam("search") String search, Model model){
        model.addAttribute("vehiclesSearch", carRepository.findByManufacturer(search));
        model.addAttribute("categorySearch", categoryRepository.findByCategoryName(search));
        return "searchList";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

   @RequestMapping("/secure")

    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }
    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegistrationPage(@ModelAttribute("user") User user, Model model,
           @RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return "redirect:/register";
            }
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                user.setPhoto(uploadResult.get("url").toString());
                userRepository.save(user);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/register";
            }
            return "redirect:/";
        }
 }

