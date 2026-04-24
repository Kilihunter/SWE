package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import org.springframework.stereotype.Component;

@Component
public class KühlschrankResourceMapper {

    public KühlschrankResource map(Kühlschrank domainKühlschrank) {
        Integer id = domainKühlschrank.getId();
        return new KühlschrankResource(id, domainKühlschrank.getName());
    }
}