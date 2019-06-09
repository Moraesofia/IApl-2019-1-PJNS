package com.crossover.jns.JnsFilmes.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.crossover.jns.JnsFilmes.business.entity.Award;

public class AwardDto {

	@NotBlank
	private Long id;

	@NotBlank
    @Size(min = 2, max = 128)
	private String name;

	@NotBlank
	private Integer year;

	public static AwardDto fromAward(Award award) {
		AwardDto awardDto = new AwardDto();
		awardDto.setId(award.getId());
		awardDto.setName(award.getName());
		awardDto.setYear(award.getYear());

		return awardDto;
	}
	
	public Award toAward() {
		Award award = new Award();
		award.setId(this.id);
		award.setName(this.name);
		award.setYear(this.year);

        return award;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
