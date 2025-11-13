package application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

    public static void main(String[] args) {

        UserDao userDao = DaoFactory.createUserDao();

        System.out.println("\n=== INSERT ===");
        User newUser = new User(null, "teste", "teste@teste1.com");
        userDao.insert(newUser);
        System.out.println("Inserido! Novo ID = " + newUser.getId());

        System.out.println("\n=== FIND BY ID ===");
        User u = userDao.findById(newUser.getId());
        System.out.println(u);

        System.out.println("\n=== UPDATE ===");
        u.setName("Gepetélvis Atualizado");
        userDao.update(u);

        System.out.println("\n=== FIND ALL ===");
        List<User> list = userDao.findAll();
        for (User obj : list) System.out.println(obj);

        System.out.println("\n=== DELETE ===");
        userDao.deleteById(newUser.getId());
        System.out.println("Deletado!");
    }
}
