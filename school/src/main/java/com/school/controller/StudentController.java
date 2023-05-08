package com.school.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.school.service.TestService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        HashMap<String, Object> map=new HashMap<>();
//        map.put("张三",100);
//        System.out.println("test");
//        // 这里的使用本地Java API的方式调用远程的Restful接口
//        String echo = testService.echo();
//        System.out.println(echo);


        //创建低级容器
        /*BasicCredentialsProvider credent = new BasicCredentialsProvider();
//        credent.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("elastic","fF-DI3QEBuphT-bGxo14"));
        RestClient client=RestClient.builder(new HttpHost("127.0.0.1", 9200))
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
        });*/

        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "34339480";
        String secret = "1ed08c7fb44db63ff1f71648dc0654c7";
        TaobaoClient taobaoClient = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(5L);
        req.setPageNo(1L);
        req.setPlatform(1L);
        req.setHasCoupon(true);
        req.setSort("total_sales_des");
        req.setQ("男装");
        req.setMaterialId(2836L);
        req.setHasCoupon(false);
        req.setIp("13.2.33.4");
        req.setAdzoneId(114864450375L);
//        req.setNeedFreeShipment(true);
//        req.setNeedPrepay(true);
//        req.setIncludePayRate30(true);
//        req.setIncludeGoodRate(true);
//        req.setIncludeRfdRate(true);
//        req.setNpxLevel(2L);
//        req.setDeviceEncrypt("MD5");
//        req.setDeviceValue("xxx");
//        req.setDeviceType("IMEI");
//        req.setEndKaTkRate(1234L);
//        req.setStartKaTkRate(1234L);
//        req.setLockRateEndTime(1567440000000L);
//        req.setLockRateStartTime(1567440000000L);
//        req.setLongitude("121.473701");
//        req.setLatitude("31.230370");
//        req.setCityCode("310000");
////        req.setSellerIds("1,2,3,4");
//        req.setSpecialId("2323");
//        req.setRelationId("3243");
//        req.setPageResultKey("abcdef");
//        req.setUcrowdId(1L);
//        List<TbkDgMaterialOptionalRequest.Ucrowdrankitems> list2 = new ArrayList<TbkDgMaterialOptionalRequest.Ucrowdrankitems>();
//        TbkDgMaterialOptionalRequest.Ucrowdrankitems obj3 = new TbkDgMaterialOptionalRequest.Ucrowdrankitems();
//        list2.add(obj3);
//        obj3.setCommirate(1234L);
//        obj3.setPrice("10.12");
//        obj3.setItemId("542808901898");
//        req.setUcrowdRankItems(list2);
//        req.setGetTopnRate(0L);
//        req.setBizSceneId("1");
//        req.setPromotionType("2");
        TbkDgMaterialOptionalResponse rsp = null;
        try {
            rsp = taobaoClient.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        String body = rsp.getBody();

        System.out.println(rsp.getBody());
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

}
