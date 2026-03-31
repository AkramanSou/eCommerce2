package org.example.ecommerce.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "categories")
class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var nom: String = ""

    var description: String = ""

    var icone: String = "🥊"

    @OneToMany(mappedBy = "categorie", cascade = [CascadeType.ALL])
    var articles: MutableList<Article> = mutableListOf()
}