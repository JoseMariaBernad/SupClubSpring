package com.supclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.supclub.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "redirect:/reservations";
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public String reservations(Model model) {
		model.addAttribute("title", "Reservas");
		model.addAttribute("reservations", reservationService.findAll());
		return "reservations";
	}
}
