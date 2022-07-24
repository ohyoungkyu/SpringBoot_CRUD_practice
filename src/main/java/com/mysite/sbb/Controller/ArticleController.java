package com.mysite.sbb.Controller;

import com.mysite.sbb.Domain.Article;
import com.mysite.sbb.Service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @RequestMapping("/create")
    @ResponseBody
    public String articleCreate(String title, String body) {
        Article article = articleService.create(title, body);

        return "%d번 게시물 생성이 완료되었습니다.".formatted(article.getId());
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> articleList() {
        List<Article> article = articleService.getList();

        return article;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Article getArticle(Long id) {
        Article article = articleService.findId(id);

        return article;
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public String doModify(Long id, String title, String body) {
        Article article = articleService.findModify(id,title,body);

        return "%d번 게시물이 수정 되었습니다.".formatted(id);
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Long id) {
        Article article = articleService.doDelete(id);

        return "%d번 게시물이 삭제 되었습니다.".formatted(id);
    }

}
