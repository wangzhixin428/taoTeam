package com.school.controller;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.common.uaa.entity.UserEntity;
import com.school.service.TestService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    TestService testService;

    /**
     * 老师权限或学生权限
     */
    @GetMapping("/grade")
    public Object rs(HttpServletRequest request){
        List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
        Map<String,Object> map=new HashMap<>();
//        map.put("张三",100);
//        System.out.println("test");
//        // 这里的使用本地Java API的方式调用远程的Restful接口
//        String echo = testService.echo();
//        System.out.println(echo);


        //创建低级容器
        BasicCredentialsProvider credent = new BasicCredentialsProvider();
//        credent.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("elastic","fF-DI3QEBuphT-bGxo14"));
        RestClient client=RestClient.builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credent);
                    }
                }).build();

        //使用JSON映射器传输数据
        RestClientTransport restClientTransport = new RestClientTransport(client, new JacksonJsonpMapper());

        //创建API客户端
        ElasticsearchClient client2 = new ElasticsearchClient(restClientTransport);
        
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("school")
                .query(QueryBuilders.multiMatch().fields("class","name").query("三")
                        .build()._toQuery())
                .build();
        SearchResponse<HashMap> search = null;
        try {
            search = client2.search(searchRequest, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        search.hits().hits().forEach(e->{
            resultList.add(e.source());
            System.out.println(e.source());
        });
        return resultList;
    }

}
