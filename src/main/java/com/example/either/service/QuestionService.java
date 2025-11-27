package com.example.either.service;

import com.example.either.entity.Question;
import com.example.either.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;


    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }
}
