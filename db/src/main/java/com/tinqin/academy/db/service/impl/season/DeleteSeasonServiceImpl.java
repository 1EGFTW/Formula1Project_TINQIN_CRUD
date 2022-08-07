package com.tinqin.academy.db.service.impl.season;

import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("season")
public class DeleteSeasonServiceImpl implements DeleteService {
    private final SeasonRepository seasonRepository;

    public DeleteSeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public void delete(Long id) {
        seasonRepository.deleteById(id);
    }
}
