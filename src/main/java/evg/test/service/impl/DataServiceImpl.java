package evg.test.service.impl;

import evg.test.dto.DataDTO;
import evg.test.service.DataService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private static final DataDTO[] dataDTOStaticArray = {new DataDTO("26cba770-0115-45b2-8cc0-185dac2243bb", "content1"), new DataDTO("11baca0e-3dbc-4066-a035-ce5bcf07ab9d", "content2")};

    @Override
    public List<DataDTO> getDataList(String[] category, String[] tags, String[] studio, String[] promotedIds, String orderPublishTime) {
        return Collections.unmodifiableList(Arrays.asList(dataDTOStaticArray));
    }
}
