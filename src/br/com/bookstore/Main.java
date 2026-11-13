package br.com.bookstore;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

public class Main {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAOSqlite();

        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 120.0);
        Livro l2 = new Livro("Design Patterns", "GoF", 150.0);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 135.0);

        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);

        System.out.println("== CA6DASTRO DE LIVROS ==");
        System.out.println("ID gerado: " + l1.getId());
        System.out.println("ID gerado: " + l2.getId());
        System.out.println("ID gerado: " + l3.getId());

        System.out.println("\n== BUSCANDO LIVRO (ID 2) ==");
        dao.buscarPorId(2L).ifPresentOrElse(
            livro -> System.out.println("Encontrado: " + livro),
            () -> System.out.println("Registro não encontrado.")
        );

        System.out.println("\n== BUSCANDO LIVRO INEXISTENTE (ID 999) ==");
        dao.buscarPorId(999L).ifPresentOrElse(
            livro -> System.out.println("Encontrado: " + livro),
            () -> System.out.println("Nenhum livro encontrado (Evitando NullPointerException).")
        );

        System.out.println("\n== LISTAGEM COMPLETA ==");
        System.out.printf("%-5s | %-20s | %-20s | %-10s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("-".repeat(70));
        for (Livro livro : dao.listarTodos()) {
            System.out.printf("%-5d | %-20s | %-20s | R$ %.2f%n",
                livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getPreco());
        }
    }
}