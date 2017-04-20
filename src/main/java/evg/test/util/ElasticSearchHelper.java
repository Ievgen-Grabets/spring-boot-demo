package evg.test.util;

import evg.test.dto.DataDTO;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.*;

public class ElasticSearchHelper {

    public static List<QueryBuilder> addQueryToList(List<QueryBuilder> queryBuilderList, String key, String... values) {

        if (Objects.isNull(values) || Objects.isNull(values[0])) {
            return queryBuilderList;
        }

        QueryBuilder query;

        if(values.length==1){
            query = QueryBuilders.termQuery(key, values[0]);
        }else{
            Set<String> queryValuesSet = new HashSet<>(Arrays.asList(values));
            query = QueryBuilders.termsQuery(key, queryValuesSet);
        }

        queryBuilderList.add(query);
        return queryBuilderList;

    }

    public static DataDTO parseDataDtoFromSearchHit(SearchHit hit){
        Map element = hit.getSource();

        String id = (String) element.get("id");
        String name = (String) element.get("name");
        String elementCategory = (String) element.get("category");
        String elementDescription = (String) element.get("description");
        String elementStudio = (String) element.get("studio");
        Date elementPublishTime = new Date((Long) element.get("publishTime"));
        Object[] objectArray =  ((ArrayList) element.get("tag")).toArray();
        String[] elementTag =  Arrays.copyOf(objectArray, objectArray.length, String[].class);
        objectArray = ((ArrayList) element.get("promotedIds")).toArray();
        String[] elementPromotedIds = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        DataDTO dataDTO = new DataDTO(name);
        dataDTO.setId(id);
        dataDTO.setCategory(elementCategory);
        dataDTO.setTag(elementTag);
        dataDTO.setDescription(elementDescription);
        dataDTO.setStudio(elementStudio);
        dataDTO.setPublishTime(elementPublishTime);
        dataDTO.setPromotedIds(elementPromotedIds);

        return dataDTO;

    }

}
