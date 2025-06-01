package jvhk.api.consulta.creditos.service;

import jvhk.api.consulta.creditos.repository.CreditoRepository;
import jvhk.api.consulta.creditos.dtos.CreditoDTO;
import jvhk.api.consulta.creditos.entities.Credito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository repository;

    public List<CreditoDTO> listarCreditosByNumeroNfse(String numeroNfse){
        List<Credito> creditos = repository.findAllCreditosByNumeroNfse(numeroNfse);
        if(!creditos.isEmpty()){
            List<CreditoDTO> listaRetorno = new ArrayList<>();
            return creditos.stream().map(CreditoDTO::new).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public CreditoDTO listarCredito(String numeroCredito) {
        var credito = repository.findByNumeroCredito(numeroCredito);
        if (credito.isPresent()) {
            return new CreditoDTO(credito.get());
        }
        return null;
    }
}
