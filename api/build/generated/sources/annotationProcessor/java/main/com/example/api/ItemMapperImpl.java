package com.example.api;

import com.example.domain.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-09T12:14:39-0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.4.jar, environment: Java 17.0.5 (Azul Systems, Inc.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDto toDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        return itemDto;
    }

    @Override
    public Item toItem(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        return item.build();
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( items.size() );
        for ( Item item : items ) {
            list.add( toDto( item ) );
        }

        return list;
    }
}
