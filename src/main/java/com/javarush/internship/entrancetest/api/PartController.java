package com.javarush.internship.entrancetest.api;

import com.javarush.internship.entrancetest.Converter;
import com.javarush.internship.entrancetest.api.viewmodel.PartViewModel;
import com.javarush.internship.entrancetest.entity.Part;
import com.javarush.internship.entrancetest.repository.PartRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PartController {
    private PartRepository partRepository;
    private Converter converter;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
        this.converter = new Converter();
    }

    @GetMapping("/list/all")
    public List<Part> listAll() {
        return this.partRepository.findAll();
    }

    @GetMapping("/list/required")
    public List<Part> listRequired() {
        return this.partRepository.findAll().stream().filter(p -> p.isRequired()).collect(Collectors.toList());
    }

    @GetMapping("/list/optional")
    public List<Part> listOptional() {
        return this.partRepository.findAll().stream().filter(p -> !p.isRequired()).collect(Collectors.toList());
    }

    @GetMapping("part/get/{unparsedId}")
    public Part getPart(@PathVariable String unparsedId) {
        int id = Integer.getInteger(unparsedId);
        if (this.partRepository.existsById(id))
            return this.partRepository.getOne(id);
        else return null;
    }

    //TODO Дописать API сохранения нового или существующего комплектующего
    @PostMapping("/part/save")
    public Part savePart(@RequestBody PartViewModel partViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        Part newPartEntity = null;
        if (partViewModel != null) {
            newPartEntity = this.converter.convertToPartEntity(partViewModel);
            this.partRepository.saveAndFlush(newPartEntity);
        }
        return newPartEntity;
    }

    @DeleteMapping("/part/delete/{unparsedId}")
    public void deletePart(@PathVariable String unparsedId) {
        int id = Integer.getInteger(unparsedId);
        if (this.partRepository.existsById(id))
            this.partRepository.deleteById(id);
    }

    //Метод, считающий, сколько компьютеров можно собрать
    @GetMapping("/info/assembliesavailable")
    public int getAvailableAssemblies() {

        return listRequired()
                .stream()
                .min(Comparator.comparing(Part::getCount))
                .orElseThrow(NoSuchElementException::new)
                .getCount();
    }
}