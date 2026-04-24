/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Haltbarkeitsdatum;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Item;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Lebensmittel;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.repository.ItemRepository;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  
  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  public Optional<Item> findById(int id) {
    return itemRepository.findById(id);
  }


  public Item create(Haltbarkeitsdatum haltbarkeit, Lebensmittel lebensmittel) {
    Item item = new Item(haltbarkeit, lebensmittel);
    return itemRepository.save(item);
  }

  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public void deleteById(int id) {
    itemRepository.deleteById(id);
  }
}
*/