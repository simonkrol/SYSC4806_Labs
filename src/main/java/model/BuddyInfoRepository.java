package model;

/**
 * @author Simon Krol
 * SYSC4806 Lab 4 - Feb 4th 2021
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "buddies", path="buddies")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    List<BuddyInfo> findByNameAndPhoneNumber(String name, String phoneNumber);
}