package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PrizeDto {

    @ApiModelProperty(notes = "Must be omitted when adding a new entity. Required otherwise.")
    private Long id;

    @ApiModelProperty(required = true,
            notes = "The prize's category. For instance, 'SCRIPT' means 'best script prize'.",
            allowableValues = "DIRECTOR,ACTRESS,ACTOR,FILM,SCRIPT")
    @NotBlank
    private String category;

    @ApiModelProperty(notes = "The ID of the person that won the prize. Must be ommited if the prize's categoy is FILM or SCRIPT. Required otherwise.")
    @NotNull
    private Long idWinner;

    @ApiModelProperty(required = true, notes = "The ID of the person that won the prize.")
    @NotNull
    private Long idFilm;

    @ApiModelProperty(required = true, notes = "The ID of the award that the prize belongs to.")
    @NotNull
    private Long idAward;

    public static PrizeDto fromPrize(Prize prize) {
        PrizeDto prizeDto = new PrizeDto();
        prizeDto.setId(prize.getId());
        prizeDto.setCategory(prize.getCategory().name());

        Person winner = prize.getWinner();
        prizeDto.setIdWinner(winner == null ? null : winner.getId());
        Film film = prize.getFilm();
        prizeDto.setIdFilm(film == null ? null : film.getId());
        Award award = prize.getAward();
        prizeDto.setIdAward(award == null ? null : award.getId());

        return prizeDto;
    }

    public Prize toPrize(PersonService personService, FilmService filmService, AwardService awardService) throws PersistenceException, InvalidDtoException {
        Prize prize = new Prize();
        prize.setId(this.id);
        prize.setCategory(CategoryEnum.valueOf(this.category));
        try {
            prize.setWinner(personService.findById(getIdWinner()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idWinner", "not found");
        }
        try {
            prize.setFilm(filmService.findById(getIdFilm()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idFilm", "not found");
        }
        try {
            prize.setAward(awardService.findById(getIdAward()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idAward", "not found");
        }
        return prize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getIdWinner() {
        return idWinner;
    }

    public void setIdWinner(Long idWinner) {
        this.idWinner = idWinner;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public Long getIdAward() {
        return idAward;
    }

    public void setIdAward(Long idAward) {
        this.idAward = idAward;
    }

}
