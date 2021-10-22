package co.com.mercadolibre.mutantes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.DNA;
import co.com.mercadolibre.mutantes.service.MutantService;

/**
 * Clase controlador para rest Mutantes
 * @author omar
 *
 */
@RestController
public class MutanteController {
	
	private static final Logger logger = LoggerFactory.getLogger(MutanteController.class);
	
	@Autowired
	MutantService mutantService;
	/**
	 * Método que analiza el DNA ingresado
	 * @param dna DNA del sujeto a analizar
	 * @return HTTP 200-OK cuando es mutante o HTTP 403-Forbudden para humano
	 */
	@PostMapping(value = "/mutant")
	public ResponseEntity<String> isMutant(@RequestBody DNA dna) {
		ResponseEntity<String> responseEntity;
		boolean isMutant;
		try {
            isMutant = mutantService.isMutant(dna.getDna());

            if (isMutant) {
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }


        } catch (ServiceException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            logger.error(e.getMessage());
        }
		return responseEntity;
	}
}
