package com.hrtek.service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hrtek.service.IGlobalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalServiceImpl implements  IGlobalService {
    @Override
    public List<Term> termlist(String terms) {
        return HanLP.segment(terms);
    }

    @Override
    public List<Term> nlpTokenizer(String terms) {
        return NLPTokenizer.segment(terms);
    }

    @Override
    public List<String> extractKeyword(String terms) {
        return HanLP.extractKeyword(terms,5);
    }

    @Override
    public List<String> extractSummary(String terms) {

        return HanLP.extractSummary(terms,5);


    }

    @Override
    public List<String> extractPhrase(String terms) {
        return HanLP.extractPhrase(terms,5);

    }
}
