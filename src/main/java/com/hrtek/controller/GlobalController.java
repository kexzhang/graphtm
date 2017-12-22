package com.hrtek.controller;

import com.hankcs.hanlp.seg.common.Term;
import com.hrtek.service.IGlobalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/global")
@Api(value = "文本分析接口",tags = {"文本分析API"})
public class GlobalController {
    @Autowired
    private IGlobalService globalService;
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView  mav = new ModelAndView("global/index");
        return mav;
    }
    @ApiOperation(value = "文本分词",notes = "根据输入内容进行分析")
    @ApiImplicitParam(name="terms",value = "文本内容",required = true,dataType ="String" )
   @PostMapping("/analyzer")
    public List<Term> Analyzer( @RequestBody String terms){
       return  globalService.termlist(terms);
    }
    @ApiOperation(value = "命名实体识别",notes = "根据输入内容进行分析")
    @ApiImplicitParam(name="terms",value = "文本内容",required = true,dataType ="String" )
    @PostMapping("/nlpTokenizer")
    public List<Term> nlpTokenizer( @RequestBody String terms){
        return  globalService.nlpTokenizer(terms);
    }


    @PostMapping("/extractKeyword")
    @ApiOperation(value = "关键词提取",notes = "根据输入内容进行分析")
    @ApiImplicitParam(name="terms",value = "文本内容",required = true,dataType ="String" )
    public List<String> extractKeyword(@RequestBody String terms){
        return  globalService.extractKeyword(terms);
    }

    @PostMapping("/extractSummary")
    @ApiOperation(value = "文章摘要",notes = "根据输入内容进行分析")
    @ApiImplicitParam(name="terms",value = "文本内容",required = true,dataType ="String" )
    public List<String> extractSummary(@RequestBody String terms){
        return  globalService.extractSummary(terms);
    }

    @PostMapping("/extractPhrase")
    @ApiOperation(value = "短语提取",notes = "根据输入内容进行分析")
    @ApiImplicitParam(name="terms",value = "文本内容",required = true,dataType ="String" )
    public List<String> extractPhrase(@RequestBody String terms){
        return  globalService.extractPhrase(terms);
    }
}
