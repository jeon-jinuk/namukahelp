package com.routine.domain.b_circle.controller;

import com.routine.domain.b_circle.model.Notice;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.repository.NoticeRepository;
import com.routine.domain.b_circle.repository.CircleRepository;
import com.routine.security.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/circle")
public class CircleNoticeController {

    private final NoticeRepository noticeRepository;
    private final CircleRepository circleRepository;


    @GetMapping("/{circleId}/noticeList")
    public String getNotices(@PathVariable Long circleId, Model model) {
        List<Notice> notices = noticeRepository.findByCircleId(circleId);
        model.addAttribute("notices", notices);
        model.addAttribute("circleId", circleId);
        return "notice/noticeList";  // templates/notice/noticeList.html
    }
    @GetMapping("/circle/{circleId}/noticeList")
    public String getNoticeList(@PathVariable Long circleId, Model model) {
        List<Notice> notices = noticeRepository.findByCircleId(circleId);
        model.addAttribute("notices", notices);
        model.addAttribute("circleId", circleId);
        return "notice/noticeList";
    }





    @GetMapping("/{circleId}/notices/new")
    public String showCreateNoticeForm(@PathVariable Long circleId, Model model) {
        model.addAttribute("circleId", circleId);
        return "notice/noticeCreate";
    }


    @PostMapping("/{circleId}/notices")
    public String createNotice(@PathVariable Long circleId,
                               @RequestParam String title,
                               @RequestParam String content,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long memberId = principalDetails.getMember().getId();


        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid circle id"));


        Notice notice = new Notice(circle, title, content, memberId);


        noticeRepository.save(notice);


        return "redirect:/circle/" + circleId + "/notices";
    }


    // 공지사항 수정 페이지로 이동
    @GetMapping("/{circleId}/notices/{noticeId}/edit")
    public String showEditNoticeForm(@PathVariable Long circleId, @PathVariable Long noticeId, Model model) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notice id"));
        model.addAttribute("notice", notice);
        model.addAttribute("circleId", circleId);
        return "notice/noticeEdit"; // noticeEdit.html
    }

    // 공지사항 수정 처리
    @PostMapping("/{circleId}/notices/{noticeId}")
    public String updateNotice(@PathVariable Long circleId, @PathVariable Long noticeId,
                               @RequestParam String title, @RequestParam String content) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notice id"));
        notice.setTitle(title);
        notice.setContent(content);
        noticeRepository.save(notice);

        return "redirect:/circle/" + circleId + "/notices"; // 수정 후 목록으로 리디렉션
    }
    // 공지사항 삭제
    @PostMapping("/{circleId}/notices/{noticeId}/delete")
    public String deleteNotice(@PathVariable Long circleId, @PathVariable Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notice id"));
        noticeRepository.delete(notice);

        return "redirect:/circle/" + circleId + "/notices"; // 삭제 후 목록으로 리디렉션
    }
    @GetMapping("/{circleId}/notices/{noticeId}")
    public String getNoticeDetail(@PathVariable Long circleId, @PathVariable Long noticeId, Model model) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notice id"));
        model.addAttribute("notice", notice);
        model.addAttribute("circleId", circleId);
        return "notice/noticeDetail";
    }
    @GetMapping("/{circleId}/notices")
    public String redirectToNoticeList(@PathVariable Long circleId) {
        return "redirect:/circle/" + circleId + "/noticeList";
    }




}

