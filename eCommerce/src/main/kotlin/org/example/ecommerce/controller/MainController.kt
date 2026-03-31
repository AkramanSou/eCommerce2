//package org.example.ecommerce.controller
//
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.GetMapping
//
//@Controller
//class MainController (){
//
//    /**
//     * Méthode permettant d'afficher la page d'accueil de l'application.
//     * @return le chemin vers le template a partir du dossier ressources/templates (on ne marque pas le .html)
//     */
//    @GetMapping("/ecommerce")
//    fun home():String{
//        return "index"
//    }
//
//
//}

package org.example.ecommerce.controller

import org.example.ecommerce.repository.ArticleRepository
import org.example.ecommerce.repository.CategorieRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(
    val categorieRepository: CategorieRepository,
    val articleRepository: ArticleRepository
) {

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

    @GetMapping("/categories/gants")
    fun gants(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Gants de Boxe")
        val categorie = categorieRepository.findAll().find { it.nom == "Gants" }
        model.addAttribute("articles", categorie?.articles ?: emptyList<Any>())
        return "categories/gants"
    }

    @GetMapping("/categories/vetements")
    fun vetements(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Vêtements")
        val categorie = categorieRepository.findAll().find { it.nom == "Vêtements" }
        model.addAttribute("articles", categorie?.articles ?: emptyList<Any>())
        return "categories/vetements"
    }

    @GetMapping("/categories/protections")
    fun protections(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Protections")
        val categorie = categorieRepository.findAll().find { it.nom == "Protections" }
        model.addAttribute("articles", categorie?.articles ?: emptyList<Any>())
        return "categories/protections"
    }

    @GetMapping("/categories/accessoires")
    fun accessoires(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Accessoires")
        val categorie = categorieRepository.findAll().find { it.nom == "Accessoires" }
        model.addAttribute("articles", categorie?.articles ?: emptyList<Any>())
        return "categories/accessoires"
    }
}
