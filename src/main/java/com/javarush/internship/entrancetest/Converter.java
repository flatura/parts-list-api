package com.javarush.internship.entrancetest;

import com.javarush.internship.entrancetest.api.viewmodel.PartViewModel;
import com.javarush.internship.entrancetest.entity.Part;

/**
 * Класс содержит методы для конвертации Entity в ViewModel и обратно
 */

public class Converter {
    public Converter() {
    }

    public PartViewModel convertToPartViewModel(Part entity) {
        PartViewModel viewModel = new PartViewModel();

        viewModel.setId(String.valueOf(entity.getId()));
        viewModel.setName(entity.getName());
        viewModel.setRequired(entity.isRequired() ? "true" : "false");
        viewModel.setCount(String.valueOf(entity.getCount()));

        return viewModel;
    }

    public Part convertToPartEntity(PartViewModel viewModel) {
        return new Part(
                Integer.parseInt(viewModel.getId()),
                viewModel.getName(),
                viewModel.getRequired().equals("true"),
                Integer.parseInt(viewModel.getCount()));
    }
}
