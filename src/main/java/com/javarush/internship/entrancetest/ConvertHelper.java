package com.javarush.internship.entrancetest;

import com.javarush.internship.entrancetest.api.viewmodel.PartViewModel;
import com.javarush.internship.entrancetest.entity.Part;

/**
 * Класс содержит методы для конвертации Entity в ViewModel и обратно
 */

public class ConvertHelper {
    private ConvertHelper() {
    }

    public static PartViewModel convertToPartViewModel(Part entity) {
        PartViewModel viewModel = new PartViewModel();

        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setRequired(entity.isRequired());
        viewModel.setCount(entity.getCount());

        return viewModel;
    }

    public static Part convertToPartEntity(PartViewModel viewModel) {
        return new Part(
                viewModel.getName(),
                viewModel.getRequired(),
                viewModel.getCount());
    }
}
