package by.service.tp.OneCBase.data

import by.service.tp.OneCBase.MyApp

object SectionDAO {
      fun getAllSections(_idTheme: Int): List<Section> {
            val db = MyApp.myDatabase.readableDatabase
            val cursor =  db.rawQuery("SELECT * FROM section WHERE section.id_theme = $_idTheme ", null);
            val items = mutableListOf<Section>()
            if (cursor.moveToFirst()) {
                do {
                    items.add(Section(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), false))
                } while (cursor.moveToNext());
            }
            db.close()
            return items
        }
}