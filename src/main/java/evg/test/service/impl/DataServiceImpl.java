package evg.test.service.impl;

import evg.test.dto.DataDTO;
import evg.test.service.DataService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private static final DataDTO[] dataDTOStaticArray = {new DataDTO("content1"), new DataDTO("content2")};

    @Override
    public List<DataDTO> getDataList(String[] category, String[] tags, String[] studio, String[] promotedIds, String orderPublishTime) {
        return Collections.unmodifiableList(Arrays.asList(dataDTOStaticArray));
    }
}
