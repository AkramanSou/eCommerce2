package org.example.ecommerce.controller.admincontrollers

import org.example.ecommerce.model.Article
import org.example.ecommerce.repository.ArticleRepository
import org.example.ecommerce.repository.CategorieRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(
    val articleRepository: ArticleRepository,
    val categorieRepository: CategorieRepository
) {

    // READ - Liste des articles
    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("articles", articleRepository.findAll())
        model.addAttribute("pageTitle", "Admin - Articles")
        return "admin/articles/index"
    }

    // CREATE - Afficher le formulaire
    @GetMapping("/create")
    fun create(model: Model): String {
        model.addAttribute("article", Article())
        model.addAttribute("categories", categorieRepository.findAll())
        model.addAttribute("pageTitle", "Nouvel Article")
        return "admin/articles/create"
    }

    // CREATE - Traiter le formulaire
    @PostMapping("/create")
    fun store(
        @ModelAttribute article: Article,
        @RequestParam categorieId: Long
    ): String {
        val categorie = categorieRepository.findById(categorieId).orElseThrow()
        article.categorie = categorie
        articleRepository.save(article)
        return "redirect:/admin/articles"
    }

    // UPDATE - Afficher le formulaire
    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val article = articleRepository.findById(id).orElseThrow()
        model.addAttribute("article", article)
        model.addAttribute("categories", categorieRepository.findAll())
        model.addAttribute("pageTitle", "Modifier Article")
        return "admin/articles/edit"
    }

    // UPDATE - Traiter le formulaire
    @PostMapping("/edit/{id}")
    fun update(
        @PathVariable id: Long,
        @ModelAttribute article: Article,
        @RequestParam categorieId: Long
    ): String {
        val categorie = categorieRepository.findById(categorieId).orElseThrow()
        article.id = id
        article.categorie = categorie
        articleRepository.save(article)
        return "redirect:/admin/articles"
    }

    // DELETE
    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        articleRepository.deleteById(id)
        return "redirect:/admin/articles"
    }
}