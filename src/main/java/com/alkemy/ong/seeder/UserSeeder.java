package com.alkemy.ong.seeder;

import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        
        List<Role> users = new ArrayList();
        if (roleRepository.count() == 0) {
            Role role = new Role(1l,"USER","for normal users", null);
            roleRepository.save(role);
            users.add(role);
        }
        List<Role> admins = new ArrayList();
        if (roleRepository.count() == 1) {
            Role role = new Role(2l,"ADMIN","for admins", null);
            roleRepository.save(role);
            admins.add(role);
        }
        if (userRepository.count() == 0) {
            Long l;
            for (int i = 1; i <= 10; i++) {
                l = new Long(i);
                User user = new User(l, userNames().get(i-1), userLastNames().get(i-1), userNames().get(i-1).toLowerCase() + userLastNames().get(i-1).toLowerCase() + "@gmail.com", "12345", userNames().get(i-1) + ".jpg", users, null, true);
                userRepository.save(user);
            }
            for (int i = 11; i <= 20; i++) {
                l = new Long(i);
                User user = new User(l, userNames().get(i-1), userLastNames().get(i-1), userNames().get(i-1).toLowerCase() + userLastNames().get(i-1).toLowerCase() + "@gmail.com", "12345", userNames().get(i-1) + ".jpg", admins, null, true);
                userRepository.save(user);
            }
        }
    }

    private List<String> userNames() {
        List<String> names = new ArrayList();
        names.add("Hernesto");
        names.add("Andres");
        names.add("Camilo");
        names.add("Enzo");
        names.add("Mauro");
        names.add("Rodrigo");
        names.add("Nicolas");
        names.add("Juan");
        names.add("Daniel");
        names.add("Santiago");
        names.add("Lucia");
        names.add("Maria");
        names.add("Laura");
        names.add("Patricia");
        names.add("Paula");
        names.add("Daniela");
        names.add("Martina");
        names.add("Aldana");
        names.add("Nahir");
        names.add("Luciana");
        return names;
    }

    private List<String> userLastNames() {
        List<String> lastNames = new ArrayList();
        lastNames.add("Hernandez");
        lastNames.add("Garcia");
        lastNames.add("Martinez");
        lastNames.add("Lopez");
        lastNames.add("Gonzales");
        lastNames.add("Perez");
        lastNames.add("Rodriguez");
        lastNames.add("Sanchez");
        lastNames.add("Morales");
        lastNames.add("Barrera");
        lastNames.add("Coppari");
        lastNames.add("Fernandez");
        lastNames.add("Isuani");
        lastNames.add("Leiva");
        lastNames.add("Dominguez");
        lastNames.add("Alvarez");
        lastNames.add("Maidana");
        lastNames.add("Suarez");
        lastNames.add("Lococco");
        lastNames.add("Moreira");
        return lastNames;
    }
}
