package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.CategoryRequest;
import ra.model.entity.Test1;
import ra.service.ICategoryService;

import javax.transaction.Transactional;

@Transactional
@Controller
@RequestMapping("/category")
//@RequestMapping("")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public String hello(Model model){
        model.addAttribute("list", categoryService.findAll());
        return "category-list";
    }

    @GetMapping("/openAdd")
    public String openAddForm(){
        return "add-category";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CategoryRequest categoryRequest){
        categoryService.save(categoryRequest);
        return "redirect:/category";
    }

    @GetMapping("/openEdit/{id}")
    public String openEditForm(Model model, @PathVariable Long id){
        model.addAttribute("cat",categoryService.findById(id));
        return "edit-category";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute CategoryRequest categoryRequest){
        categoryService.save(categoryRequest);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/category";
    }


}
