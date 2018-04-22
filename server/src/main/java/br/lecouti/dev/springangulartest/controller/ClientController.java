package br.lecouti.dev.springangulartest.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.lecouti.dev.springangulartest.dao.ClientRepository;
import br.lecouti.dev.springangulartest.dto.LoanSimulation;
import br.lecouti.dev.springangulartest.model.Client;
import br.lecouti.dev.springangulartest.model.RiskLevel;

/**
 * Controlle for Client API
 * 
 * @author Leandro Coutinho
 *
 */
@RestController
@RequestMapping("/api/client")
public class ClientController {

	private static final DecimalFormat DECIMAL_FORMATTER;
	static {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
		DECIMAL_FORMATTER = (DecimalFormat) nf;
		DECIMAL_FORMATTER.applyPattern(".##");
	}

	@Autowired
	private ClientRepository repository;

	@GetMapping("/list")
	public Collection<Client> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public Client getById(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow(() -> getClientNotFoundException(id));
	}

	@PostMapping("")
	public Client create(@Valid @RequestBody Client client) {
		// Since the app is only for testing purposes and I'm going to deploy in Heroku
		// I'm adding a limit of 10 maximum entries in the the database
		List<Client> allClients = new ArrayList<>(getAll());
		if (allClients.size() == 10) {
			return allClients.get(0);
		}

		client.setRisk(calculateRisk(client)); // Recalculate risk because sometimes user saves before the callback for /calculateRisk
		return repository.save(client);
	}

	@PutMapping("/{id}")
	public Client update(@PathVariable("id") Long clientId, @Valid @RequestBody Client clientDetails) {
		Client client = repository.findById(clientId).orElseThrow(() -> getClientNotFoundException(clientId));

		client.setName(clientDetails.getName());
		client.setIncome(clientDetails.getIncome());
		client.setRisk(calculateRisk(client)); // Recalculate risk because sometimes user saves before the callback for /calculateRisk
		client.setAddress(clientDetails.getAddress());

		return repository.save(client);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Client client = repository.findById(id).orElseThrow(() -> getClientNotFoundException(id));
		repository.delete(client);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/calculate-risk")
	private RiskLevel calculateRisk(@RequestBody Client client) {
		Double income = Double.valueOf(client.getIncome());
		if (income <= 2000) {
			return RiskLevel.C;
		} else if (income > 2000 && income <= 8000) {
			return RiskLevel.B;
		} else {
			// income > 8000
			return RiskLevel.A;
		}
	}

	@PostMapping("/simulate-loan")
	public LoanSimulation simulateLoan(@RequestBody LoanSimulation loanDetails) {
		Client client = getById(loanDetails.getClientId());
		Double total = loanDetails.getLoanValue();

		switch (client.getRisk()) {
		case A:
			total += total * 0.019 * loanDetails.getNumMonths();
			break;
		case B:
			total += total * 0.05 * loanDetails.getNumMonths();
			break;
		case C:
			total += total * 0.1 * loanDetails.getNumMonths();
			break;
		}

		loanDetails.setTotal(DECIMAL_FORMATTER.format(total));
		loanDetails.setPerMonth(DECIMAL_FORMATTER.format(total / loanDetails.getNumMonths()));
		return loanDetails;
	}

	private ResourceNotFoundException getClientNotFoundException(Long clientId) {
		return new ResourceNotFoundException(String.format("Client not found with id: '%s'", clientId));
	}
}