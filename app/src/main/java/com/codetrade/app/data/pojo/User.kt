package com.codetrade.app.data.pojo

class User {
    lateinit var name: String
    lateinit var email: String
    lateinit var loginType: String

    constructor()

    constructor(name: String, email: String) {
        this.name = name
        this.email = email
    }

    constructor(name: String, email: String, type: String) {
        this.name = name
        this.email = email
        this.loginType = type
    }
}