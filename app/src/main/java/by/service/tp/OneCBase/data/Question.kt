package by.service.tp.OneCBase.data

data class Question(
    var _id_theme: Int,
    var _id_section: Int,
    var _id_question: Int,
    var nameSection: String,
    var orderQuestion: Int,
    var nameQuestion: String,
    var rightChoice: Int,
    var userChoice: Int
)