package com.nexuscrm.app.repository;

import com.nexuscrm.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

    User findByEmail(String mail);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userBooks WHERE u.name = :name")
    User findUserByNameWithBooks(@Param("name") String name);

    // Cargamos el Usuario -> sus Libros -> y los Autores de esos libros en una sola consulta
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.userBooks b " +
            "LEFT JOIN FETCH b.author " +
            "WHERE u.name = :name")
    User findUserByNameWithBooksAndAuthors(@Param("name") String name);

}
