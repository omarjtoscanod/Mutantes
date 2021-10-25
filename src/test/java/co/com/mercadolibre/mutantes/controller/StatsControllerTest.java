package co.com.mercadolibre.mutantes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import co.com.mercadolibre.mutantes.model.Statistics;
import co.com.mercadolibre.mutantes.service.StatsServiceImpl;

@SpringBootTest
public class StatsControllerTest {
	
	private static final String URI = "/stats";
	
	@InjectMocks
    private StatsController controller;
	
	@Mock
    private StatsServiceImpl statsService;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }
	
	@Test
    public void testStatsController() throws Exception {
		Mockito.when(statsService.getStats()).thenReturn(new Statistics(1, 10));
		ResultActions resultActions = mockMvc.perform(get(URI));
		
		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		Assert.isTrue(!result.getResponse().getContentAsString().isEmpty(), "Response body must not be empty");
	}
}
