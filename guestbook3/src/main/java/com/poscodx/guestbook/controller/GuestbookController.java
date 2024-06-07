package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.service.GuestbookService;

import guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	/**@Autowired
	private GuestbookRepositoryWithRawJdbc guestbookRepository1;
	
	@Autowired
	private GuestbookRepositoryWithJdbcContexts guestbookRepository2; //GuestbookRepository
	
	@Autowired
	private GuestbookRepositoryWithJdbcTemplate guestbookRepository3;
	*/
	
	@Autowired	
	private GuestbookService gusetbookService;

	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestbookVo> list = gusetbookService.getContentsList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		gusetbookService.addContents(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "delete";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(GuestbookVo vo, @PathVariable("no") Long no) {
		gusetbookService.deleteContents(vo);
		return "redirect:/";
	}
}
