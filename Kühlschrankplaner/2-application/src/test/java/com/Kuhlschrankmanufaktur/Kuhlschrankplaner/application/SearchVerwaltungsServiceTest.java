package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Haltbarkeitsdatum;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Item;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.KühlschrankRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVerwaltungsServiceTest {

    @Mock
    private KühlschrankRepository repository;

    @InjectMocks
    private SearchVerwaltungsService service;

    @Test
    void findeAblaufendeItems_findetNurItemsInnerhalbDerGrenzeUndSortiertNachHaltbarkeit() {
        Item morgen = itemMitHaltbarkeit(LocalDate.now().plusDays(1));
        Item inDreiTagen = itemMitHaltbarkeit(LocalDate.now().plusDays(3));
        Item spaeter = itemMitHaltbarkeit(LocalDate.now().plusDays(10));
        Item abgelaufen = itemMitHaltbarkeit(LocalDate.now().minusDays(1));

        Kühlschrank kühlschrank = mock(Kühlschrank.class);

        when(kühlschrank.getItems())
                .thenReturn(List.of(spaeter, inDreiTagen, morgen, abgelaufen));

        when(repository.findById(1))
                .thenReturn(Optional.of(kühlschrank));

        List<Item> result = service.findeAblaufendeUndAbgelaufeneItems(1, 3);

        assertThat(result).containsExactly(abgelaufen,morgen, inDreiTagen);
    }

    @Test
    void filterUndSortiereBestand_filtertStatusAbgelaufen() {
        Item abgelaufen = itemMitHaltbarkeit(LocalDate.now().minusDays(1));
        Item ok = itemMitHaltbarkeit(LocalDate.now().plusDays(5));

        Kühlschrank kühlschrank = mock(Kühlschrank.class);

        when(kühlschrank.getItems())
                .thenReturn(List.of(ok, abgelaufen));

        when(repository.findAll())
                .thenReturn(List.of(kühlschrank));

        List<Item> result = service.filterUndSortiereBestand(
                null,
                null,
                "ABGELAUFEN",
                "haltbarkeit"
        );

        assertThat(result).containsExactly(abgelaufen);
    }

    private Item itemMitHaltbarkeit(LocalDate haltbarkeit) {
        Item item = mock(Item.class);

        when(item.getHaltbarkeit())
                .thenReturn(new Haltbarkeitsdatum(haltbarkeit));

        return item;
    }
}