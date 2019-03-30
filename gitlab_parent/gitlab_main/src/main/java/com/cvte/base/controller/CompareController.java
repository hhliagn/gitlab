package com.cvte.base.controller;

import com.cvte.base.pojo.Version;
import com.cvte.base.service.compareService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compare")
public class CompareController {

    @Autowired
    private compareService compareService;


    @PostMapping(value = "/{repoName}")
    public Result enter(@PathVariable String repoName,
                        @RequestBody List<Version> versionList){

        Version oldVersion = versionList.get(0);
        Version newVersion = versionList.get(1);

        String content
                = compareService.compareTwoVersion(repoName,oldVersion,newVersion);

        return new Result(true, StatusCode.OK,"查询成功",content);
    }

}
