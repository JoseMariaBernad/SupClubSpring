package com.supclub.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.supclub.entity.Reservation;
import com.supclub.service.ReservationService;

@Controller
@SessionAttributes("reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
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
	
	@RequestMapping(value = "/newReservation")
	public String crear(Map<String, Object> model) {
		Reservation reservation = new Reservation();
		model.put("reservation", reservation);
		model.put("title", "Nueva reserva");
		return "newReservation";
	}
	
	@RequestMapping(value = "/newReservation", method = RequestMethod.POST)
	public String newReservation(@Valid Reservation reservation, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("title", "Nueva reserva");
			return "newReservation";
		}
		
		if(reservation.getBoard() == null) {
			model.addAttribute("availableBoards", reservationService.findAvailableBoards(
											reservation.getStartDate(), reservation.getEndDate()));
			model.addAttribute("title", "Nueva reserva");
			return "newReservation";
		}
		
		reservationService.confirmReservation(reservation);

		status.setComplete();
		return "redirect:reservations";
	}
}
