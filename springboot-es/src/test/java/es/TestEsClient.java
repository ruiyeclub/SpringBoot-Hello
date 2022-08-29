package es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import es.entity.Product;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.assertj.core.util.Lists;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * @Author: ttlikeu。
 * @Date: 2022/6/29 17:19
 */


public class TestEsClient {
    String ip = "es的ip地址";
    String port = "9200";
    String scheme = "https";
    String username = "elastic";
    String password = "password";


    @Test
    public void TestEsConnection() throws Exception {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLIOSessionStrategy sessionStrategy = new SSLIOSessionStrategy(sslContext, NoopHostnameVerifier.INSTANCE);

        RestClient restClient = RestClient.builder(
                new HttpHost(ip, Integer.parseInt(port), scheme)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                httpClientBuilder.setSSLStrategy(sessionStrategy);
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpClientBuilder;
            }
        }).build();
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);
        //end::create-client
        //tag::first-request
        SearchResponse<Product> search = client.search(s -> s
                        .index("products")
                        .query(q -> q
                                .term(t -> t
                                        .field("name")
                                        .value(v -> v.stringValue("bicycle"))
                                )),
                Product.class);

        for (Hit<Product> hit : search.hits().hits()) {
            processProduct(hit.source());
        }


    }

    private void processProduct(Product p) {
    }

    /**
     * 获取客户端
     *
     * @return
     * @throws Exception
     */
    public ElasticsearchClient getClient() throws Exception {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLIOSessionStrategy sessionStrategy = new SSLIOSessionStrategy(sslContext, NoopHostnameVerifier.INSTANCE);

        RestClient restClient = RestClient.builder(
                new HttpHost(ip, Integer.parseInt(port), scheme)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                httpClientBuilder.setSSLStrategy(sessionStrategy);
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpClientBuilder;
            }
        }).build();
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);
        return client;
    }




    public boolean existsIndex(String index) throws Exception {
        ElasticsearchClient esClient = getClient();
        GetResponse<CtTrendsCanal> response = esClient.get(g -> g
                        .index(index)
                        .id("3"),
                CtTrendsCanal.class
        );
        return response.found();
    }

    /**
     * 测试es数据添加
     */
    @Test
    public void add() throws Exception {
        ElasticsearchClient client = getClient();
        CtTrendsCanal ct = new CtTrendsCanal();
        ct.setId(5L);
        ct.setTitle("测试");
        ct.setContent("我是一个程序员222");

        EsIndexAndId t = new EsIndexAndId(5L, index, ct);
        if (!existsIndex(index)) {
            try {
                IndexRequest<?> request = IndexRequest.of(i -> i
                        .index(index)
                );
                client.index(request);
            } catch (Exception e) {
                System.out.println(ServiceUtils.getStackTrace(e));
            }
        }
        CreateRequest createRequest = CreateRequest.of(i -> i
                .index(index)
                .id(t.getId().toString())
                .document(t.getObject())
        );
        CreateResponse indexResponse = null;
        try {
            indexResponse = client.create(createRequest);
        } catch (Exception e) {
            System.out.println(ServiceUtils.getStackTrace(e));
        }
    }

    /**
     * 测试es数据更新
     */
    @Test
    public void TestUpdate() throws Exception {
        ElasticsearchClient client = getClient();
        CtTrendsCanal ct = new CtTrendsCanal();
        ct.setId(4L);
        ct.setTitle("测试");
        ct.setContent("我是一个程序员,啦啦啦，这里更新啦1");

        EsIndexAndId t = new EsIndexAndId(4L, index, ct);
        if (!existsIndex(index)) {
            try {
                IndexRequest<?> request = IndexRequest.of(i -> i
                        .index(index)
                );
                client.index(request);
            } catch (Exception e) {
                System.out.println(ServiceUtils.getStackTrace(e));
            }
        }
        UpdateRequest updateRequest = UpdateRequest.of(i -> i
                .index(index)
                .id(t.getId().toString())
                .scriptedUpsert(false)
                .docAsUpsert(false)
                .refresh(Refresh.WaitFor)
                .detectNoop(false)
                .retryOnConflict(3)
                .upsert(t.getObject())
                .doc(t.getObject())
        );
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(updateRequest, t.getObject().getClass());
        } catch (Exception e) {
            System.out.println(ServiceUtils.getStackTrace(e));
        }
        System.out.println(updateResponse);
    }

    /**
     * 测试查询
     */
    @Test
    public void TestSelect() throws Exception {
        ElasticsearchClient client = getClient();
        CtTrendsCanal ct = new CtTrendsCanal();

        EsIndexAndId t = new EsIndexAndId(4L, index, ct);
        // 检查参数
        GetRequest getRequest = GetRequest.of(i -> i
                .id(t.getId().toString())
                .index(t.getIndex())
        );
        GetResponse<?> getResponse = client.get(getRequest, t.getObject().getClass());
        try {
            getResponse = client.get(getRequest, t.getObject().getClass());
        } catch (Exception e) {
            System.out.println(ServiceUtils.getStackTrace(e));
        }

        System.out.println(getResponse);

        Object source = getResponse.source();
        System.out.println(JSONObject.parseObject(JSON.toJSONString(source), t.getObject().getClass()));
        ;
        System.out.println(JSON.toJSONString(source));
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void TestExistsIndex() throws Exception {
        ElasticsearchClient client = getClient();

        try {
            BooleanResponse existsResponse = client.indices().exists(b -> b.index(index));
            System.out.println(existsResponse.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (getIndexResponse != null) {
//            return true;
//        }
//        return false;

    }

    /**
     * 测试删除文档
     *
     * @throws Exception
     */
    @Test
    public void TestDeleteDoc() throws Exception {
        ElasticsearchClient client = getClient();

        DeleteRequest request = DeleteRequest.of(i -> i.id("3").index(index));
        //同步执行
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除索引
     *
     * @throws Exception
     */
    @Test
    public void TestDeleteIndex() throws Exception {
        ElasticsearchClient client = getClient();

        /**先创建索引 */
        CreateIndexRequest createIndexRequest = CreateIndexRequest.of(i -> i.index(index));
        client.indices().create(createIndexRequest);

        /**再删除索引 */
        DeleteIndexRequest deleteRequest = DeleteIndexRequest.of(dir -> dir.index(index));
        DeleteIndexResponse deleteResponse = null;
        try {
            deleteResponse = client.indices().delete(deleteRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(deleteResponse);
    }

    /**
     * 测试批量数据插入
     */
    @Test
    public void TestUpsertAll() throws Exception {
        ElasticsearchClient client = getClient();

        List<EsIndexAndId> tsList = Lists.newArrayList();

        CtTrendsCanal ct = new CtTrendsCanal();
        ct.setId(100L);
        ct.setTitle("测试");
        ct.setContent("我是一个程序员1,啦啦啦，这里更新啦1");

        CtTrendsCanal ct2 = new CtTrendsCanal();
        ct2.setId(101L);
        ct2.setTitle("测试");
        ct2.setContent("我是一个程序员2,啦啦啦，这里更新啦2");

        tsList.add(new EsIndexAndId(ct.getId(), index, ct));
        tsList.add(new EsIndexAndId(ct2.getId(), index, ct2));

        BulkRequest.Builder br = new BulkRequest.Builder();
        for (EsIndexAndId es : tsList) {
            if (checkIndexTypeId(es)) {
                continue;
            }
            br.operations(op -> op
                    .index(idx -> idx
                            .index(es.getIndex())
                            .id(es.getId().toString())
                            .document(es.getObject())
                    )
            );
        }

        BulkResponse response = client.bulk(br.build());
        System.out.println(response);
        if (response.errors()) {
            System.out.println("es 批量添加有错误");
            for (BulkResponseItem item : response.items()) {
                if (item.error() != null) {
                    System.out.println(item.error().reason());
                }
            }
        }

    }


    /**
     * 测试批量删除
     */
    @Test
    public void TestDeleteByIds() throws Exception {
        ElasticsearchClient client = getClient();
        List<EsIndexAndId> tsList = Lists.newArrayList();

        CtTrendsCanal ct = new CtTrendsCanal();
        ct.setId(100L);
        ct.setTitle("测试");
        ct.setContent("我是一个程序员1,啦啦啦，这里更新啦1");

        CtTrendsCanal ct2 = new CtTrendsCanal();
        ct2.setId(101L);
        ct2.setTitle("测试");
        ct2.setContent("我是一个程序员2,啦啦啦，这里更新啦2");

        tsList.add(new EsIndexAndId(ct.getId(), index, ct));
        tsList.add(new EsIndexAndId(ct2.getId(), index, ct2));

        BulkRequest.Builder br = new BulkRequest.Builder();
        for (EsIndexAndId es : tsList) {
            if (checkIndexTypeId(es)) {
                continue;
            }
            br.operations(op -> op
                    .delete(idx -> idx
                            .index(es.getIndex())
                            .id(es.getId().toString())
                    )

            );
        }
        BulkResponse response = client.bulk(br.build());
        System.out.println(response);
    }


    /**
     * 批量查询
     */
    @Test
    public void TestBatchSearch() throws Exception {
        ElasticsearchClient client = getClient();
        String searchText = "";
        BoolQuery booleanQuery = BoolQuery.of(b -> b.filter(f -> f
                .term(t -> t
                        .field("status")
                        .value(1)
                )));

//        Query query = Query.of(
//                q -> q
//                        .bool(booleanQuery
//                        )
//        );

        Query statusQuery = Query.of(q -> q
                .term(t -> t.field("status").value(1))
        );
        Query trendsIdQuery = Query.of(q -> q
                .term(t -> t.field("trendsId").value(1))
        );
        Query query = Query.of(q -> q
                .bool(b -> b
                        .filter(statusQuery,
                                trendsIdQuery
                        )
                )
        );


        SearchResponse response = client.search(s -> s
                        .index(index)
                        .query(query)
                        .from(0)
                        .size(10)
                        .timeout("30s")
                ,
                CtTrendsComments.class
        );
        System.out.println(response);

    }

    @Test
    public void TestBuildSearch() throws Exception {
        ElasticsearchClient client = getClient();
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        String queryText = "";
        if (StringUtils.isNotBlank(queryText)) {
            MultiMatchQuery.Builder multiMatchQueryBuilder = new MultiMatchQuery.Builder();
            multiMatchQueryBuilder.fields("content", "title").query(queryText);
            Query multiMatchQuery = multiMatchQueryBuilder.build()._toQuery();
            boolQueryBuilder.must(multiMatchQuery);
        }

        TermQuery.Builder termQueryBuilder = new TermQuery.Builder();
        Query termQuery = termQueryBuilder.field("status").value(1).build()._toQuery();
        boolQueryBuilder.filter(termQuery);
        Query boolQuery = boolQueryBuilder.build()._toQuery();

        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        // 查询条件
        searchRequestBuilder.query(boolQuery);

        // 索引
        searchRequestBuilder.index(index);

        // 排序
        SortOptions sortOptions = SortOptions.of(so -> so.field(f -> f.field("modified").order(SortOrder.Desc)));
        searchRequestBuilder.sort(sortOptions);
        searchRequestBuilder.from(0);
        searchRequestBuilder.size(10);
        searchRequestBuilder.timeout(30 + "s");
        SearchResponse<CtTrends> search = client.search(searchRequestBuilder.build(), CtTrends.class);
        System.out.println(search);
    }


    @Test
    public void TestGetTrendsInfo() throws Exception {
        ElasticsearchClient client = getClient();

        // 设置boolQuery条件
        Query statusFilter = Query.of(q -> q
                .term(t -> t.field("status").value(1))
        );
        Query trendsFilter = Query.of(q -> q
                .term(t -> t.field("id").value(8))
        );
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.filter(statusFilter, trendsFilter);
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        // 查询条件
        searchRequestBuilder.query(boolQueryBuilder.build()._toQuery());

        // 索引
        searchRequestBuilder.index(index);
        searchRequestBuilder.from(0);
        searchRequestBuilder.size(10);
        searchRequestBuilder.timeout(30 + "s");
        // 查询条件--->生成DSL查询语句
        SearchResponse<CtTrends> search = client.search(searchRequestBuilder.build(), CtTrends.class);
        System.out.println(search);
    }

    /**
     * 查询关注用户相关的列表
     * @throws Exception
     */
    @Test
    public void TestQueryAuthorList()throws Exception  {
        ElasticsearchClient client = getClient();

        // 设置boolQuery条件
        Query statusFilter = Query.of(q -> q
                .term(t -> t.field("status").value(1))
        );
        TermsQuery.Builder termsQuery = new TermsQuery.Builder();
        ArrayList<FieldValue> authorList = Lists.newArrayList();
        authorList.add(new FieldValue.Builder().longValue(1).build());
        authorList.add(new FieldValue.Builder().longValue(4).build());
        termsQuery.field("author");
        termsQuery.terms(new TermsQueryField.Builder().value(authorList).build());
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.filter(statusFilter, termsQuery.build()._toQuery());
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        // 查询条件
        searchRequestBuilder.query(boolQueryBuilder.build()._toQuery());

        // 索引
        String index = "这里是索引";
        searchRequestBuilder.index(index);
        searchRequestBuilder.from(0);
        searchRequestBuilder.size(10);
        searchRequestBuilder.timeout(30 + "s");
        // 查询条件--->生成DSL查询语句
        SearchResponse<CtTrends> search = client.search(searchRequestBuilder.build(), CtTrends.class);
        System.out.println(search);
    }


    public static boolean checkIndexTypeId(EsIndexAndId t) {
        return t == null || t.getId() == null || t.getIndex() == null;
    }

}
