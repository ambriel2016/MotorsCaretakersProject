package com.cristian.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim", "avatar1.png");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        User user1 = new User("cris@cris.com", "password", "Cris", "Reynoso", true, "cris", "avatar2.png");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user1);

        User user2 = new User("rey@rey.com", "password", "Rey", "Aponte", true, "Rey", "avatar3.png");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin", "avatar.png");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


        // cars and categories

        Category category = new Category();
        category.setCategoryName("SUV");

        Set<Car> tempCars = new HashSet<>();

        Car car1;
        car1 = new Car(1, "Cadillac", "XT4", "2019", "$35,190.00", "https://www.cadillac.com/suvs/xt4/build-and-price/trim", "Max");
        car1.setCategory(category);

        Car car2;
        car2 = new Car(2, "Chrysler", "Chrysler Pacifica ", "2020", "$33.745.00", "https://www.chrysler.com/incentives.html?modelYearCode=CUC202005", "James");
        car2.setCategory(category);


        Car car3;
        car3 = new Car(3, "Ford", "Ford Edge", "2020", "$31,100.00", "https://www.ford.com/suvs-crossovers/edge/?gnav=header-all-vehicles", "Cristian");
        car3.setCategory(category);


        Car car4;
        car4 = new Car(4, "Honda", "CR - V", "2020", "$25,050.00", "https://automobiles.honda.com/cr-v", "Astin");
        car4.setCategory(category);

        Car car5;
        car5 = new Car(5, "Jeep", "Compass", "2019", "$22.095.00", "https://www.jeep.com/2019/compass.html", "Josia");
        car5.setCategory(category);

        Car car6;
        car6 = new Car(6, "Volkswagen", "Golf SportWagen 1.4T S", "2020", "$21,895.00", "https://www.vw.com/builder/tab/trim/model/golf-sportwagen/", "Reynaldo");
        car6.setCategory(category);

        categoryRepository.save(category);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
    }
}