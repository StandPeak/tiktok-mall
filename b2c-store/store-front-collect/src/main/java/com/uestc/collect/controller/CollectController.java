package com.uestc.collect.controller;

import com.uestc.collect.service.CollectService;
import com.uestc.pojo.Collect;
import com.uestc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("collect")
public class CollectController {
    @Autowired
    private CollectService collectService;


    @PostMapping("save")
    public R save(@RequestBody Collect collect) {

        return collectService.save(collect);
    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect) {

        return collectService.list(collect.getUserId());
    }

    @PostMapping("remove")
    public R remove(@RequestBody Collect collect) {

        return collectService.remove(collect);
    }
}
