package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KühlschrankResourceMapper {

    private final ItemResourceMapper itemMapper;

    // Spring fügt den ItemMapper hier automatisch ein (Dependency Injection)
    public KühlschrankResourceMapper(ItemResourceMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public KühlschrankResource map(Kühlschrank kühlschrank) {
        // 1. Wir holen die Items aus der Domäne und übersetzen jedes einzelne in eine Resource
        List<ItemResource> mappedItems = kühlschrank.getItems().stream()
                .map(item -> itemMapper.map(item)) // Ruft deinen ItemMapper auf
                .collect(Collectors.toList());

        // 2. Wir geben den Kühlschrank inkl. der fertigen Item-Liste zurück
        return new KühlschrankResource(
                kühlschrank.getId(),
                kühlschrank.getName(),
                mappedItems // <-- Die übersetzte Liste einfügen
        );
    }
}