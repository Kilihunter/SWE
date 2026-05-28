/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.KühlschrankRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class KühlschrankVerwaltungsServiceTest {

    @Mock
    private KühlschrankRepository kühlschrankRepository;

    @Mock
    private ItemFactory itemFactory;

    @InjectMocks
    private KühlschrankVerwaltungsService service;

    @Test
    void getKühlschrank_gibtKühlschrankZurückWennVorhanden() {
        Kühlschrank kühlschrank = new Kühlschrank("Kueche");

        when(kühlschrankRepository.findById(1))
                .thenReturn(Optional.of(kühlschrank));

        Kühlschrank result = service.getKühlschrank(1);

        assertThat(result).isSameAs(kühlschrank);
    }
    @Test
    void getKühlschrank_wirftExceptionWennNichtVorhanden() {
        when(kühlschrankRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getKühlschrank(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Kühlschrank mit ID 99 nicht gefunden");
    }

    @Test
    void kühlschrankAnlegen_speichertNeuenKühlschrank() {
        Kühlschrank gespeicherterKühlschrank = new Kühlschrank("Kueche");

        when(kühlschrankRepository.save(any(Kühlschrank.class)))
                .thenReturn(gespeicherterKühlschrank);

        Kühlschrank result = service.kühlschrankAnlegen("Kueche");

        assertThat(result).isSameAs(gespeicherterKühlschrank);
        verify(kühlschrankRepository).save(any(Kühlschrank.class));
    }

    @Test
    void itemEntfernen_entferntItemUndSpeichertKühlschrank() {
        Kühlschrank kühlschrank = mock(Kühlschrank.class);

        when(kühlschrankRepository.findById(1))
                .thenReturn(Optional.of(kühlschrank));

        when(kühlschrankRepository.save(kühlschrank))
                .thenReturn(kühlschrank);

        Kühlschrank result = service.itemEntfernen(1, 42);

        verify(kühlschrank).itemEntfernen(42);
        verify(kühlschrankRepository).save(kühlschrank);

        assertThat(result).isSameAs(kühlschrank);
    }

    @Test
    void itemTeilweiseVerbraucht_verbrauchtItemUndSpeichertKuehlschrank() {
        Kühlschrank kühlschrank = mock(Kühlschrank.class);

        when(kühlschrankRepository.findById(1))
                .thenReturn(Optional.of(kühlschrank));

        when(kühlschrankRepository.save(kühlschrank))
                .thenReturn(kühlschrank);

        Kühlschrank result = service.itemTeilweiseVerbraucht(1, 42, 2);

        verify(kühlschrank).itemVerbrauchen(42, 2);
        verify(kühlschrankRepository).save(kühlschrank);

        assertThat(result).isSameAs(kühlschrank);
    }
}
    */