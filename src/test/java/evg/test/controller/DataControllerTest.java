package evg.test.controller;

import evg.test.dto.DataDTO;
import evg.test.service.DataService;
import evg.test.util.CommonConstHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.when;

public class DataControllerTest {

    @InjectMocks
    private DataController controller;

    @Mock
    private DataService mockDataService;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();

    }

    @Test
    public void testListPeopleInGroup() throws Exception {
        DataDTO[] dataDTOS = {new DataDTO("11baca0e-3dbc-4066-a035-ce5bcf07ab9d", "content1")};
        List<DataDTO> expectedDataDTOs = Arrays.asList(dataDTOS);
        when(mockDataService.getDataList(
                new String[]{"cat1", "cat2"},
                new String[]{"tggg"},
                null,
                null,
                "relevancy")).thenReturn(expectedDataDTOs);

        mockMvc.perform(get("/getData?category=cat1,cat2&tags=tggg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].id", is("11baca0e-3dbc-4066-a035-ce5bcf07ab9d")));
    }


}
