package com.wallace.game.virtualtabletop.message

/**
 * Created by work on 10/8/2016.
 */
class Login () {
    lateinit var userID: String

    constructor(userID: String) : this() {
        this.userID = userID
    }
}