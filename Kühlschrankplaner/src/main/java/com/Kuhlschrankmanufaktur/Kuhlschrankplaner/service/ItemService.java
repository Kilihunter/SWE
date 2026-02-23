package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Item;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.repository.ItemRepository;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Transactional(readOnly = true)
  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Optional<Item> findById(Long id) {
    return itemRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public Optional<Item> findByName(String name) {
    return itemRepository.findByName(name);
  }

  @Transactional(readOnly = true)
  public List<Item> searchByName(String namePart) {
    return itemRepository.findByNameContainingIgnoreCase(namePart);
  }

  public Item create(String name) {
    Item item = new Item();
    item.setName(name);
    return itemRepository.save(item);
  }

  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public void deleteById(Long id) {
    itemRepository.deleteById(id);
  }
}
