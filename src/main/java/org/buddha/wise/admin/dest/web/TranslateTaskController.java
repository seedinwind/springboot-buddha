package org.buddha.wise.admin.dest.web;


import org.buddha.wise.JsonResult;
import org.buddha.wise.admin.dest.model.TranslateTask;
import org.buddha.wise.admin.dest.repo.TranslateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TranslateTaskController {

    @Autowired
    private TranslateRepository repository;

    @PostMapping(value="/admin/translate/gaoseng/task")
    public JsonResult<String> saveTranslateTask(@RequestBody  TranslateTask task) {
        repository.save(task);
        return new JsonResult<String>(0,"ok");
    }
}
