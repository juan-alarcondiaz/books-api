package com.informacolombia.test.booksapi.books.infrastructure.adapters.output.oracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

}
