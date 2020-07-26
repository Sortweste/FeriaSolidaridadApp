package com.sort.feriaapp.data

class SocialMedia() {
    var name:String? = null
    var url:String? = null
    var type:String? = null

    constructor(name:String, url:String, type:String):this(){
        this.name = name
        this.url = url
        this.type = type
    }

}