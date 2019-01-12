package com.javarush.internship.entrancetest.api;

import com.javarush.internship.entrancetest.entity.Part;
import com.javarush.internship.entrancetest.repository.PartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PartController {
    private PartRepository partRepository;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
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

    //TODO Дописать API создания новой entity
    @PostMapping("/part/new")
    public void createPart(@RequestBody Part newPart) {
        if (newPart != null) {
            this.partRepository.saveAndFlush(newPart);
        }
    }

    @GetMapping("part/get/{unparsedId}")
    public Part getPart(@PathVariable String unparsedId) {
        int id = Integer.getInteger(unparsedId);
        if (this.partRepository.existsById(id))
            return this.partRepository.getOne(id);
        else return null;
    }

    //TODO Дописать API редактирования существующего комплектующего
    @PostMapping("/part/save")
    public void setPart(@RequestBody Part newPart) {
        if (newPart != null) {
            if (this.partRepository.existsById(newPart.getId())) {
                this.partRepository.saveAndFlush(newPart);
            }
        }
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