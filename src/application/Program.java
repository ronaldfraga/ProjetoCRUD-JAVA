package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDao userDao = DaoFactory.createUserDao();

        System.out.println("--------------------------------------");
        System.out.println("| WELCOME TO THE REGISTRATION SCREEN |");
        System.out.println("--------------------------------------");
        System.out.println("");
        System.out.println("NOME ALUNO: RONALD FRAGA DA SILVA");
        System.out.println("TURMA: 4DC1");
        System.out.println("MATRICULA: 24210092");
        System.out.println("Feito por: RONALD FRAGA");      

        int option = -1;

        do {
            try {
                System.out.println("\n=== MENU CRUD ===");
                System.out.println("1 - Inserir usuário");
                System.out.println("2 - Listar usuários");
                System.out.println("3 - Buscar por ID");
                System.out.println("4 - Atualizar usuário");
                System.out.println("5 - Deletar usuário");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                option = sc.nextInt();
                sc.nextLine(); // limpar buffer

                switch (option) {
                    case 1:
                        System.out.println("\n--- INSERIR USUÁRIO ---");
                        System.out.print("Nome: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        User newUser = new User(null, name, email);
                        userDao.insert(newUser);
                        System.out.println("Usuário inserido! ID gerado = " + newUser.getId());
                        break;

                    case 2:
                        System.out.println("\n--- LISTAR USUÁRIOS ---");
                        List<User> list = userDao.findAll();
                        if (list.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            for (User u : list) {
                                System.out.println(u);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- BUSCAR POR ID ---");
                        System.out.print("ID: ");
                        int idFind = sc.nextInt();
                        User found = userDao.findById(idFind);
                        if (found == null) {
                            System.out.println("Usuário não encontrado!");
                        } else {
                            System.out.println(found);
                        }
                        break;

                    case 4:
                        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
                        System.out.print("ID do usuário: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine(); // limpar buffer
                        User toUpdate = userDao.findById(idUpdate);
                        if (toUpdate == null) {
                            System.out.println("Usuário não encontrado!");
                        } else {
                            System.out.print("Novo nome: ");
                            toUpdate.setName(sc.nextLine());
                            System.out.print("Novo email: ");
                            toUpdate.setEmail(sc.nextLine());
                            userDao.update(toUpdate);
                            System.out.println("Usuário atualizado com sucesso!");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- DELETAR USUÁRIO ---");
                        System.out.print("ID: ");
                        int idDel = sc.nextInt();
                        sc.nextLine();
                        userDao.deleteById(idDel);
                        System.out.println("Usuário deletado com sucesso!");
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida! Digite um número válido.");
                sc.nextLine(); // limpa o que o usuário digitou errado
            } catch (DbException e) {
                System.out.println("❌ Erro de banco de dados: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
            }

        } while (option != 0);

        sc.close();
    }
}
