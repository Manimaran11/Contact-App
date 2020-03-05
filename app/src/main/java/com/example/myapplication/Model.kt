package com.example.myapplication

class Model {
    lateinit var first_name:String
    lateinit var last_name:String
    lateinit var work_number:String
    constructor(first_name: String,last_name:String,work_number:String) {
        this.first_name = first_name
        this.last_name = last_name
        this.work_number = work_number
    }
    constructor()
}