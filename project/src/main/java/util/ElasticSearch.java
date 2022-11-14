package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ElasticSearch {

	public static void main(String[] args) throws IOException {
		
//		Map<String, Object> map = getDocument("seoul_wifi", "1");
//		System.out.println(map.get("gu_nm"));
		
		// 여러 건 조회
		HttpHost host = new HttpHost("localhost", 9200);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client= new RestHighLevelClient(restClientBuilder);
		
		SearchRequest searchRequest = new SearchRequest("seoul_wifi");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//		sourceBuilder.query(QueryBuilders.matchQuery("gu_nm", "강남구"));
		sourceBuilder.query(QueryBuilders.matchQuery("place_nm", "도서관"));
		searchRequest.source(sourceBuilder);
		
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
//		System.out.println(searchResponse.getHits().getHits());
		List<Map<String, Object>> list = new ArrayList();
		for (SearchHit sh : searchResponse.getHits().getHits()) {
			list.add(sh.getSourceAsMap());
		}
		for (Map<String, Object> map : list) {
			System.out.println(map.get("gu_nm"));
			System.out.println(map.get("place_nm"));
			System.out.println("--------------------");
		}
	}
	
	
	// 한건 조회
	public static Map<String, Object> getDocument(String index, String id) throws IOException {

		HttpHost host = new HttpHost("localhost", 9200);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client= new RestHighLevelClient(restClientBuilder);
		
		GetRequest getRequest = new GetRequest(index, id);
		RequestOptions options = RequestOptions.DEFAULT;
		
		GetResponse getResponse = client.get(getRequest, options);
		Map<String, Object> map = getResponse.getSourceAsMap();
		
		return map;
	}
}
