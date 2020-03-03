package bg.softuni.errorhandling.web.controllers;

import bg.softuni.errorhandling.service.models.CloudFileServiceModel;
import bg.softuni.errorhandling.service.services.CloudService;
import bg.softuni.errorhandling.web.models.CloudFileViewModel;
import bg.softuni.errorhandling.web.models.CreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreateController {
    private final CloudService cloudService;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateController(CloudService cloudService, ModelMapper modelMapper) {
        this.cloudService = cloudService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView getCreate(ModelAndView modelAndView) {
        modelAndView.setViewName("create");
        List<CloudFileViewModel> files = this.cloudService.getAll()
                .stream()
                .map(f -> this.modelMapper.map(f, CloudFileViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("files", files);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute CreateModel createModel) throws IOException {

        boolean isUploaded = this.cloudService.upload(this.modelMapper.map(createModel, CloudFileServiceModel.class));

        if (isUploaded) {
            return new ModelAndView("redirect:/create");
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @GetMapping("/create/d/{publicId}")
    public ModelAndView deleteFile(@PathVariable String publicId) throws IOException {
        this.cloudService.delete(publicId);
        return new ModelAndView("redirect:/create");
    }
}
