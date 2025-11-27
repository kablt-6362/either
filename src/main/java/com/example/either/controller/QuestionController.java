package com.example.either.controller;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.service.AnswerService;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;


    @GetMapping("")
    public String list(Model model){
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions",questions);
        return "list";
    }

    @GetMapping("/new")
    public String newlist(Model model){
        model.addAttribute("question",new Question());
        return "form";
    }

    @PostMapping("")
    public String create(@ModelAttribute Question question)
    {
        questionService.createQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         Model model){

        Question question = questionService.getById(id);
        List<Answer> answers = answerService.getAllAnswer(id);
        int countA = answerService.getAnswerTextA(id);
        int countB = answerService.getAnswerTextB(id);
        int total = countA + countB;
        double percentA =0;
        double percentB= 0;
        if(total != 0){
        percentA = ((double) countA /total)*100;
        percentA = (double)((int)(percentA*10))/10;
        percentB = ((double) countB /total)*100;
        percentB = (double) ((int)(percentB*10))/10;
        }

        model.addAttribute("percentA",percentA);
        model.addAttribute("percentB",percentB);
        model.addAttribute("countB",countB);
        model.addAttribute("countA",countA);
        model.addAttribute("answers",answers);
        model.addAttribute("question",question);
        model.addAttribute("answer",new Answer());
        return "detail";
    }

    @PostMapping("/{postId}/answers")
    public String createAnswer(@PathVariable Long postId, @ModelAttribute Answer answer,Model model){

        answerService.createAnswer(postId,answer);


        return "redirect:/questions/"+ postId;
    }



}
