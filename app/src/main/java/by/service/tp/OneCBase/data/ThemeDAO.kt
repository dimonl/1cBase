package by.service.tp.OneCBase.data

import by.service.tp.OneCBase.MyApp

object ThemeDAO {
      fun getAllThemes(): List<Theme> {
            val db = MyApp.myDatabase.readableDatabase
            val cursor =  db.rawQuery("SELECT * FROM theme", null);
            val items = mutableListOf<Theme>()
            if (cursor.moveToFirst()) {
                do {
                    items.add(Theme(cursor.getInt(0), cursor.getString(1)))
                } while (cursor.moveToNext());
            }
            db.close()
            return items
        }
}