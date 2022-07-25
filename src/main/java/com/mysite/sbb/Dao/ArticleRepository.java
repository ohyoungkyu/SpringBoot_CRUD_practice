package com.mysite.sbb.Dao;

import com.mysite.sbb.Domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
