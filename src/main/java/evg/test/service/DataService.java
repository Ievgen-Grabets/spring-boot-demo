package evg.test.service;

import evg.test.dto.DataDTO;

import java.util.List;

public interface DataService {

    List<DataDTO> getDataList(String[] category, String[] tags, String[] studio, String[] promotedIds, String orderPublishTime);

}
