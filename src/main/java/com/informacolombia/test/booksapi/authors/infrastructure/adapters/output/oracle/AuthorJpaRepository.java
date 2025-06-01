package com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM AuthorEntity a JOIN a.books b WHERE a.id = :authorId")
    Boolean existsBooksByAuthorId(@Param("authorId") Long authorId);
}
