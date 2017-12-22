package com.hrtek.service.impl;

import com.hrtek.Repository.TempRepository;
import com.hrtek.dao.ITempDao;
import com.hrtek.entity.Temp;
import com.hrtek.service.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempServiceImpl  implements ITempService{
    @Autowired
    private ITempDao tempDao;
    @Autowired
    private TempRepository tempRepository;
    @Override
    public List queryTempAll() {

        return   tempDao.findAll();


    }

    public Iterable<Temp> queryTempAllByRepository(){
    Iterable<Temp> tempIterable =    tempRepository.findAll();
        return tempIterable;
    }

}
