package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findFirstByLebensmittel_NameIgnoreCase(String name);

	List<Item> findByLebensmittel_NameContainingIgnoreCase(String namePart);
}
