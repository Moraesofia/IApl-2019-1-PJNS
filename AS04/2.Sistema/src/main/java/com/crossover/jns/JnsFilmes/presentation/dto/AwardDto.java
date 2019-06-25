package com.crossover.jns.JnsFilmes.presentation.dto;

import javax.validation.constraints.*;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import io.swagger.annotations.ApiModelProperty;

public class AwardDto {

    @ApiModelProperty(notes = "Must be omitted when adding a new entity. Required otherwise.")
    private Long id;

    @ApiModelProperty(required = true, notes = "Between 2 and 128 caracters")
    @NotBlank
    @Size(min = 2, max = 128)
    private String name;

    @ApiModelProperty(required = true)
    @NotNull
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
