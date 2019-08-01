package org.buddha.wise.admin.source.web;

import org.buddha.wise.JsonResult;
import org.buddha.wise.admin.source.model.PassageSource;
import org.buddha.wise.admin.source.repo.PassageSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PassageSourceController {

    @Autowired
    private PassageSourceRepository repository;

    @RequestMapping("admin/translate/gaoseng")
    public JsonResult<List<PassageSource>> getPassageSourceList() {
        return new JsonResult<List<PassageSource>>(repository.findAll());
    }
}
