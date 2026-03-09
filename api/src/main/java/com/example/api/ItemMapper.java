package com.example.api;

import com.example.domain.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);

    Item toItem(ItemDto dto);

    List<ItemDto> toDtoList(List<Item> items);
}
