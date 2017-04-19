package evg.test.service;

import org.elasticsearch.client.transport.TransportClient;

public interface ElasticSearchClient {

    TransportClient getClient();

}
