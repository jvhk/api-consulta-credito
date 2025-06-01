package jvhk.api.consulta.creditos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jvhk.api.consulta.creditos.entities.Credito;
import jvhk.api.consulta.creditos.entities.EnumSimplesNacional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDTO {

    private String numeroCredito;
    private String numeroNfse;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataConstituicao;
    private BigDecimal valorIssqn;
    private String tipoCredito;
    private String simplesNacional;
    private BigDecimal aliquota;
    private BigDecimal valorFaturado;
    private BigDecimal valorDeducao;
    private BigDecimal baseCalculo;

    public CreditoDTO(Credito credito){
        setNumeroCredito(credito.getNumeroCredito());
        setNumeroNfse(credito.getNumeroNfse());
        setDataConstituicao(credito.getDataConstituicao());
        setValorIssqn(credito.getValorIssqn());
        setTipoCredito(credito.getTipoCredito());
        setSimplesNacional(EnumSimplesNacional.getDescricaoByValor(credito.isSimplesNacional()));
        setAliquota(credito.getAliquota());
        setValorFaturado(credito.getValorFaturado());
        setValorDeducao(credito.getValorDeducao());
        setBaseCalculo(credito.getBaseCalculo());
    }

}
