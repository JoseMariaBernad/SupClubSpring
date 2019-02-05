package com.supclub.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supclub.entity.Board;
import com.supclub.entity.Reservation;
import com.supclub.model.ValidationResult;
import com.supclub.repository.BoardRepository;
import com.supclub.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	private Date currentTime;
	
	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	
	public ValidationResult confirmReservation(Reservation newReservation) {
        Iterable<Reservation> reservations = reservationRepository.findAll(); 
        		//repository.FindAllReservationsAsync();
        ValidationResult result = newReservation.confirm(reservations, currentTime);

        if (result.isValid())
        {
            reservationRepository.save(newReservation);
        }

        return result;
	}

	public Iterable<Reservation> findAll() {
		//TODO: find only reservations for current user or all if user is administrator 
		return reservationRepository.findAll();
	}

	public Iterable<Board> findAvailableBoards(Date startDate, Date endDate) {
		// TODO Actually find available boards, not all boards.
		
		return boardRepository.findAll();
	}

	public Reservation findReservationById(Long id) {
		
		return reservationRepository.findById(id).orElse(null);
	}

	public ValidationResult cancelReservation(Reservation reservation) {
		// TODO Validate reservation prior to canceling
		reservationRepository.delete(reservation);
		return null;
	}
}
