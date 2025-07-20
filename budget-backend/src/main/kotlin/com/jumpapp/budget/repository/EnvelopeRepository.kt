package com.jumpapp.budget.repository

import com.jumpapp.budget.model.Envelope
import org.springframework.data.jpa.repository.JpaRepository

interface EnvelopeRepository : JpaRepository<Envelope, Long> {
}