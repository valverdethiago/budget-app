package com.jumpapp.budget.controller

import com.jumpapp.budget.dto.AmountDto
import com.jumpapp.budget.model.Envelope
import com.jumpapp.budget.repository.EnvelopeRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@CrossOrigin(origins = ["http://localhost:5173"])
@RestController
@RequestMapping("/api/envelopes")
class EnvelopeController(
    val envelopeRepository: EnvelopeRepository
) {
    private val logger = LoggerFactory.getLogger(EnvelopeController::class.java)
    private var income: BigDecimal = BigDecimal.ZERO


    @GetMapping
    fun listEnvelopes(): List<Envelope> = envelopeRepository.findAll()
    @GetMapping("/income")
    fun getIncome(): AmountDto = AmountDto(income)
    @PostMapping("/income")
    fun addIncome(@RequestBody dto: AmountDto) {
        logger.info("Received request to add income: $dto.value")
        income = income.add(dto.value)
        logger.info("Updated income: $income")
    }
    @PostMapping("/{id}/allocate")
    fun allocateIncome(
        @PathVariable id: Long,
        @RequestBody amount: BigDecimal
    ) {
        val envelope = envelopeRepository.findById(id).orElseThrow()
        if (income >= amount) {
            income = income.subtract(amount)
            val updated = envelope.copy(balance = envelope.balance.add(amount))
            envelopeRepository.save(updated)
        }
    }

    @PostMapping("/{id}/spend")
    fun spendFromEnvelope(
        @PathVariable id: Long,
        @RequestBody amount: BigDecimal
    ) {
        val envelope = envelopeRepository.findById(id).orElseThrow()
        if (envelope.balance >= amount) {
            val updated = envelope.copy(balance = envelope.balance.subtract(amount))
            envelopeRepository.save(updated)
        }
    }

    @PostMapping
    fun createEnvelope(@RequestBody envelope: Envelope): Envelope {
        require(envelope.id == null)
        return envelopeRepository.save(envelope)
    }





}