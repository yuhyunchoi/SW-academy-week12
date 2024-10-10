package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.Inquiry;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {
    private final Map<Long, Inquiry> inquiryMap = new HashMap<>();
    private long nextId = 1L;

    @Override
    public List<Inquiry> getInquiries() {
        return inquiryMap.values().stream()
                .sorted(Comparator.comparing(Inquiry::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Inquiry getInquiry(long id) {
        return inquiryMap.get(id);
    }

    @Override
    public Inquiry addInquiry(Inquiry inquiry) {
        long id = nextId++;
        Inquiry inquiryInfo = new Inquiry(
                id,
                inquiry.getTitle(),
                inquiry.getCategory(),
                inquiry.getContent(),
                inquiry.getUserId(),
                LocalDateTime.now(),
                false);
        inquiryMap.put(inquiryInfo.getId(), inquiryInfo);
        return inquiryInfo;
    }

    @Override
    public List<Inquiry> getInquiriesByUserId(String userId){
        return inquiryMap.values().stream()
                .filter(inquiry -> inquiry.getUserId().equals(userId))
                .sorted(Comparator.comparing(Inquiry::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Inquiry updateInquiry(long id, Inquiry updatedInquiry) {
        if (inquiryMap.containsKey(id)) {
            Inquiry existingInquiry = inquiryMap.get(id);
            existingInquiry.setTitle(updatedInquiry.getTitle());
            existingInquiry.setCategory(updatedInquiry.getCategory());
            existingInquiry.setContent(updatedInquiry.getContent());
            // 추가 수정사항 반영 가능
            return existingInquiry;
        }
        return null;
    }

    @Override
    public boolean deleteInquiry(long id) {
        return inquiryMap.remove(id) != null;
    }

    @Override
    public Inquiry register(String title, String category, String content, String userId) {
        long id = nextId++;
        Inquiry inquiry = Inquiry.create(title, category, content, userId);
        inquiry.setId(id);

        inquiryMap.put(id, inquiry);
        return inquiry;
    }

}
