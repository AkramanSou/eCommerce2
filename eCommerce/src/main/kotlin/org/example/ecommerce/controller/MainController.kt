package org.example.ecommerce.controller

import org.example.ecommerce.model.Categorie
import org.example.ecommerce.repository.ArticleRepository
import org.example.ecommerce.repository.CategorieRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable

@Controller
class MainController(
    val categorieRepository: CategorieRepository,
    val articleRepository: ArticleRepository
) {

    @ModelAttribute("toutesLesCategories")
    fun toutesLesCategories(): List<Categorie> {
        return categorieRepository.findAll()
    }

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("activePage", "accueil")
        model.addAttribute("pageTitle", "Accueil")
        model.addAttribute("categories", categorieRepository.findAll())
        model.addAttribute("articles", articleRepository.findAll())
        return "index"
    }

    @GetMapping("/boutique")
    fun boutique(model: Model): String {
        model.addAttribute("activePage", "boutique")
        model.addAttribute("pageTitle", "Boutique")
        model.addAttribute("articles", articleRepository.findAll())
        return "boutique"
    }

    @GetMapping("/promotions")
    fun promotions(model: Model): String {
        model.addAttribute("activePage", "promotions")
        model.addAttribute("pageTitle", "Promotions")
        model.addAttribute("articles", articleRepository.findAll())
        return "promotions"
    }

    @GetMapping("/contact")
    fun contact(model: Model): String {
        model.addAttribute("activePage", "contact")
        model.addAttribute("pageTitle", "Contact")
        return "contact"
    }

    @GetMapping("/panier")
    fun panier(model: Model): String {
        model.addAttribute("pageTitle", "Mon Panier")
        return "panier"
    }

    @GetMapping("/connexion")
    fun connexion(model: Model): String {
        model.addAttribute("pageTitle", "Connexion")
        return "connexion"
    }

    @GetMapping("/inscription")
    fun inscription(model: Model): String {
        model.addAttribute("pageTitle", "Inscription")
        return "inscription"
    }

    @GetMapping("/categories/{id}")
    fun categorie(@PathVariable id: Long, model: Model): String {
        val categorie = categorieRepository.findById(id).orElseThrow()
        model.addAttribute("categorie", categorie)
        model.addAttribute("articles", categorie.articles)
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", categorie.nom)
        return "categories/categorie"
    }
}
