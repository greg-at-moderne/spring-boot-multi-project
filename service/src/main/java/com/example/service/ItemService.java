package com.example.service;

import com.example.domain.Item;
import com.example.persistence.ItemEntity;
import com.example.persistence.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll().stream()
                .map(this::toItem)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id).map(this::toItem);
    }

    @Transactional
    public Item create(Item item) {
        ItemEntity entity = toEntity(item);
        ItemEntity saved = itemRepository.save(entity);
        return toItem(saved);
    }

    @Transactional
    public Optional<Item> update(Long id, Item item) {
        return itemRepository.findById(id).map(existing -> {
            existing.setName(item.getName());
            existing.setDescription(item.getDescription());
            existing.setPrice(item.getPrice());
            return toItem(itemRepository.save(existing));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Item toItem(ItemEntity entity) {
        return Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    private ItemEntity toEntity(Item item) {
        return ItemEntity.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }
}
