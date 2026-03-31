package org.example.ecommerce.model

import jakarta.persistence.*

@Entity
@Table(name = "articles")
class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var nom: String = ""

    var description: String = ""

    @Column(nullable = false)
    var prix: Double = 0.0

    var icone: String = "🥊"

    var stock: Int = 0

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    var categorie: Categorie? = null
}