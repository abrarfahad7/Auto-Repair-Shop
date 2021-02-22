package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface ProfileRepository extends CrudRepository <Profile, String> {
	Profile findProfileByProfileID(String profileID);
    Profile findByCustomer(Customer customer);

}
