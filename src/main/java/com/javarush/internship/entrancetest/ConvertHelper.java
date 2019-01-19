package com.javarush.internship.entrancetest;

import com.javarush.internship.entrancetest.api.viewmodel.PartViewModel;
import com.javarush.internship.entrancetest.entity.Part;

/**
 * Класс содержит методы для конвертации Entity в ViewModel и обратно
 */

public class ConvertHelper {
    private ConvertHelper() {
    }

    /**
     * Конвертер формата
     *
     * @param entity - формат backend и БД
     * @return PartViewModel - формат HTTP
     */
    public static PartViewModel convertToPartViewModel(Part entity) {
        return new PartViewModel(
                String.valueOf(entity.getId()),
                entity.getName(),
                (entity.isNeed() ? "true" : "false"),
                String.valueOf(entity.getCount()));
    }

    /**
     * Конвертер формата для backend
     * @param viewModel - формат HTTP
     * @return Part - формат backend и БД
     */
    public static Part convertToPartEntity(PartViewModel viewModel) {
        return new Part(
                viewModel.getName(),
                viewModel.isRequired().equals("true"),
                Integer.parseInt(viewModel.getCount()));
    }
}
