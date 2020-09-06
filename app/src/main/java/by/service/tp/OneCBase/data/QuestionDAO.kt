package by.service.tp.OneCBase.data

import by.service.tp.OneCBase.MyApp


object QuestionDAO {
      fun getQuestions(_idTheme: Int ,_idSections: IntArray): List<Question> {
            val db = MyApp.myDatabase.readableDatabase
            val sections = this.convertArrayToStr(_idSections)

          //////////////
          val queryText1: String = "SELECT question.id_theme, question.id_section,question.id_question from question" +
                  " left join questionVariant on ((question.id_theme = questionVariant.id_theme) & (question.id_section = questionVariant.id_section) & (question.id_question = questionVariant.id_question))" +
                  " where ((question.id_theme = $_idTheme)&(question.id_section IN $sections)&(question.id_question != 0)) "
          val cursor1=  db.rawQuery(queryText1, null)
          val questList = mutableListOf<QuestList>()
          if (cursor1.moveToFirst()) {
              do {
                  val element = QuestList(cursor1.getInt(0).toString() +","+ cursor1.getInt(1).toString() +","+ cursor1.getInt(2).toString())
                  if (!questList.contains(element)){
                      questList.add(element)
                  }
              } while (cursor1.moveToNext());
          }
          val questListFinal = mutableListOf<QuestList>()
          if (questList.isNotEmpty()) {
              for ( i in 1..14) {
              questListFinal.add(questList[(1..questList.size).random()-1])
              }
          }
          ////////////////////////////////

            val queryText: String = "SELECT question.id_theme, question.id_section,question.id_question,question.name, questionVariant.\"order\", questionVariant.name, questionVariant.right_choice from question" +
                    " left join questionVariant on ((question.id_theme = questionVariant.id_theme) & (question.id_section = questionVariant.id_section) & (question.id_question = questionVariant.id_question))" +
                    " where ((question.id_theme = $_idTheme)&(question.id_section IN $sections)&(question.id_question != 0)) "
          val cursor =  db.rawQuery(queryText, null)

          val items = mutableListOf<Question>()
            if (cursor.moveToFirst()) {
                do {
                    val currentItem = Question(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6),0 )
                    val currentItemCheck = QuestList(currentItem._id_theme.toString() + "," + currentItem._id_section.toString() +","+ currentItem._id_question.toString())
                    if(questListFinal.contains(currentItemCheck)){
                        items.add(currentItem)
                    }
                } while (cursor.moveToNext());
            }
            db.close()

//            var result: MutableList<Question> = emptyList<Question>().toMutableList()
//            if (items.isNotEmpty()){
//                val random = Random(items.size)
//                val newList = items.map {  }
//                for ( i in 1..14){
//                    //  (0..10).random()   random.nextInt()
//                    result.add(items[(1..items.size).random()-1])
//                }
//            }
            return items
        }

    private fun convertArrayToStr(arr:IntArray): String {
        return "(" + arr.joinToString(",") + ")"
    }
}