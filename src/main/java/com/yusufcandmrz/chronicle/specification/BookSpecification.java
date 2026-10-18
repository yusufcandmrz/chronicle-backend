package com.yusufcandmrz.chronicle.specification;

import com.yusufcandmrz.chronicle.entity.Book;
import com.yusufcandmrz.chronicle.entity.BookCompanyRole;
import com.yusufcandmrz.chronicle.entity.BookPersonRole;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Book> hasAuthorName(String authorName) {
        return hasPersonName(authorName, BookPersonRole.AUTHOR);
    }

    public static Specification<Book> hasTranslatorName(String translatorName) {
        return hasPersonName(translatorName, BookPersonRole.TRANSLATOR);
    }

    private static Specification<Book> hasPersonName(String name, BookPersonRole role) {
        return ((root, query, criteriaBuilder) -> {
            var bookPeople = root.join("bookPeople");
            var person = bookPeople.join("person");

            query.distinct(true);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            bookPeople.get("bookPersonRole"),
                            role
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(person.get("name")),
                            "%" + name.toLowerCase() + "%"
                    )
            );
        });
    }

    public static Specification<Book> hasPublisherName(String publisherName) {
        return hasCompanyName(publisherName, BookCompanyRole.PUBLISHER);
    }

    private static Specification<Book> hasCompanyName(String name, BookCompanyRole role) {
        return ((root, query, criteriaBuilder) -> {
            var bookCompany = root.join("bookCompanies");
            var company = bookCompany.join("company");

            query.distinct(true);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            bookCompany.get("bookCompanyRole"),
                            role
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(company.get("name")),
                            "%" + name.toLowerCase() + "%"
                    )
            );
        });
    }
}
