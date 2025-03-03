package com.uestc.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uestc.param.ProductSearchParam;
import com.uestc.pojo.Product;
import com.uestc.search.service.SearchService;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 根据关键字和分页进行数据库查询
     *  1.判断关键字是否为null null 查询全部 不为null all 字段查询
     *  2.添加分页属性
     *  3.es查询
     *  4.结果处理
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();

        if (StringUtils.isEmpty(search)) {
            // null 不添加all关键字，查询全部即可
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        } else {
            // 不为null
            // 添加all的匹配
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));
        }

        //进行分页数据添加
        searchRequest.source().from((productSearchParam.getCurrentPage() - 1) * productSearchParam.getPageSize()); // 偏移量
        searchRequest.source().size(productSearchParam.getPageSize());
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }

        // 商品服务 R msg total 符合的数量 | data 商品集合
        SearchHits hits = searchResponse.getHits();
        // 查询符合的数量
        long value = hits.getTotalHits().value;

        SearchHit[] hitsHit = hits.getHits();
        List<Product> productList = new ArrayList<>();

        // Json 处理器
        ObjectMapper objectMapper = new ObjectMapper();
        for (SearchHit searchHit : hitsHit) {
            // 查询的内容数据！productDoc模型对应的json数据
            String sourceAsString = searchHit.getSourceAsString();
            Product product = null;
            try {
                //productDoc all -> product 如果没有all的属性，会报错！jackson提供忽略没有属性的注解
                // TODO: 修改product的实体类，添加忽略没有属性的注解！
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);
        }

        R ok = R.ok(null, productList, value);
        log.info("SearchServiceImpl.search业务结束，结果:{}", ok);
        return ok;
    }
}
