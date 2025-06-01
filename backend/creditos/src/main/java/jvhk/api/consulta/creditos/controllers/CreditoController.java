package jvhk.api.consulta.creditos.controllers;

import jvhk.api.consulta.creditos.dtos.CreditoDTO;
import jvhk.api.consulta.creditos.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> listaCreditosByNumeroNfse(@PathVariable("numeroNfse") String numeroNfse){
        return new ResponseEntity(creditoService.listarCreditosByNumeroNfse(numeroNfse), HttpStatus.OK);
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> listaCredito(@PathVariable("numeroCredito") String numeroCredito){
        return new ResponseEntity(creditoService.listarCredito(numeroCredito), HttpStatus.OK);
    }

}
