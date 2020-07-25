package com.sort.feriaapp.data

/* @Entity(tableName = "articles")
data class Article (
    @PrimaryKey val id: Int

) */
class Article (){

    var id:Int? = null
    var foto:Int = 0
    var title:String? = null
    var institution:String? = null
    var description:String? = null
    var video:Int = 0
    var meeting_url:String? = null
    var events:ArrayList<Events>? = null
    var type:String? = null
    var homepage:String? = null
    //var social_media:ArrayList<socialMedia>? = null

    constructor(id: Int,foto:Int,title:String, institution:String, description:String):this(){
        this.id = id
        this.foto = foto
        this.title = title
        this.institution = institution
        this.description = description
    }
    constructor(id: Int,foto:Int,title:String, institution:String, description:String,video:Int,meeting_url:String,
    events: ArrayList<Events>,type:String,homepage:String):this(){
        this.id = id
        this.foto = foto
        this.title = title
        this.institution = institution
        this.description = description
        this.video = video
        this.meeting_url = meeting_url
        this.events = events
        this.type = type
        this.homepage = homepage
    }
}