package com.teerak.petsplacecenter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME" +
                "($user_id Integer PRIMARY KEY, $username TEXT, $password TEXT, $user_Name TEXT, $user_Lname TEXT, $user_tel TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private val DB_NAME = "profileDB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "profile"
        private val user_id = "user_id"
        private val username = "username"
        private val password = "password"
        private val user_Name = "user_Name"
        private val user_Lname = "user_Lname"
        private val user_tel = "user_tel"
    }

    fun getProfile(): ArrayList<User> {
        val profileDB = ArrayList<User>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from $TABLE_NAME", null)
        } catch (e: SQLiteException) {
            onCreate(db)
            return ArrayList()
        }
        var userID: Int
        var UN: String
        var PW: String
        var name: String
        var lname: String
        var tel: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userID = cursor.getInt(cursor.getColumnIndex(user_id))
                UN = cursor.getString(cursor.getColumnIndex(username))
                PW = cursor.getString(cursor.getColumnIndex(password))
                name = cursor.getString(cursor.getColumnIndex(user_Name))
                lname = cursor.getString(cursor.getColumnIndex(user_Lname))
                tel = cursor.getString(cursor.getColumnIndex(user_tel))

                profileDB.add(User(userID, UN, PW, name, lname, tel))
                cursor.moveToNext()
            }
        }
        db.close()
        return profileDB
    }

    fun insertProfile (profileDB: User): Long {
        val db = writableDatabase

        val values = ContentValues()
        values.put(user_id, profileDB.user_id)
        values.put(username, profileDB.username)
        values.put(password, profileDB.password)
        values.put(user_Name, profileDB.user_Name)
        values.put(user_Lname, profileDB.user_Lname)
        values.put(user_tel, profileDB.user_tel)

        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return success
    }

    /*fun updateStudent (std: Student) : Int {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, std.name)
        values.put(COLUMN_AGE, std.age)

        val success = db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(std.id))
        db.close()
        return success
    }*/
    fun deleteUserProfile(user_id: String): Int
    {
        val db = writableDatabase
        val success = db.delete(TABLE_NAME, "$user_id = ?", arrayOf(user_id))
        db.close()
        return success
    }
}

