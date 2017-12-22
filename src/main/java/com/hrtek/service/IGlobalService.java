package com.hrtek.service;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public interface IGlobalService {
    List<Term> termlist (String terms);

    List<Term> nlpTokenizer(String terms);

    List<String> extractKeyword(String terms);

    List<String> extractSummary(String terms);

    List<String> extractPhrase(String terms);
}
