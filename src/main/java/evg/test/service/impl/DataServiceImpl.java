package evg.test.service.impl;

import evg.test.dto.DataDTO;
import evg.test.service.DataService;
import evg.test.service.ElasticSearchClient;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.internal.InternalSearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    public List<DataDTO> getDataList(String[] category, String[] tags, String studio, String[] promotedIds, String orderPublishTime) {

        TransportClient client = elasticSearchClient.getClient();
        //SearchResponse response = client.prepareSearch().get();

        List<DataDTO> dataDTOS = new ArrayList<>();
        SearchResponse response = client.prepareSearch("index1")
                .setTypes("feeds")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("tag", "tag2"))                 // Query
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        for (SearchHit hit : response.getHits().getHits()) {
            Map element = ((InternalSearchHit) hit).getSource();
            String name = (String) element.get("name");
            String id = (String) element.get("id");
            String elementCategory = (String) element.get("category");
            String elementDescription = (String) element.get("description");
            String elementStudio = (String) element.get("studio");
            Date elementPublishTime = new Date((Long) element.get("publishTime"));
            Object[] objectArray =  ((ArrayList) element.get("tag")).toArray();
            String[] elementTag =  Arrays.copyOf(objectArray, objectArray.length, String[].class);;
            objectArray =  ((ArrayList) element.get("promotedIds")).toArray();
            String[] elementPromotedIds = Arrays.copyOf(objectArray, objectArray.length, String[].class);;
            DataDTO dataDTO = new DataDTO(name);
            dataDTO.setId(id);
            dataDTO.setCategory(elementCategory);
            dataDTO.setTag(elementTag);
            dataDTO.setDescription(elementDescription);
            dataDTO.setStudio(elementStudio);
            dataDTO.setPublishTime(elementPublishTime);
            dataDTO.setPromotedIds(elementPromotedIds);

            dataDTOS.add(dataDTO);


            System.out.println("hit" + hit);

        }


        //return Collections.unmodifiableList(Arrays.asList(FakeDataHolder.DATA_DTO_ARRAY));
        return Collections.unmodifiableList(dataDTOS);

    }
}
