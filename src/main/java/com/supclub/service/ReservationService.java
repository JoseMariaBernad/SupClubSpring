package com.supclub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supclub.entity.Reservation;
import com.supclub.model.ValidationResult;
import com.supclub.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
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
}
