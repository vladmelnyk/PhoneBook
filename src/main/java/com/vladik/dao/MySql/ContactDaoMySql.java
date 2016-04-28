package com.vladik.dao.mysql;

import com.vladik.dao.ContactDao;
import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ContactDaoMySql extends JpaRepository<Contact, Integer>, ContactDao {

    List<Contact> findByUserAndFirstNameContains(User user, String expression);

    @Query(value = "SELECT c.id, c.users_id, c.first_name, c.middle_name, c.last_name, c.mobile_number, c.phone_number, c.address, c.email FROM Contacts c WHERE c.users_id = :usersId AND c.mobile_number LIKE %:mobileNumberExp%", nativeQuery = true)
    List<Contact> searchByUserAndMobileNumber(@Param("usersId") String usersId, @Param("mobileNumberExp") String mobileNumberExp);

    @Query(value = "SELECT c.id, c.users_id, c.first_name, c.middle_name, c.last_name, c.mobile_number, c.phone_number, c.address, c.email FROM Contacts c WHERE c.users_id = :usersId AND c.mobile_number LIKE %:mobileNumberExp% AND c.first_name LIKE %:firstNameExp% ", nativeQuery = true )
    List<Contact> searchByUserAndMobileNumberAndFirstName(@Param("usersId") String usersId, @Param("mobileNumberExp") String mobileNumberExp,  @Param("firstNameExp") String firstNameExp);


}
