package model;
/**
 * @author Simon Krol
 * SYSC4806 Lab 4 - Feb 4th 2021
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path="books")
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(Long id);
}