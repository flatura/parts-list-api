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

    /**
     * API получения всех комплектующих из БД
     *
     * @return List<Part> массив всех элементов БД
     */
    @GetMapping("/list/all")
    public List<Part> listAll() {
        return this.partRepository.findAll();
    }


    /**
     * API получения обязательных комплектующих из БД. Для внутреннего пользования
     *
     * @return List<Part> массив всех элементов БД
     */
    //@GetMapping("/list/required") // Ибо для внутреннего пользования
    private List<Part> listRequired() {
        return this.partRepository.findAll().stream().filter(p -> p.isNeed()).collect(Collectors.toList());
    }


    /*
    // Получение опциональных комплектующих
    @GetMapping("/list/optional")
    public List<Part> listOptional() {
        return this.partRepository.findAll().stream().filter(p -> !p.isNeed()).collect(Collectors.toList());
    }
    */

    /**
     * API GET - получение одного компелктующего из БД
     * @param unparsedId - ID комплектующего
     * @return null - если такого нет, PartViewModel, если такой есть
     */
    @GetMapping("/part/{unparsedId}")
    public PartViewModel getPartById(@PathVariable String unparsedId) {
        Integer id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (this.partRepository.existsById(id)) {
            PartViewModel result = ConvertHelper.convertToPartViewModel(this.partRepository.getOne(id));
            return ConvertHelper.convertToPartViewModel(this.partRepository.getOne(id));
        } else return null;
    }

    /**
     * API CREATE - создания нового комплектующего
     * @param partViewModel новая модель
     * @return HTTP-ответ об успехе/провале
     */
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public ResponseEntity<Void> createPart(@RequestBody PartViewModel partViewModel) {
        Part partToCreate = ConvertHelper.convertToPartEntity(partViewModel);
        this.partRepository.saveAndFlush(partToCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * API UPDATE
     * @param unparsedId ID-элемента
     * @param partViewModel Элемент
     * @return HTTP-ответ об успехе/провале
     */
    @RequestMapping(value = "/part/{unparsedId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> savePart(@PathVariable String unparsedId, @RequestBody PartViewModel partViewModel) {
        Integer id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
        }
        if (this.partRepository.existsById(id)) {
            if (partViewModel != null) {
                Part partToModify = ConvertHelper.convertToPartEntity(partViewModel);
                partToModify.setId(id);
                this.partRepository.saveAndFlush(partToModify);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *  API подсчета сколько компьютеров можно собрать
     *  @return int количество доступных сборок
     */
    @GetMapping("/info/assembliesavailable")
    public int getAvailableAssemblies() {
        int result = 0;
        try {
            result = listRequired()
                    .stream()
                    .min(Comparator.comparing(Part::getCount))
                    .orElseThrow(NoSuchElementException::new)
                    .getCount();
        } catch (NoSuchElementException e) {
        }
        return result;
    }

    /**
     * API DELETE удаления компелктующего
     * @param unparsedId ID элемента
     * @return HTTP-ответ об успехе/провале
     */

    @DeleteMapping("/part/delete/{unparsedId}")
    public ResponseEntity<Void> deletePart(@PathVariable String unparsedId) {
        int id = 0;
        try {
            id = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
        }
        if (id == 0 || this.partRepository.existsById(id)) {
            this.partRepository.deleteById(id);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}