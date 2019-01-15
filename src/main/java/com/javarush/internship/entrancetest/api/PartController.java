package com.javarush.internship.entrancetest.api;

import com.javarush.internship.entrancetest.ConvertHelper;
import com.javarush.internship.entrancetest.api.viewmodel.PartViewModel;
import com.javarush.internship.entrancetest.entity.Part;
import com.javarush.internship.entrancetest.repository.PartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Получение всех комплектующих
    @GetMapping("/list/all")
    public List<Part> listAll() {
        return this.partRepository.findAll();
    }

    // Получение обязательных комплектующих
    @GetMapping("/list/required")
    public List<Part> listRequired() {
        return this.partRepository.findAll().stream().filter(p -> p.isRequired()).collect(Collectors.toList());
    }

    // Получение опциональных комплектующих
    @GetMapping("/list/optional")
    public List<Part> listOptional() {
        return this.partRepository.findAll().stream().filter(p -> !p.isRequired()).collect(Collectors.toList());
    }

    //API получения одного комплектующего
    @GetMapping("/part/{unparsedId}")
    public PartViewModel getPartById(@PathVariable String unparsedId) {
        System.out.println("Getting Part with id = " + unparsedId);
        Integer id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("id = " + id);
        if (this.partRepository.existsById(id))
            return ConvertHelper.convertToPartViewModel(this.partRepository.getOne(id));
        else return null;
    }

    //Создание нового комплектующего
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public ResponseEntity<Void> createPart(@RequestBody PartViewModel partViewModel) {
        System.out.println("Creating Part " + partViewModel.getName() + " / required=" + partViewModel.getRequired() + " / count=" + partViewModel.getCount());

        Part partToCreate = ConvertHelper.convertToPartEntity(partViewModel);
        this.partRepository.saveAndFlush(partToCreate);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    //Сохранение существующего комплектующего
    @RequestMapping(value = "/part/{unparsedId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> savePart(@PathVariable String unparsedId, @RequestBody PartViewModel partViewModel) {
        Integer id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга id");
            e.printStackTrace();
        }
        System.out.println("Updating Part with id = " + unparsedId);
        if (this.partRepository.existsById(id)) {
            if (partViewModel != null) {
                Part partToModify = ConvertHelper.convertToPartEntity(partViewModel);
                partToModify.setId(id);
                this.partRepository.saveAndFlush(partToModify);
            }
        } else {
            System.out.println("Part with id = " + unparsedId + " not found!");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
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

    //API удаления компелктующего под номером id
    @DeleteMapping("/part/delete/{unparsedId}")
    public ResponseEntity<Void> deletePart(@PathVariable String unparsedId) {
        System.out.println("Удаление комплектующего с id = -" + unparsedId + "-");
        Integer id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга ID");
            e.printStackTrace();
        }
        System.out.print("Поиск записи с id=" + id);
        if (this.partRepository.existsById(id)) {
            System.out.print("...найдена, удаляем...");
            this.partRepository.deleteById(id);
        } else return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}