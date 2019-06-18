package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Persistence;
import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
public abstract class EntityServiceBase<TEntity, TId, TRepository extends JpaRepository<TEntity, TId>> {

    @Autowired
    protected TRepository repository;

    @Nullable
    public TEntity findById(TId id) throws PersistenceException, NotFoundException {
        if (id == null)
            throw new NotFoundException("Can't find entity with null ID");
        try {
            TEntity entity = repository.findById(id).orElse(null);
            if (entity == null)
                throw new NotFoundException("Couldn't find entity");
            return entity;
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public Collection<TEntity> findAll() throws PersistenceException {
        try {
            return repository.findAll();
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public boolean existsById(TId id) throws PersistenceException {
        try {
            return repository.existsById(id);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public <S extends TEntity> void delete(S item) throws PersistenceException {
        try {
            repository.delete(item);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public void deleteById(TId id) throws PersistenceException, NotFoundException {
        try {
            if (!repository.existsById(id))
                throw new NotFoundException("Couldn't find entity with ID " + id);
            repository.deleteById(id);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public <S extends TEntity> S save(S item) throws PersistenceException {
        try {
            return repository.save(item);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public <S extends TEntity> Collection<S> saveAll(Iterable<S> items) throws PersistenceException {
        try {
            return repository.saveAll(items);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public long countAll() throws PersistenceException {
        try {
            return repository.count();
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }
}
