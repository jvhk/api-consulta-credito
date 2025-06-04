package jvhk.api.consulta.creditos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jvhk.api.consulta.creditos.dtos.CreditoDTO;
import jvhk.api.consulta.creditos.service.CreditoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebMvcTest(CreditoController.class)
public class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreditoService creditoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarCreditosByNumeroNfse_deveRetornarListaDeCreditos() throws Exception {
        String numeroNfse = "NF123";
        List<CreditoDTO> lista = List.of(
                buildDTO("C001", numeroNfse),
                buildDTO("C002", numeroNfse)
        );

        Mockito.when(creditoService.listarCreditosByNumeroNfse(numeroNfse)).thenReturn(lista);

        mockMvc.perform(get("/creditos/{numeroNfse}", numeroNfse))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].numeroCredito").value("C001"));
    }

    @Test
    void listarCredito_deveRetornarCreditoPorNumero() throws Exception {
        String numeroCredito = "C001";
        CreditoDTO dto = buildDTO(numeroCredito, "NF123");

        Mockito.when(creditoService.listarCredito(numeroCredito)).thenReturn(dto);

        mockMvc.perform(get("/creditos/credito/{numeroCredito}", numeroCredito))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroCredito").value("C001"))
                .andExpect(jsonPath("$.numeroNfse").value("NF123"));
    }

    private CreditoDTO buildDTO(String numeroCredito, String numeroNfse) {
        return new CreditoDTO(
                numeroCredito,
                numeroNfse,
                LocalDate.now(),
                BigDecimal.valueOf(100.0),
                "ISSQN",
                "Sim",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(1000),
                BigDecimal.ZERO,
                BigDecimal.valueOf(1000)
        );
    }
}
