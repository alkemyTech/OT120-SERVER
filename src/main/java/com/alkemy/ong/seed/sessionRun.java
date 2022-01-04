
package com.alkemy.ong.seed;

import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
class sessionRun {
  

    public void sessionRun() {
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
        Session mySession = myFactory.openSession();
        try {
            List<User> users = addUserRol();
            mySession.beginTransaction();
            mySession.save(users.get(0));
            mySession.save(users.get(1));
            mySession.save(users.get(2));
            mySession.save(users.get(3));
            mySession.save(users.get(4));
            mySession.save(users.get(5));
            mySession.save(users.get(6));
            mySession.save(users.get(7));
            mySession.save(users.get(8));
            mySession.save(users.get(9));
            List <User> admins = addAdminRol();
            mySession.save(admins.get(0));
            mySession.save(admins.get(1));
            mySession.save(admins.get(2));
            mySession.save(admins.get(3));
            mySession.save(admins.get(4));
            mySession.save(admins.get(5));
            mySession.save(admins.get(6));
            mySession.save(admins.get(7));
            mySession.save(admins.get(8));
            mySession.save(admins.get(9));
            mySession.getTransaction().commit();
            mySession.close();
        } finally {
            myFactory.close();
        }
    }
    
    public List<User> addUserRol() {
        
        List<Role> roles = new ArrayList();
        User user = new User();
        Role role = new Role();
        role.setId(1l);
        role.setName("USER");
        role.setDescription("for normals users");
        role.setTimestamp(null);
        roles.add(role);
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Role.class).buildSessionFactory();
        Session mySession = myFactory.openSession();
        try {
            mySession.beginTransaction();
            mySession.save(role);
            mySession.getTransaction().commit();
            mySession.close();
            roles.add(role);
        } finally {
            myFactory.close();
        }
        List<User> users = new ArrayList();
        Long l;
        for (int i = 1; i <= 10; i++) {
            l=new Long(i);
            users.add(new User(l, "user"+i+"name", "user"+i+"lastname", "user"+i+"@gmail.com", "user"+i, "user"+i+".jpg", roles, null, true));
        }               
        return users;
    }
    public List<User> addAdminRol(){
        List<Role> roles = new ArrayList();
        User user = new User();
        Role role = new Role();
        role.setId(2l);
        role.setName("ADMIN");
        role.setDescription("for admins");
        role.setTimestamp(null);
        roles.add(role);
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Role.class).buildSessionFactory();
        Session mySession = myFactory.openSession();
        try {
            mySession.beginTransaction();
            mySession.save(role);
            mySession.getTransaction().commit();
            mySession.close();
            roles.add(role);
        } finally {
            myFactory.close();
        } 
        List<User> users = new ArrayList();
        Long l;
        for (int i = 1; i <= 10; i++) {
            l=new Long(i+10);
            users.add(new User(l, "user"+(i+10)+"name", "user"+(i+10)+"lastname", "user"+(i+10)+"@gmail.com", "user"+(i+10), "user"+(i+10)+".jpg", roles, null, true));
        }               
        return users;        
    }
    
}
