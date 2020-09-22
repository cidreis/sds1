package com.devsuperior.resources;

import java.time.Instant;

import com.devsuperior.dto.RecordDTO;
import com.devsuperior.dto.RecordInsertDTO;
import com.devsuperior.services.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/records")
public class RecordResource {
	
	@Autowired
	private RecordService service;

	@PostMapping
	public ResponseEntity<RecordDTO> insert(@RequestBody final RecordInsertDTO dto) {
		final RecordDTO newDTO = service.insert(dto);
		return ResponseEntity.ok().body(newDTO);

	}

	@GetMapping
	public ResponseEntity<Page<RecordDTO>> findAll(@RequestParam(value = "min", defaultValue = "") final String min,
			@RequestParam(value = "max", defaultValue = "") final String max,
			@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") final String direction) {

		final Instant minDate = ("".equals(min)) ? null : Instant.parse(min);
		final Instant maxDate = ("".equals(max)) ? null : Instant.parse(max);

		if (linesPerPage == 0) {
			linesPerPage = Integer.MAX_VALUE;
		}

		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		final Page<RecordDTO> list = service.findByMoments(minDate, maxDate, pageRequest);
		return ResponseEntity.ok().body(list);
	}
}
