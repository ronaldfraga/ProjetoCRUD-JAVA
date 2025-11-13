package application;

import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDao userDao = DaoFactory.createUserDao();
        int opcao;
        System.out.println(" ┌------------------------------------------");
        System.out.println(" | WELCOME TO MY SYSTEM CRUD                |");
        System.out.println(" ∟------------------------------------------┘");
        System.out.println();
        System.out.println("NOME ALUNO: RONALD FRAGA DA SILVA ");
        System.out.println("TURMA: 4DC1");
        System.out.println();

   
     do {
            System.out.println("\n==============================");
            System.out.println("         MENU USUÁRIOS        ");
            System.out.println("==============================");
            System.out.println("1 - Inserir novo usuário");
            System.out.println("2 - Buscar usuário por ID");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Listar todos os usuários");
            System.out.println("5 - Deletar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.println("\n=== INSERIR NOVO USUÁRIO ===");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    User novo = new User(null, nome, email);
                    userDao.insert(novo);
                    System.out.println("Usuário inserido! ID: " + novo.getId());
                    break;

                case 2:
                    System.out.println("\n=== BUSCAR USUÁRIO POR ID ===");
                    System.out.print("Digite o ID: ");
                    int idBusca = sc.nextInt();
                    sc.nextLine();
                    User u = userDao.findById(idBusca);
                    if (u != null) {
                        System.out.println("Usuário encontrado: " + u);
                    } else {
                        System.out.println("Nenhum usuário encontrado com esse ID.");
                    }
                    break;

                case 3:
                    System.out.println("\n=== ATUALIZAR USUÁRIO ===");
                    System.out.print("Digite o ID do usuário: ");
                    int idAtualiza = sc.nextInt();
                    sc.nextLine();
                    User usuarioExistente = userDao.findById(idAtualiza);
                    if (usuarioExistente == null) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }
                    System.out.print("Novo nome (" + usuarioExistente.getName() + "): ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo email (" + usuarioExistente.getEmail() + "): ");
                    String novoEmail = sc.nextLine();

                    if (!novoNome.isEmpty()) usuarioExistente.setName(novoNome);
                    if (!novoEmail.isEmpty()) usuarioExistente.setEmail(novoEmail);

                    userDao.update(usuarioExistente);
                    System.out.println("Usuário atualizado com sucesso!");
                    break;

                case 4:
                    System.out.println("\n=== LISTAR TODOS OS USUÁRIOS ===");
                    List<User> lista = userDao.findAll();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                    } else {
                        for (User obj : lista) System.out.println(obj);
                    }
                    break;

                case 5:
                    System.out.println("\n=== DELETAR USUÁRIO ===");
                    System.out.print("Digite o ID: ");
                    int idDel = sc.nextInt();
                    sc.nextLine();
                    userDao.deleteById(idDel);
                    System.out.println("Usuário deletado (se existia).");
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
