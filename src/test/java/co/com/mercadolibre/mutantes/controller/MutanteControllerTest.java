package co.com.mercadolibre.mutantes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import co.com.mercadolibre.mutantes.exception.MutantException;
import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.service.MutantServiceImpl;

@SpringBootTest
public class MutanteControllerTest {
	
	private static final String CONTENT_TYPE_JSON = "application/json";
	
	private static final String URI = "/mutant";
	
	@InjectMocks
    private MutanteController mutanteController;
	
	@Mock
    private MutantServiceImpl mutantService;
	
	private MockMvc mockMvc;
	
	private String[] dnaMutante = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
	
	private String[] dnaHumano = new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
	
	private String[] dnaInvalido = new String[]{"ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA"};
	
	@BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mutanteController)
                .build();
    }
	
	@Test
    void encontrarADNMutanteTest() throws Exception {
		String mockMutantBody = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
		
		mockIsMutantService(dnaMutante, true);
		
		ResultActions resultActions = mockMvc.perform(post(URI)
				.contentType(CONTENT_TYPE_JSON).content(mockMutantBody));
		
		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");
		
	}
	
	@Test
    void encontrarADNHumanoTest() throws Exception {
		String mockHumantBody = "{\"dna\":[\"GTTTGA\",\"ATGTGC\",\"AGTTGG\",\"AGATGG\",\"CCCCTA\",\"TCGCTG\"]}";
		
		mockIsMutantService(dnaHumano, true);
		
		ResultActions resultActions = mockMvc.perform(post(URI)
				.contentType(CONTENT_TYPE_JSON).content(mockHumantBody));
		
		MvcResult result = resultActions.andExpect(status().isForbidden()).andReturn();
		Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");
		
	}
	
	@Test
    void validarErrorEnRequestTest() throws Exception {

        String mockInvalidBody = "{\"dna\":[\"ABGCGA\",\"CAGTGC\",\"TTATGG\",\"AGAAGG\",\"CCCCTA\"]}";

        // Alien DNA to analyze with the Mutant Service that is mocked
        Mockito.doThrow(new ServiceException("Alien DNA")).when(mutantService).isMutant(dnaInvalido);

        // It calls the mutant endpoint
        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockInvalidBody));

        // After the Call to the Mutant Endpoint, the response must be empty with a 400 status code (Bad Request)
        MvcResult result = resultActions.andExpect(status().isUnprocessableEntity()).andReturn();
        Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");
    }
	
	/**
	 * Método que simula la búsqueda del ADN 
	 * @param dna Secuencia de ADN a analizar
	 * @param expectedResult Resultado esperado
	 * @throws ServiceException Excepción en el servicio
	 */
	private void mockIsMutantService(String[] dna, boolean expectedResult) 
			throws ServiceException {
        Mockito.when(mutantService.isMutant(dna)).thenReturn(expectedResult);
    }
}
