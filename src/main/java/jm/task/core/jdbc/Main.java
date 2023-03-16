package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user1 = new User("Nick1", "Gr1", (byte) 19);
        User user2 = new User("Nick2", "Gr2", (byte) 25);
        User user3 = new User("Nick3", "Gr3", (byte) 16);
        User user4 = new User("Nick4", "Gr4", (byte) 100);

        UserService userService = new UserServiceImpl();


        try {
            userService.createUsersTable();
            System.out.println("Таблица создалась");
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try {
            userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
            System.out.println("User с именем - " + user1.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
            System.out.println("User с именем - " + user2.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
            System.out.println("User с именем - " + user3.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
            System.out.println("User с именем - " + user4.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            userService.removeUserById(3);
//            System.out.println("User removed");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            List<User> list = userService.getAllUsers();
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            userService.cleanUsersTable();
            System.out.println("Table is clear");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            userService.dropUsersTable();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // реализуйте алгоритм здесь
    }
}
