package com.test.ecomm.adapter.persistence.model

import javax.persistence.*

@Entity
@Table(
    name = "product",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name", "name"])
    ]
)
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var price: Double? = null,

    @Column(nullable = false)
    var currency: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: CategoryEntity? = null
)
