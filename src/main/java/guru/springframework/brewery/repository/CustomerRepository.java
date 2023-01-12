package guru.springframework.brewery.repository;

import guru.springframework.brewery.domain.Brewery;
import guru.springframework.brewery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
