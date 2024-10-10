package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {
    List<Inquiry> getInquiries();
    Inquiry getInquiry(long id);
    Inquiry addInquiry(Inquiry inquiry);
    List<Inquiry> getInquiriesByUserId(String userId);
    Inquiry updateInquiry(long id, Inquiry inquiry);
    boolean deleteInquiry(long id);
    Inquiry register(String title, String category, String content, String userId);
}
