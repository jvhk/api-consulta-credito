package jvhk.api.consulta.creditos.service;

import jvhk.api.consulta.creditos.dtos.CreditoDTO;
import jvhk.api.consulta.creditos.entities.Credito;
import jvhk.api.consulta.creditos.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceTest {

    @Mock
    private CreditoRepository repository;

    @InjectMocks
    private CreditoService service;

    @Test
    void listarCreditosByNumeroNfse_deveRetornarListaQuandoExistirCreditos() {
        String numeroNfse = "NF123";

        List<Credito> creditos = List.of(
                buildCredito("C001", "NF123", BigDecimal.valueOf(100.00)),
                buildCredito("C002", "NF123", BigDecimal.valueOf(200.00))
        );

        when(repository.findAllCreditosByNumeroNfse(numeroNfse)).thenReturn(creditos);

        List<CreditoDTO> resultado = service.listarCreditosByNumeroNfse(numeroNfse);

        assertEquals(2, resultado.size());
        assertEquals("C001", resultado.get(0).getNumeroCredito());
    }

    @Test
    void listarCreditosByNumeroNfse_deveRetornarListaVaziaQuandoNaoExistirCreditos() {
        when(repository.findAllCreditosByNumeroNfse("NF999")).thenReturn(Collections.emptyList());

        List<CreditoDTO> resultado = service.listarCreditosByNumeroNfse("NF999");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void listarCredito_deveRetornarCreditoQuandoExistir() {
        String numeroCredito = "C001";
        Credito credito = buildCredito(numeroCredito, "NF123", BigDecimal.valueOf(150.00));

        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito));

        CreditoDTO resultado = service.listarCredito(numeroCredito);

        assertNotNull(resultado);
        assertEquals("C001", resultado.getNumeroCredito());
    }

    @Test
    void listarCredito_deveRetornarNullQuandoNaoExistir() {
        when(repository.findByNumeroCredito("C999")).thenReturn(Optional.empty());

        CreditoDTO resultado = service.listarCredito("C999");

        assertNull(resultado);
    }

    private Credito buildCredito(String numeroCredito, String numeroNfse, BigDecimal valorIssqn) {
        Credito credito = new Credito();
        credito.setNumeroCredito(numeroCredito);
        credito.setNumeroNfse(numeroNfse);
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(valorIssqn);
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(false);
        credito.setAliquota(BigDecimal.valueOf(5.00));
        credito.setValorFaturado(BigDecimal.valueOf(1000.00));
        credito.setValorDeducao(BigDecimal.ZERO);
        credito.setBaseCalculo(BigDecimal.valueOf(1000.00));
        return credito;
    }
}
