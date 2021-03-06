package com.naver.helloworld.resort.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class ModernJavaBreak2Resort implements ResortService {
	private GuestRepository repository;
	public ModernJavaBreak2Resort(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();

		Stream<Guest> stream = all.stream();
		
		// filtering
		Predicate<Guest> filterFunc = g -> company.equals(g.getCompany());
		Stream<Guest> filtered = stream.filter(filterFunc);
		
		// sorting
		Comparator<Guest> sortFunc = Comparator.comparing(Guest::getGrade);
		Stream<Guest> sorted = filtered.sorted(sortFunc);

		// mapping
		Function<Guest, String> mapFunc = Guest::getName;
		Stream<String> mapped = sorted.map(mapFunc);
		Collector<String, ?, List<String>> collector = Collectors.toList();
		return mapped.collect(collector);
	}
}