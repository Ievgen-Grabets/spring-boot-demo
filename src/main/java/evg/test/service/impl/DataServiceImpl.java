package evg.test.service.impl;

import evg.test.dto.DataDTO;
import evg.test.service.DataService;
import evg.test.service.ElasticSearchClient;
import evg.test.util.ElasticSearchHelper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    public List<DataDTO> getDataList(String[] category, String[] tags, String studio, String[] promotedIds, String order) {

        TransportClient client = elasticSearchClient.getClient();
        List<QueryBuilder> queryBuilderList = new ArrayList<>();
        queryBuilderList = ElasticSearchHelper.addQueryToList(queryBuilderList, "category", category);
        queryBuilderList = ElasticSearchHelper.addQueryToList(queryBuilderList, "tag", tags);
        queryBuilderList = ElasticSearchHelper.addQueryToList(queryBuilderList, "studio", studio);
        queryBuilderList = ElasticSearchHelper.addQueryToList(queryBuilderList, "promotedIds", promotedIds);

        BoolQueryBuilder query = QueryBuilders.boolQuery();
        for(QueryBuilder queryBuilder: queryBuilderList){
            query.must(queryBuilder);
        }

        SearchRequestBuilder requestBuilder = client.prepareSearch("index1");

        requestBuilder.setTypes("feeds").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(query).setFrom(0).setSize(60);

        if(Objects.equals(order, "recent")){
            requestBuilder.addSort("publishTime" , SortOrder.DESC);
        }

        SearchResponse response = requestBuilder.get();
        SearchHit[] hits = response.getHits().getHits();
        List<DataDTO> dataDTOS = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            dataDTOS.add(ElasticSearchHelper.parseDataDtoFromSearchHit(hit));
        }

        return Collections.unmodifiableList(dataDTOS);

    }
}
