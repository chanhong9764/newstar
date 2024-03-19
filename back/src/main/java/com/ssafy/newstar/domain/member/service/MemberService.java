package com.ssafy.newstar.domain.member.service;

import com.ssafy.newstar.domain.category.entity.Category;
import com.ssafy.newstar.domain.category.repository.CategoryRepository;
import com.ssafy.newstar.domain.member.dto.MemberRequest;
import com.ssafy.newstar.domain.member.entity.Member;
import com.ssafy.newstar.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;
  private final CategoryRepository categoryRepository;
  public Member getMember(Long memberId) {
    return memberRepository.findById(memberId).get();
  }

  public Member createMember(MemberRequest memberRequest) {
    String pw = UUID.randomUUID().toString();
    Member member = Member.createMember(pw);
    memberRepository.save(member);

    // 담겨있는 키워드를 뽑아와 저장한다 ! memberRequest;
    for (Integer tmp : memberRequest.getCategories()) {
      Category category = Category.createCategory(member, tmp);
      categoryRepository.save(category);
    }

    return member;
  }
}
