package com.mysite.sbb.Service;

import com.mysite.sbb.Dao.ArticleRepository;
import com.mysite.sbb.Domain.Article;
import com.mysite.sbb.Util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articlerepository;

    public Article create(String title, String body) {
        if (Util.empty(title)) {
            System.out.println("제목을 입력해주세요.");
        }
        if (Util.empty(body)) {
            System.out.println("내용을 입력해주세요.");
        }

        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setUpdateDate(LocalDateTime.now());
        article.setRegDate(LocalDateTime.now());
        article.setUserId(1L);

        articlerepository.save(article);

        return article;
    }

    public List<Article> getList() {

        return articlerepository.findAll();
    }

    public Article findId(Long id) {
        Article article = articlerepository.findById(id).get();

        return article;
    }

    public Article findModify(Long id, String title, String body) {
        if(id == null) {
            System.out.println("id를 입력해주세요.");
        }
        if(Util.empty(title)) {
            System.out.println("제목을 입력해주세요.");
        }
        if(Util.empty(body)) {
            System.out.println("내용을 입력해주세요.");
        }

        Article article = articlerepository.findById(id).get();

        article.setBody(body);
        article.setTitle(title);

        articlerepository.save(article);

        return article;
    }

    public Article doDelete(Long id) {
        if(!articlerepository.existsById(id)) {
            System.out.println("게시물은 삭제되었거나 이미 존재하지 않습니다.");
        }

        Article article = articlerepository.findById(id).get();

        articlerepository.delete(article);

        return article;
    }
}
