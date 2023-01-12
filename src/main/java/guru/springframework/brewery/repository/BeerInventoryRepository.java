package guru.springframework.brewery.repository;

import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {

    List<BeerInventory> findAllByBeer(Beer beer);
}
