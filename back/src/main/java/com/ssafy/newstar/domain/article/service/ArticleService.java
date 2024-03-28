package com.ssafy.newstar.domain.article.service;
import com.ssafy.newstar.domain.article.entity.Article;
import com.ssafy.newstar.domain.article.repository.ArticleRepository;
import com.ssafy.newstar.domain.member.entity.Member;
import com.ssafy.newstar.domain.member.repository.MemberRepository;
import com.ssafy.newstar.domain.record.entity.Record;
import com.ssafy.newstar.domain.record.repository.RecordRepository;
import com.ssafy.newstar.util.response.ErrorCode;
import com.ssafy.newstar.util.response.exception.GlobalException;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
  private final ArticleRepository articleRepository;
  private final MemberRepository memberRepository;
  private final RecordRepository recordRepository;
  public Record getArticles(Long articleId, Long memberId) {
    Article article = articleRepository.getReferenceById(articleId);
    Member member = memberRepository.getReferenceById(memberId);
    return recordRepository.findByArticleAndMember(article, member).orElseThrow(
        () -> new GlobalException(ErrorCode.ARTICLE_NOT_FOUND));

  }

  public Slice<Article> getArticlesByCategory(Pageable pageable, String bcategory, String scategory) {
    return articleRepository.getByCategory(pageable, bcategory, scategory);
  }
}
