package HotelManagement.repository;

import HotelManagement.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   Optional<Employee> findByUsernameAndDeletedFlag(String username, String deletedFlag);

   boolean existsByUsernameAndDeletedFlag(String username, String deletedFlag);

   boolean existsByPhoneNumberAndDeletedFlag(String phoneNumber, String deletedFlag);

   boolean existsByEmailAndDeletedFlag(String email, String deletedFlag);

   Optional<Employee> findByIdAndDeletedFlag(Long id, String deletedFlag);

   List<Employee> findAllByDeletedFlag(String deletedFlag);

    Optional<Object> findByUsername(String username);

    boolean existsByUsername(String adminUsername);

    Optional<Employee>findByEmailAndDeletedFlag(String email, String deletedFlag);


}
