package com.example.web;

import com.example.api.ItemDto;
import com.example.api.ItemMapper;
import com.example.domain.Item;
import com.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public List<ItemDto> findAll() {
        return itemMapper.toDtoList(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        return itemService.findById(id)
                .map(item -> ResponseEntity.ok(itemMapper.toDto(item)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestBody ItemDto dto) {
        Item item = itemMapper.toItem(dto);
        return itemMapper.toDto(itemService.create(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto dto) {
        return itemService.update(id, itemMapper.toItem(dto))
                .map(item -> ResponseEntity.ok(itemMapper.toDto(item)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (itemService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
