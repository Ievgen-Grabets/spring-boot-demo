package evg.test.service.impl;

import evg.test.service.ElasticSearchClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class ElasticSearchClientImpl implements ElasticSearchClient {

    private TransportClient client;

    public synchronized TransportClient getClient() {
        if(client==null){
            TransportClient elasticSearchClient = null;
            try {
                elasticSearchClient = new PreBuiltTransportClient(Settings.EMPTY)
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
            } catch (UnknownHostException e) {
                System.err.println("Error occurred on creating elastic search client");
                System.err.println(e.getMessage());
                System.err.println(e.getCause());
            }
            client = elasticSearchClient;
            return client;
        }else{
            return client;
        }
    }
}
