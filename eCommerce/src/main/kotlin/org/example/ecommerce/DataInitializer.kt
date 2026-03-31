package org.example.ecommerce

import org.example.ecommerce.model.Article
import org.example.ecommerce.model.Categorie
import org.example.ecommerce.repository.ArticleRepository
import org.example.ecommerce.repository.CategorieRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    val categorieRepository: CategorieRepository,
    val articleRepository: ArticleRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        if (categorieRepository.count() > 0) return // évite de re-insérer à chaque démarrage

        // Catégories
        val gants = Categorie().apply {
            nom = "Gants"
            description = "Gants de boxe pour tous niveaux"
            icone = "🥊"
        }

        val vetements = Categorie().apply {
            nom = "Vêtements"
            description = "Shorts, t-shirts, hoodies"
            icone = "🎽"
        }

        val protections = Categorie().apply {
            nom = "Protections"
            description = "Casques, coquilles, protège-dents"
            icone = "🛡️"
        }

        val accessoires = Categorie().apply {
            nom = "Accessoires"
            description = "Sacs, cordes, bandages"
            icone = "⚙️"
        }

        categorieRepository.saveAll(listOf(gants, vetements, protections, accessoires))

        // Articles
        articleRepository.saveAll(listOf(
            Article().apply { nom = "Gants Pro Combat"; prix = 79.99; icone = "🥊"; stock = 15; categorie = gants },
            Article().apply { nom = "Gants Entraînement"; prix = 44.99; icone = "🥊"; stock = 20; categorie = gants },
            Article().apply { nom = "Gants MMA Open"; prix = 39.99; icone = "🥋"; stock = 10; categorie = gants },
            Article().apply { nom = "Short de Boxe"; prix = 34.99; icone = "🩱"; stock = 25; categorie = vetements },
            Article().apply { nom = "Hoodie Combat"; prix = 59.99; icone = "🎽"; stock = 12; categorie = vetements },
            Article().apply { nom = "Casque de Sparring"; prix = 59.99; icone = "🛡️"; stock = 8; categorie = protections },
            Article().apply { nom = "Protège-dents Pro"; prix = 19.99; icone = "🦷"; stock = 30; categorie = protections },
            Article().apply { nom = "Sac de Frappe"; prix = 119.99; icone = "💪"; stock = 5; categorie = accessoires },
            Article().apply { nom = "Corde à sauter"; prix = 14.99; icone = "🪢"; stock = 40; categorie = accessoires }
        ))
    }
}