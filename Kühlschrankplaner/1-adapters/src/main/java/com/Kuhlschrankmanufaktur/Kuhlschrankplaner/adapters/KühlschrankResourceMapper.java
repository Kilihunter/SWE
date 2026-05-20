package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import org.springframework.stereotype.Component;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KühlschrankResourceMapper {

    private final ItemResourceMapper itemMapper;
    
    public KühlschrankResourceMapper(ItemResourceMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public KühlschrankResource map(Kühlschrank kühlschrank) {
        List<ItemResource> mappedItems = kühlschrank.getItems().stream()
                .map(item -> itemMapper.map(item))
                .collect(Collectors.toList());

        return new KühlschrankResource(
                kühlschrank.getId(),
                kühlschrank.getName(),
                mappedItems 
        );
    }
}