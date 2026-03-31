package org.example.ecommerce.controller.admincontrollers

import org.example.ecommerce.model.Categorie
import org.example.ecommerce.repository.CategorieRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/categories")
class AdminCategorieController(
    val categorieRepository: CategorieRepository
) {

    // READ - Liste des catégories
    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("categories", categorieRepository.findAll())
        model.addAttribute("pageTitle", "Admin - Catégories")
        return "admin/categories/index"
    }

    // CREATE - Afficher le formulaire
    @GetMapping("/create")
    fun create(model: Model): String {
        model.addAttribute("categorie", Categorie())
        model.addAttribute("pageTitle", "Nouvelle Catégorie")
        return "admin/categories/create"
    }

    // CREATE - Traiter le formulaire
    @PostMapping("/create")
    fun store(@ModelAttribute categorie: Categorie): String {
        categorieRepository.save(categorie)
        return "redirect:/admin/categories"
    }

    // UPDATE - Afficher le formulaire
    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val categorie = categorieRepository.findById(id).orElseThrow()
        model.addAttribute("categorie", categorie)
        model.addAttribute("pageTitle", "Modifier Catégorie")
        return "admin/categories/edit"
    }

    // UPDATE - Traiter le formulaire
    @PostMapping("/edit/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute categorie: Categorie): String {
        categorie.id = id
        categorieRepository.save(categorie)
        return "redirect:/admin/categories"
    }

    // DELETE
    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        categorieRepository.deleteById(id)
        return "redirect:/admin/categories"
    }
}