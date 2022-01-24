package com.newtory.calendar.resource.domain;

import lombok.Getter;

@Getter
public class Member {
	private Long id;

	public Member(Long id) {
		this.id = id;
	}
}
