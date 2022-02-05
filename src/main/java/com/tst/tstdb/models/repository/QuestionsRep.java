package com.tst.tstdb.models.repository;

import com.tst.tstdb.models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionsRep extends CrudRepository<Question, Integer>{
    List<Question> findAll();
    Question findById(int id);
}