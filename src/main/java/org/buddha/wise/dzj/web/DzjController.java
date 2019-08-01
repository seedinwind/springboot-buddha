package org.buddha.wise.dzj.web;

import org.buddha.wise.JsonResult;
import org.buddha.wise.PageResult;
import org.buddha.wise.dzj.model.Dzj;
import org.buddha.wise.dzj.model.DzjBu;
import org.buddha.wise.dzj.model.DzjJing;
import org.buddha.wise.dzj.repo.DzjRepository;
import org.buddha.wise.dzj.service.DzjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class DzjController {
    @Autowired
    private DzjService service;

    @RequestMapping("admin/dzj")
    public PageResult<List<Dzj>> getDzjByJingti(@RequestParam(value="jingti", defaultValue="") String jingti,@RequestParam(value="yizhe", defaultValue="") String yizhe,@RequestParam(value = "page", defaultValue = "0") String page) {
        Pageable pageable = PageRequest.of(Integer.valueOf(page), 10);
        Page<Dzj> coll=service.getDzjByJingti(jingti,yizhe,pageable);
        return new PageResult<List<Dzj>>(coll.getContent(),coll.getTotalPages()-1,pageable.getPageNumber());
    }


    @RequestMapping("admin/dzj/content")
    public JsonResult<List<DzjBu>>  getDzjContent(){
        return  new JsonResult<List<DzjBu>>(service.getContents());
    }

    @RequestMapping("admin/dzj/content/bu")
    public JsonResult<List<DzjJing>>  getDzjContentByBu(@RequestParam(value="buming", defaultValue="") String buming){
        return  new JsonResult<List<DzjJing>>(service.getContentsBu(buming));
    }
}
