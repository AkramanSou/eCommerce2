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

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("activePage", "accueil")
        model.addAttribute("pageTitle", "Accueil")
        return "index"
    }

    @GetMapping("/boutique")
    fun boutique(model: Model): String {
        model.addAttribute("activePage", "boutique")
        model.addAttribute("pageTitle", "Boutique")
        return "boutique"
    }

    @GetMapping("/promotions")
    fun promotions(model: Model): String {
        model.addAttribute("activePage", "promotions")
        model.addAttribute("pageTitle", "Promotions")
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

    // ── Catégories ──

    @GetMapping("/categories/gants")
    fun gants(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Gants de Boxe")
        return "categories/gants"
    }

    @GetMapping("/categories/vetements")
    fun vetements(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Vêtements")
        return "categories/vetements"
    }

    @GetMapping("/categories/protections")
    fun protections(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Protections")
        return "categories/protections"
    }

    @GetMapping("/categories/accessoires")
    fun accessoires(model: Model): String {
        model.addAttribute("activePage", "categories")
        model.addAttribute("pageTitle", "Accessoires")
        return "categories/accessoires"
    }
}
