package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.ArticleRepository;
import com.mysite.sbb.Domain.Article;
import com.mysite.sbb.Util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/usr/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        List<Article> article = articleRepository.findAll();

        return article;
    }

    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if(Util.empty(title)) {
            return "제목을 입력해주세요.";
        }
        if(Util.empty(body)) {
            return "내용을 입력해주세요.";
        }

        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setUserId(1L);

        articleRepository.save(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showDetail(Long id) {
        Article article = articleRepository.findById(id).get();

        return article;
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public String doModify(Long id, String title, String body) {
        if(id == null) {
            return "게시물 번호를 입력해주세요.";
        }
        if(Util.empty(title)) {
            return "제목을 입력해주세요.";
        }
        if(Util.empty(body)) {
            return "내용을 입력해주세요.";
        }
        Article article = articleRepository.findById(id).get();

        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);

        return "%d번 게시물이 수정되었습니다.".formatted(id);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String doDelete(Long id) {
        if(!articleRepository.existsById(id)){
            return "존재하지 않는 게시물입니다.";
        }

        Article article = articleRepository.findById(id).get();
        articleRepository.delete(article);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }
}

