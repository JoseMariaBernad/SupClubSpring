package com.supclub.repository;

import org.springframework.data.repository.CrudRepository;

import com.supclub.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{

}
