package com.test.ecomm.adapter.persistence.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(
    name = "category",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name", "name"])
    ]
)
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "parent_id")
    var parent: CategoryEntity? = null,
)
