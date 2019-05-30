package com.jinbang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jinbang.model.Paper;
import com.jinbang.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @PostMapping("/createEmptyPaper")
    public ResponseEntity<HashMap<String, Integer>> createEmptyPaper(HttpSession session, @RequestBody JSONObject request){
        HashMap<String, Integer> rslt = new HashMap<>();
        if(session.getAttribute("name") != null){
            int pid = paperService.createEmptyPaper(request);
            rslt.put("pid", pid);
            return new ResponseEntity<HashMap<String, Integer>>(rslt, HttpStatus.OK);
        } else {
            rslt.put("NotLogged", -1);
            return new ResponseEntity<HashMap<String, Integer>>(rslt, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buildPaper")
    public ResponseEntity<String> buildPaper(HttpSession session, @RequestBody JSONObject jsonParam){
        if(session.getAttribute("name")!=null){
            // 直接将json信息打印出来
            paperService.buildPaper(jsonParam);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("NotLogged", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/getAllPaperDetail")
    public ResponseEntity<JSONObject> getAllPaperDetail(HttpSession session){
        System.out.println("查看试卷");
        if(session.getAttribute("name") != null){
            JSONArray allPaperAndBuildpapers = paperService.getAllPaperDetail();
            JSONObject rep = new JSONObject();
            rep.put("allPaperDetail", allPaperAndBuildpapers);
            return new ResponseEntity<JSONObject>(rep, HttpStatus.OK);
        } else {
            JSONObject rep = new JSONObject();
            rep.put("Err", "NotLogged");
            return new ResponseEntity<JSONObject>(rep, HttpStatus.BAD_REQUEST);
        }
    }

}
