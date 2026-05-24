package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.EinkaufslisteRepository;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.KühlschrankRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EinkaufslistenVerwaltungsServiceTest {

    @Mock
    private EinkaufslisteRepository einkaufslisteRepository;

    @Mock
    private KühlschrankRepository kühlschrankRepository;

    @InjectMocks
    private EinkaufslistenVerwaltungsService service;

    @Test
    void getEinkaufsliste_gibtListeZurückWennVorhanden() {
        Einkaufsliste liste = new Einkaufsliste("Wocheneinkauf");

        when(einkaufslisteRepository.findById(1))
                .thenReturn(Optional.of(liste));

        Einkaufsliste result = service.getEinkaufsliste(1);

        assertThat(result).isSameAs(liste);
    }

    @Test
    void getEinkaufsliste_wirftExceptionWennNichtVorhanden() {
        when(einkaufslisteRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getEinkaufsliste(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Einkaufsliste mit ID 99 nicht gefunden");
    }

    @Test
    void schreibeAuf_schreibtEintragAufListeUndSpeichert() {
        Einkaufsliste liste = mock(Einkaufsliste.class);

        when(einkaufslisteRepository.findById(1))
                .thenReturn(Optional.of(liste));

        when(einkaufslisteRepository.save(liste))
                .thenReturn(liste);

        Einkaufsliste result = service.schreibeAuf(1, 3, "Karotten");

        verify(liste).schreibeAuf(3, "Karotten");
        verify(einkaufslisteRepository).save(liste);

        assertThat(result).isSameAs(liste);
    }
}