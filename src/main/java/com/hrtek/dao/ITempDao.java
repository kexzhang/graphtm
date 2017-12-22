package com.hrtek.dao;

import com.hrtek.entity.Temp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITempDao extends JpaRepository<Temp,Integer> {

}
