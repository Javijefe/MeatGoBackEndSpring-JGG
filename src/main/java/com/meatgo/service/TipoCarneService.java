package com.meatgo.service;

import com.meatgo.dto.TipoCarneDTO;
import com.meatgo.model.TipoCarne;
import com.meatgo.repository.TipoCarneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoCarneService {

    private final TipoCarneRepository tipoCarneRepository;

    public TipoCarneService(TipoCarneRepository tipoCarneRepository) {
        this.tipoCarneRepository = tipoCarneRepository;
    }

    public List<TipoCarneDTO> obtenerTodosDTO() {
        List<TipoCarne> tiposCarne = tipoCarneRepository.findAll();
        List<TipoCarneDTO> dtoList = new ArrayList<>();

        for (TipoCarne tipo : tiposCarne) {
            TipoCarneDTO dto = new TipoCarneDTO();
            dto.setIdTiposCarne(tipo.getId());
            dto.setNombre(tipo.getNombre());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
