package org.buddha.wise.dzj.service;



import org.buddha.wise.dzj.model.Dzj;
import org.buddha.wise.dzj.model.DzjBu;
import org.buddha.wise.dzj.model.DzjJing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongSupplier;

@Service
public class DzjService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<Dzj> getDzjByJingti(String jingti,String yizhe, Pageable pageable){

        Query query=new Query(Criteria.where("jingti").is(jingti).and("yizhe").is(yizhe));
        query.with( Sort.by(Sort.Direction.ASC,"juanhao","pinhao"));
        query.with(pageable);
        long count= mongoTemplate.count(query,Dzj.class,"qldzj");
        List<Dzj> list=mongoTemplate.find(query,Dzj.class,"qldzj");

       return PageableExecutionUtils.getPage(list, pageable, ()->count);
    }

    public List<DzjBu>  getContents(){
         List<AggregationOperation> opList=new ArrayList<AggregationOperation>();
         opList.add(Aggregation.group("buming").first("buming").as("title").count().as("count"));

         opList.add(Aggregation.project("title","count"));
         Aggregation aggregation=Aggregation.newAggregation(opList);
         return mongoTemplate.aggregate(aggregation, "qldzj", DzjBu.class).getMappedResults();
    }

    public List<DzjJing>  getContentsBu(String buming){
        List<AggregationOperation> opList=new ArrayList<AggregationOperation>();

        opList.add(Aggregation.match(Criteria.where("buming").is(buming)));
        opList.add(Aggregation.group("jingti","yizhe").first("jingti").as("title").count().as("count").first("yizhe").as("yizhe"));

        opList.add(Aggregation.project("title","count","yizhe"));
        Aggregation aggregation=Aggregation.newAggregation(opList);
        return mongoTemplate.aggregate(aggregation, "qldzj", DzjJing.class).getMappedResults();
    }
}
