package com.group.library_app.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    name: String,
    age: Int? = 0,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "name", nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(name = "age")
    var age: Int? = age
        protected set

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ACTIVE
        protected set

    fun updateName(name: String) {
        this.name = name
    }

    fun updateAge(age: Int) {
        this.age = age
    }

    fun delete() {
        this.status = UserStatus.DELETED
    }
}