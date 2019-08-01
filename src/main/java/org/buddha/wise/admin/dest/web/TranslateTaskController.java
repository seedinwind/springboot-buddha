package org.buddha.wise.admin.dest.web;


import org.buddha.wise.JsonResult;
import org.buddha.wise.admin.dest.model.TranslateTask;
import org.buddha.wise.admin.dest.repo.TranslateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TranslateTaskController {

    @Autowired
    private TranslateRepository repository;

    @PostMapping(value="/admin/translate/gaoseng/task",produces="application/json;charset=UTF-8")
    public JsonResult<String> saveTranslateTask(@RequestBody List<TranslateTask> tasks) {
        repository.saveAll(tasks);
        return new JsonResult<String>(0,"ok");
    }
}
